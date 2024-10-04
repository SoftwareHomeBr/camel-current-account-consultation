package br.com.santander.cxa.currentaccount.route.integration;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.http.common.HttpMethods;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.processor.errorhandler.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import br.com.santander.cxa.currentaccount.model.dto.integration.LogResponseDTO;
import br.com.santander.cxa.currentaccount.model.dto.integration.TapeRecordResponseDTO;
import br.com.santander.cxa.currentaccount.processor.CashierTapeProcessor;
import br.com.santander.cxa.currentaccount.processor.OpenLogProcessor;
import br.com.santander.cxa.currentaccount.processor.RewriteLogProcessor;

/**
 * Current account balance camel route
 * 
 * @author Bruno Nunesmaia (X245006)
 * @since 30/10/2020
 */
@Component
public class CashierTapeRoute extends RouteBuilder {
	
	//private JacksonDataFormat logResponseFormat = new ListJacksonDataFormat(LogResponseDTO.class);

	@Autowired 
	private RedeliveryPolicy redeliveryPolicy;
	
	@Value(value = "${env.api.database.current-account.prefix}")
	private String apiUrl;
	
	@Override
	public void configure() throws Exception {
    	getContext().setStreamCaching(true);
		
		onException(HttpServerErrorException.class)
			.markRollbackOnly()
			//.redeliveryPolicy(redeliveryPolicy)
			.useExponentialBackOff();
		
		onException(HttpClientErrorException.class)
			.markRollbackOnly()
			//.redeliveryPolicy(redeliveryPolicy)
			.useExponentialBackOff();
		
		from("direct:grava-fita-caixa")
			.routeId("grava-fita-caixa")
			.doTry()
				.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
				.process(new CashierTapeProcessor()).id("CashierTapeProcessor")
				.marshal().json(JsonLibrary.Gson).id("marshall fita caixa request")
				.toD(apiUrl + "/tapeRecord?bridgeEndpoint=true").id("call->ars:fitacaixa")
				.unmarshal().json(JsonLibrary.Jackson, TapeRecordResponseDTO.class).id("unmarshall fitacaixa response")
				.setProperty("retornoFichaCaixa", body())
			.doCatch(Exception.class)
				.setHeader("error", simple("grava-fita-caixa"))
				.to("direct:end-with-error")
			.end();
	}
}