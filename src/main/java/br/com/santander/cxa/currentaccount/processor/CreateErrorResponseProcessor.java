package br.com.santander.cxa.currentaccount.processor;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.google.gson.Gson;

import br.com.santander.cxa.currentaccount.model.dto.ErrorResponseDTO;

public class CreateErrorResponseProcessor implements Processor {

	private Integer httpStatusCode;
	private String httpContentType;
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateErrorResponseProcessor.class);
	
	public CreateErrorResponseProcessor() {}
	
	/**
	 * @param httpStatusCode
	 */
	public CreateErrorResponseProcessor(Integer httpStatusCode) {
		super();
		this.httpStatusCode = httpStatusCode;
	}

	/**
	 * @param httpStatusCode
	 * @param httpContentType
	 */
	public CreateErrorResponseProcessor(Integer httpStatusCode, String httpContentType) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.httpContentType = httpContentType;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		
		Exception exception = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		
	//	if(exception instanceof HttpOperationFailedException) {
	//		exchange.getMessage().setBody(((HttpOperationFailedException) exception).getResponseBody());
	//		exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, ((HttpOperationFailedException) exception).getStatusCode());
	//		return;
	//	}
		
		ErrorResponseDTO errorResponse = new ErrorResponseDTO();

		errorResponse.setTimestamp(String.valueOf(new Date().getTime()));
		errorResponse.setTrackingId(exchange.getMessage().getHeader("X-traceId", String.class));

		Integer headerHttpStatusCode = exchange.getMessage().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class);
		if (headerHttpStatusCode != null) {
			errorResponse.setHttpStatusCode(headerHttpStatusCode);
			exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, headerHttpStatusCode);
		} else if (httpStatusCode != null) {
			errorResponse.setHttpStatusCode(httpStatusCode);
			exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, httpStatusCode);
		} else {
			errorResponse.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			exchange.getMessage().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		
		if (StringUtils.isNotBlank(httpContentType)) {
			exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, httpContentType);
		} else {
			exchange.getMessage().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON);			
		}

		String passoErro = (String)exchange.getMessage().getHeader("error");
		
		String message = null;
		if (exception != null) {
			message = "Erro: " + passoErro + " : " + exception.toString();
			errorResponse.setDetails(message);
		}
		else
		{
			message = "Erro: " + passoErro;
			errorResponse.setDetails(passoErro);
		}
		
		LOGGER.error(message);
		
		//TODO: rever
		//exchange.getMessage().setFault(true);

		Gson gson = new Gson();
		String objectJsonAsString = gson.toJson(errorResponse);
	
		exchange.getMessage().setBody(objectJsonAsString);
	}

}