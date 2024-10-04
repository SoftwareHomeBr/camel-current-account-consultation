package br.com.santander.cxa.currentaccount.model.dto;

import br.com.santander.bhs.camel.embeddedcrypto.annotation.DlbCryptoClass;
import br.com.santander.bhs.camel.embeddedcrypto.annotation.DlbCryptoField;
import br.com.santander.cxa.currentaccount.model.Account;
import br.com.santander.cxa.currentaccount.model.ContextData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseRequestDTO {
	
	@DlbCryptoClass
	private Account account;
	
	@DlbCryptoClass
	private ContextData context;
	
	@DlbCryptoField
	private String operatorUserCode;
	@DlbCryptoField
	private String terminal;
	@DlbCryptoField
	private String transactionCode;
}
