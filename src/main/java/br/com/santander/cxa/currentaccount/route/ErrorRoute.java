package br.com.santander.cxa.currentaccount.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import br.com.santander.cxa.bigdata.BigData;
import br.com.santander.cxa.bigdata.model.BigDataEnum;
import br.com.santander.cxa.currentaccount.model.dto.BaseRequestDTO;
import br.com.santander.cxa.currentaccount.processor.CreateErrorResponseProcessor;

@Component
public class ErrorRoute extends RouteBuilder {

	@Override
    public void configure() throws Exception {
		
		from("direct:end-with-error")
			.routeId("current-account-end-with-error")
			.log(LoggingLevel.ERROR, exceptionMessage().toString())
			.choice().when(simple("${header:error} != \"rewrite-log\""))
				.to("direct:rewriteLog")
			.end()
			.process(e -> {
				BigData.add(e, BigDataEnum.DESCR_ERRO, (String)e.getMessage().getHeader("error"));
			})
			.toD("cxa-bigdata:send")
			.process(new CreateErrorResponseProcessor()).id("CreateErrorResponseProcessor")
		.end();
		
    }
}
