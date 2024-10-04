package br.com.santander.cxa.currentaccount.model;

import br.com.santander.bhs.camel.embeddedcrypto.annotation.DlbCryptoField;
import br.com.santander.bhs.camel.embeddedcrypto.enumerator.DlbMethodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Account {

	@DlbCryptoField(type = DlbMethodType.DECRYPT)
	private String bankCode;
	
	@DlbCryptoField(type = DlbMethodType.DECRYPT)
	private String branchCode;
	
	@DlbCryptoField(type = DlbMethodType.DECRYPT)
	private String accountNumber;
	
	@DlbCryptoField(type = DlbMethodType.DECRYPT)
	private String cardNumber;
}
