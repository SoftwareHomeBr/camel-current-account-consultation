package br.com.santander.cxa.currentaccount.model.dto.integration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContextDataDTO {
	
	private String bank;
	private String branch;
	private String terminalCode;
	private String premise;
	private String operatorCode;
	private String supervisor;
}
