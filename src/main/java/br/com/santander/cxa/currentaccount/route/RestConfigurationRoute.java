package br.com.santander.cxa.currentaccount.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * Current account consultation camel rest api
 * 
 * @author Bruno Nunesmaia (X245006)
 * @since 30/10/2020
 */
@Component
public class RestConfigurationRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
	
		restConfiguration()
			.bindingMode(RestBindingMode.json)
			.apiContextPath("/swagger") 
			.apiContextRouteId("swagger")
            .apiProperty("api.title", "Swagger Current Account")
            .apiProperty("api.description", "An Current Account consultation API in Camel")
            .apiProperty("api.version", "1.0.0-SNAPSHOT")
            .contextPath("/bff-cxa-current-account/v1/api/")
            .apiProperty("host", "")
            .apiProperty("port", "8080")
            .apiProperty("api.contact.name", "Bruno Nunesmaia (X245006)")
            .apiProperty("api.contact.email", "bruno.nunesmaia@prservicos.com.br")
            .apiProperty("schemes", "http")
            .dataFormatProperty("json.in.disableFeatures", "FAIL_ON_EMPTY_BEANS")
            .dataFormatProperty("json.out.disableFeatures", "FAIL_ON_EMPTY_BEANS");
	}
}
