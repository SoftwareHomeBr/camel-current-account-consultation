package br.com.santander.cxa.currentaccount.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.santander.cxa.bigdata.BigData;
import br.com.santander.cxa.bigdata.model.BigDataEnum;
import br.com.santander.cxa.currentaccount.model.dto.BaseRequestDTO;
import br.com.santander.cxa.currentaccount.model.dto.CurrentAccountBalanceResponseDTO;
import br.com.santander.cxa.currentaccount.processor.DecryptTurboResponseProcessor;
import br.com.santander.cxa.currentaccount.processor.FormatResponseFieldsAndPrepareForEncryptionProcessor;
import br.com.santander.cxa.currentaccount.processor.PrepareTurboBalanceRequestProcessor;
import br.com.santander.cxa.util.POM;

/**
 * Current account balance camel route
 * 
 * @author Bruno Nunesmaia (X245006)
 * @since 30/10/2020
 */
@Component
public class BalanceRoute extends RouteBuilder {
	
	@Value("${env.api.turbo.current-account.balance.prefix}")
	private String urlApiTurboAccountBalance;

	private static final Logger LOGGER = LoggerFactory.getLogger(BalanceRoute.class);
	
	
	/**
	 * passos:
	 *   - Abertura Log
	 *   - chamada ao Turbo
	 *   - regravação log
	 *   - gravação fita caixa
	 *   - gravação bigdata (sucesso no final)
	 *   
	 *   Erro em qualquer passo irá fazer com que seja gravado log no bigdata
	 */
    @Override
    public void configure() throws Exception {

    	getContext().setStreamCaching(true);

    	//cuidado, se habilitar tracing em conjunto com o hystrix, ocorre NPE no Camel e o hystrix acaba entrando em modo fallback.
    	//getContext().setTracing(true);
    	
		from("direct:balance")
			.routeId("balance")
			.circuitBreaker()
				.to("direct:balance-backend")
			.onFallback()
				.to("direct:timeout")
			.end();
		;
		from("direct:balance-backend")
			.id("retrieve-balance-backend")
			.doTry()
				.setProperty("X-EncryptedObject", simple("${header.X-EncryptedObject}"))
				.to("direct:validate-decrypt")
				.log("${body}")
				.setProperty("requestData", body())
				// faz a chamada para abertura de log
				.process(e -> {
					BaseRequestDTO request = (BaseRequestDTO) e.getProperty("requestData");
					BigData.add(e, BigDataEnum.AGENCIA_OPERACAO, request.getAccount().getBranchCode());
					BigData.add(e,  BigDataEnum.BANCO_CLIENTE, request.getAccount().getBankCode());
					BigData.add(e,  BigDataEnum.AGENCIA_TERM, request.getContext().getBranch());
					BigData.add(e,  BigDataEnum.BANCO_TERM, request.getContext().getBank());
					BigData.add(e, BigDataEnum.VERSAO_APLIC, POM.getVersion());

				})
				.to("direct:openLog")
				// chama a rota de chamada ao turbo
				.to("direct:get-balance-turbo")
			.doCatch(Exception.class)
				.setHeader("error", simple("open-log"))
				.to("direct:end-with-error")
			.end();

		from("direct:get-balance-turbo")
		.id("get-balance-turbo")
		.doTry()
			.setHeader("authorization", simple("${header.x-access-token}"))
			.process(e -> {
				e.getIn().setHeader("X-EncryptedObject", e.getProperty("X-EncryptedObject"));
			})
			.setHeader(Exchange.HTTP_METHOD, constant("GET"))
			.process(new PrepareTurboBalanceRequestProcessor()).id("prepare turbo balance request")
			.marshal().json(JsonLibrary.Gson).id("marshall turbo request")
			.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
			//Realiza chamada ao Turbo para busca das informações de saldo
			.toD(urlApiTurboAccountBalance + "?bridgeEndpoint=true&codigoCtaCliente=${header.codigoCtaCliente}&divisa=${header.divisa}" )
			.id("call turbo balance")
			.unmarshal().json(JsonLibrary.Jackson)
			.process(new DecryptTurboResponseProcessor()).id("decrypt turbo response")
			.unmarshal().json(JsonLibrary.Gson)
			.setProperty("respostaTurbo", simple("${body}"))
			// Realiza transformação JSON dos formatos AEA para o payload de retorno para o front
			.to("jolt:jolt.balance.spec")
			.process(new FormatResponseFieldsAndPrepareForEncryptionProcessor()).id("format response fields")
			.setProperty("saldo", simple("${body.availableBalance}"))
			.setProperty("nomeCliente", simple("${body.customerName}"))
			// encrypta os campos de retorno
			.to("direct:validate-encrypt")
			.setProperty("retornoConsultiva", body())
			.setProperty("sucessoConsultiva", simple("true"))
			.doTry()
				.to("direct:rewriteLog")
				.endDoTry()
				.doCatch(Exception.class)
					.process(e -> {
						LOGGER.error("Erro na rota rewriteLog");
					})
				.setHeader("error", simple("rewriteLog"))
				.to("direct:end-with-error")
				.end()
			.to("direct:grava-fita-caixa")
			// Retorna para a exchange o body do retorno para o front 
			.process(e -> {
				CurrentAccountBalanceResponseDTO retornoConsultiva = (CurrentAccountBalanceResponseDTO)e.getProperty("retornoConsultiva");
				e.getMessage().setBody(retornoConsultiva);
			})
			.toD("cxa-bigdata:send")
		.endDoTry()
		.doCatch(Exception.class)
			.process(e -> {
				Exception exception = e.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
				LOGGER.error("Erro na chamada ao Turbo: " + exception.toString());
				exception.printStackTrace();
				LOGGER.error("message body: " + e.getMessage().getBody(String.class));
			}).id("erro chamada turbo account-balances")
				.setProperty("sucessoConsultiva", simple("false"))
				.setHeader("error", simple("consultiva"))
				.to("direct:end-with-error")
		.end();
    
    }
}