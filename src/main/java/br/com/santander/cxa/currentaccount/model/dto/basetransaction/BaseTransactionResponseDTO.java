package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class BaseTransactionResponseDTO {

	protected TransactionDataDTO transactionData;
	protected PrinterControlDTO printerControl;
	protected ErrorDTO error;
}
