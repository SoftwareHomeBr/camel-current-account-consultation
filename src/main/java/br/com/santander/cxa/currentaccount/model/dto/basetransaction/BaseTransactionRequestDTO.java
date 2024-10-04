package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
public class BaseTransactionRequestDTO {

	protected AccountDataDTO accountData;
	protected AuthorizationDataDTO authorizationData;
	protected BiometryDataDTO biometryData;
	protected CardDataDTO cardData;
	protected CustomerDataDTO customerData;
	protected TransactionDataDTO transactionData;
	protected OperatorDataDTO operatorData;
	protected EmailSenderDTO emailSender;
	protected PrinterControlDTO printerControl;
}
