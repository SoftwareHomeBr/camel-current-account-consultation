package br.com.santander.cxa.currentaccount.model.dto.integration;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LogResponseDTO implements Serializable {

	private static final long serialVersionUID = -6711415392964986482L;

	private String transactionNsu;
	protected ErrorDTO error;
}
