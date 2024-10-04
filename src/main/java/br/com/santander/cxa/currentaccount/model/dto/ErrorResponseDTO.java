package br.com.santander.cxa.currentaccount.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorResponseDTO {

	private String timestamp;
	private Integer httpStatusCode;
	private Object details;
	private String trackingId;
}