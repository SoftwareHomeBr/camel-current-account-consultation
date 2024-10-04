package br.com.santander.cxa.currentaccount.model.dto.integration;

import br.com.santander.bhs.camel.embeddedcrypto.annotation.DlbCryptoField;
import br.com.santander.bhs.camel.embeddedcrypto.enumerator.DlbMethodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CurrentAccountBalanceRequestDTO {
	//@DlbCryptoField(type = DlbMethodType.ENCRYPT)
	private String codigoCtaCliente;
	private String divisa;
}
