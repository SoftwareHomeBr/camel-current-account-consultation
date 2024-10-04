package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import lombok.Data;

@Data
public class ErrorDTO {

	private String altairErrorCode;
	private String code;
	private String description;
}
