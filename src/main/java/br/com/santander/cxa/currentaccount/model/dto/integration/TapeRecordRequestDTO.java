package br.com.santander.cxa.currentaccount.model.dto.integration;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class TapeRecordRequestDTO {
	private String bankCode;
	private String bankBranchCode;
	private String accountNumber;
	private String operatorCode;
	private String value;
	private String terminal;
	private String transactionNsu;
	private String transactionCode;
	private String customerName;
	private String authentication;
}
