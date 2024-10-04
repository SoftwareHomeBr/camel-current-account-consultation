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
public class ContextData {
	
	@DlbCryptoField(type = DlbMethodType.DECRYPT)
	private String bank;
	@DlbCryptoField(type = DlbMethodType.DECRYPT)
	private String branch;
	@DlbCryptoField(type = DlbMethodType.DECRYPT)
	private String terminalCode;
	@DlbCryptoField(type = DlbMethodType.DECRYPT)
	private String premise;
	@DlbCryptoField(type = DlbMethodType.DECRYPT)
	private String operatorCode;
	@DlbCryptoField(type = DlbMethodType.DECRYPT)
	private String supervisor;
}
