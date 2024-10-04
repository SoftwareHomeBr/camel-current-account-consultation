package br.com.santander.cxa.currentaccount.model.dto;

import br.com.santander.cxa.currentaccount.model.dto.basetransaction.BaseTransactionResponseDTO;
import lombok.Data;

@Data
public class BaseResponseDTO {
	
	private BaseTransactionResponseDTO baseTransaction;
}
