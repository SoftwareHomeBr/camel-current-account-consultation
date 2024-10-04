package br.com.santander.cxa.currentaccount.model.dto.integration;

import java.io.Serializable;

public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = 4953529536874612513L;

	private String code;
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
