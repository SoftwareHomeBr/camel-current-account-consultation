package br.com.santander.cxa.currentaccount.model.dto.basetransaction;


import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class AuthorizationDataDTO {

	private String cryptographedPan;
	private String cryptographedPanContinuation;
	private String cardAuthorized;
	private String cardIdentified;
	private String biometricAuthorized;

	public boolean isCard() {
		String cardIdent = StringUtils.defaultIfEmpty(this.cardIdentified, "false");
		String cardAuth = StringUtils.defaultIfEmpty(this.cardAuthorized, "false");
		return Boolean.parseBoolean(cardIdent) || Boolean.parseBoolean(cardAuth);
	}

	public boolean isBiometry() {
		String bio = StringUtils.defaultIfEmpty(this.biometricAuthorized, "false");
		return Boolean.parseBoolean(bio);
	}
}
