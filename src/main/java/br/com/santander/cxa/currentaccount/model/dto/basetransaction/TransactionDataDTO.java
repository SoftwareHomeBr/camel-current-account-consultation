package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import lombok.Data;

@Data
public class TransactionDataDTO {

	private String transactionNsu;
	private String attendanceNsu;
	private String authentication;
	private String transactionKey;
}
