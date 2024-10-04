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
import br.com.santander.cxa.currentaccount.processor.OpenLogProcessor;
import br.com.santander.cxa.currentaccount.processor.RewriteLogProcessor;

/**
 * Current account balance camel route
 * 
 * @author Bruno Nunesmaia (X245006)
 * @since 30/10/2020
 */
@Component
public class LogRoute extends RouteBuilder {
	
	private JacksonDataFormat logResponseFormat = new ListJacksonDataFormat(LogResponseDTO.class);

	@Autowired 
	private RedeliveryPolicy redeliveryPolicy;
	
	@Value(value = "${env.api.database.current-account.prefix}")
	private String apiUrl;
	
	@Override
	public void configure() throws Exception {
    	getContext().setStreamCaching(true);
		
		onException(HttpServerErrorException.class)
			.markRollbackOnly()
		//	.redeliveryPolicy(redeliveryPolicy)
			.useExponentialBackOff();
		
		onException(HttpClientErrorException.class)
			.markRollbackOnly()
		//	.redeliveryPolicy(redeliveryPolicy)
			.useExponentialBackOff();
		
		from("direct:openLog")
			.routeId("open-log")
			.doTry()
				.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
				.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
				.process(new OpenLogProcessor()).id("OpenLogProcessor")
				.marshal().json(JsonLibrary.Gson).id("marshall log request")
				.toD(apiUrl + "/openLog?bridgeEndpoint=true").id("call->ars:openLog")
				.unmarshal().json(JsonLibrary.Jackson, LogResponseDTO.class).id("unmarshall log response")
				.process(e -> {
					var logResponse = (LogResponseDTO)e.getMessage().getBody();
					if (logResponse.getError()!=null)
						throw new Exception ("Error in openLog: " + logResponse.getError().getDescription());	
				}).id("debug-log3:openlog")
				.setProperty("openLogData", body())
			.doCatch(Exception.class)
				.setHeader("error", simple("open-log"))
				.to("direct:end-with-error")
		.end();

		from("direct:rewriteLog")
		.routeId("rewrite-log")
		.doTry()
			.setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.POST))
			.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
			.process(new RewriteLogProcessor()).id("RewriteLogProcessor")
			.marshal().json(JsonLibrary.Gson).id("rewrite marshall log request")
			.toD(apiUrl + "/rewriteLog?bridgeEndpoint=true").id("rewrite call->ars:openLog")
			.unmarshal().json(JsonLibrary.Jackson, LogResponseDTO.class).id("rewrite unmarshall log response")
			.process(e -> {
				var logResponse = (LogResponseDTO)e.getMessage().getBody();
				if (logResponse.getError()!=null)
					throw new Exception ("Error in rewriteLog: " + logResponse.getError().getDescription());	
		}).id("debug-log3:rewritelog")
		.setProperty("retornoRegravacaoLog", body())
		.doCatch(Exception.class)
			.setHeader("error", simple("rewrite-log"))
			.to("direct:end-with-error")
		.end();
	}
}