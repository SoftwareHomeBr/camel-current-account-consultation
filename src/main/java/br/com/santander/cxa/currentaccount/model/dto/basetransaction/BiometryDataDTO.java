package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import lombok.Data;

@Data
public class BiometryDataDTO {

	private String hash;
	private String guid;
	private String dna;
}
