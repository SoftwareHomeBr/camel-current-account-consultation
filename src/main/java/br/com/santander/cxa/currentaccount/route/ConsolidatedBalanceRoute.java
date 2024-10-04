package br.com.santander.cxa.currentaccount.route;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

/**
 * Consolidated current account balance camel route
 * 
 * @author Bruno Nunesmaia (X245006)
 * @since 02/11/2020
 */
@Component
public class ConsolidatedBalanceRoute extends RouteBuilder {
	
	//@Value("${api-config.service-name:CXA-CURRENT-ACCOUNT}")
	private String apiName = "CXA-CURRENT-ACCOUNT";

    @Override
    public void configure() throws Exception {

		from("direct:consolidated")
			.routeId("retrieve-consolidated-balance")
			.circuitBreaker()
				.to("direct:consolidated-backend")
			.onFallback()
				.to("direct:timeout")
			.end();
		
		from("direct:consolidated-backend")
			.routeId("retrieve-consolidated-balance-backend")
			.log(LoggingLevel.INFO, "++ " + apiName + " ++ INICANDO CONSULTA DE SALDO CONSOLIDADO DA CONTA CORRENTE ++")
			/*.choice()
				.when(simple("${header.X-EncryptedObject} != null"))
				.to("dlb:decrypt")
			.end()*/
			.doTry()
				.process(exchange -> {
					//UYMC7E0 altairRequest = new UYMC7E0();
					// altairRequest setar HeaderPG
					// altairRequest setar campos específicos
					//exchange.getIn().setBody(altairRequest);
				})
//				.to("altair://UYCA"
//						+ "?personado=false"
//						+ "&transactionName=UYCA"
//						+ "&useHeaderPs=false"
//						+ "&psFormatEnum=PsFormatEnum.PS7")
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
				.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.OK.value()))
			.doCatch(Exception.class)
			.endDoTry()
			.log(LoggingLevel.INFO, "++ " + apiName + " ++ FINALIZANDO CONSULTA DE SALDO CONSOLIDADO DA CONTA CORRENTE ++");
    }
}