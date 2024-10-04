package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class AccountDataDTO {

	private String bankCode;
	private String branchCode;
	private String number;
	private String holder;
	private String type;
	private String description;
}
