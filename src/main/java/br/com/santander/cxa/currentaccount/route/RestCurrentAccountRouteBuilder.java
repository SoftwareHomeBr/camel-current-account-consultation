package br.com.santander.cxa.currentaccount.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import br.com.santander.cxa.currentaccount.model.dto.BaseRequestDTO;
import br.com.santander.cxa.currentaccount.model.dto.BaseResponseDTO;
import br.com.santander.cxa.currentaccount.model.dto.CurrentAccountBalanceResponseDTO;
import br.com.santander.cxa.currentaccount.model.dto.integration.LogResponseDTO;
import br.com.santander.cxa.currentaccount.processor.CreateErrorResponseProcessor;

/**
 * Current account consultation camel rest api
 * 
 * @author Bruno Nunesmaia (X245006)
 * @since 30/10/2020
 */
@Component
public class RestCurrentAccountRouteBuilder extends RouteBuilder {

	@Value("${http.header.integration}")
	private String headerIntegration;
	
	@Override
	public void configure() throws Exception {
		
		onException(Exception.class)
			.process(new CreateErrorResponseProcessor(HttpStatus.INTERNAL_SERVER_ERROR.value()))
		.end();
	
		rest("/balance")
			.description("Retrieve current account balance")
			
            .post()
				.id("rest-current-account-balance")
				.description("Retrieve current account balance")
				.bindingMode(RestBindingMode.auto)
				.skipBindingOnErrorCode(true)
				.type(BaseRequestDTO.class)
				.outType(CurrentAccountBalanceResponseDTO.class)
				.responseMessage()
                	.code(HttpStatus.OK.value()).message("Success")
                .endResponseMessage()
                .responseMessage()
                	.code(420).message("Bussiness Error")
                .endResponseMessage()
                .responseMessage()
                	.code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Internal Server Error")
                .endResponseMessage()
                .responseMessage()
                	.code(HttpStatus.GATEWAY_TIMEOUT.value()).message("Integration Timeout")
                .endResponseMessage()
				.consumes(MediaType.APPLICATION_JSON_VALUE)
				.produces(MediaType.APPLICATION_JSON_VALUE)
				.to("direct:balance");
		
		rest("/statements")
			.description("Retrieve current account statements")
		
	        .post()
				.id("rest-current-account-statements")
				.description("Retrieve current account statements")
				.bindingMode(RestBindingMode.auto)
				.skipBindingOnErrorCode(true)
				.type(BaseRequestDTO.class)
				.outType(BaseResponseDTO.class)
				.responseMessage()
                	.code(HttpStatus.OK.value()).message("Success")
                .endResponseMessage()
                .responseMessage()
                	.code(420).message("Bussiness Error")
                .endResponseMessage()
                .responseMessage()
                	.code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Internal Server Error")
                .endResponseMessage()
                .responseMessage()
                	.code(HttpStatus.GATEWAY_TIMEOUT.value()).message("Integration Timeout")
                .endResponseMessage()
				.consumes(MediaType.APPLICATION_JSON_VALUE)
				.produces(MediaType.APPLICATION_JSON_VALUE)
				.to("direct:statements");
		
		rest("/consolidated")
			.description("Retrieve consolidated current account balance")
		
			.post()
				.id("rest-current-account-consolidated-balance")
				.description("Retrieve consolidated current account balance")
				.bindingMode(RestBindingMode.auto)
				.skipBindingOnErrorCode(true)
				.type(BaseRequestDTO.class)
				.outType(BaseResponseDTO.class)
				.responseMessage()
                	.code(HttpStatus.OK.value()).message("Success")
                .endResponseMessage()
                .responseMessage()
                	.code(420).message("Bussiness Error")
                .endResponseMessage()
                .responseMessage()
                	.code(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Internal Server Error")
                .endResponseMessage()
                .responseMessage()
                	.code(HttpStatus.GATEWAY_TIMEOUT.value()).message("Integration Timeout")
                .endResponseMessage()
				.consumes(MediaType.APPLICATION_JSON_VALUE)
				.produces(MediaType.APPLICATION_JSON_VALUE)
				.to("direct:consolidated");
	}
}
