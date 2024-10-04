package br.com.santander.cxa.currentaccount.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


/**
 * TimeoutRoute
 */
@Component
public class TimeoutRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

		from("direct:timeout")
			.routeId("timeout-error-response")
			.setBody(simple(""))
			.setHeader(Exchange.CONTENT_TYPE, constant(MediaType.TEXT_PLAIN_VALUE))
			.setHeader(Exchange.HTTP_RESPONSE_CODE,constant(HttpStatus.GATEWAY_TIMEOUT.value()));
    }
}