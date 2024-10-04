package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import lombok.Data;

@Data
public class CardDataDTO {

	private String debitCardNumber;
	private String cardVia;
	private String cardExpiration;
}
