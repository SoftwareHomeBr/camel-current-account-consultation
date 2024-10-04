package br.com.santander.cxa.currentaccount.model.dto;

import br.com.santander.bhs.camel.embeddedcrypto.annotation.DlbCryptoField;
import br.com.santander.cxa.annotations.CurrencyValue;
import br.com.santander.cxa.currentaccount.model.ContextData;
import br.com.santander.cxa.currentaccount.model.dto.basetransaction.TransactionDataDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class CurrentAccountBalanceResponseDTO  
{
	@CurrencyValue
	@DlbCryptoField
	private String availableBalance;
	@CurrencyValue
	@DlbCryptoField
	private String specialCheckLimit;
	@CurrencyValue
	@DlbCryptoField
	private String totalBlockedBalance;
	@CurrencyValue
	@DlbCryptoField
	private String accountingBalance;
	@CurrencyValue
	@DlbCryptoField
	private String blockedAmountExpiresTodayBalance;
	@CurrencyValue
	@DlbCryptoField
	private String fortyEightHoursBlockedBalance;
	@CurrencyValue
	@DlbCryptoField
	private String totalDueCharges;
	@CurrencyValue
	@DlbCryptoField
	private String contractualInterestRateOnOutstandingBalance;
	@CurrencyValue
	@DlbCryptoField
	private String outstandingBalanceAccruedIOF;
	@CurrencyValue
	@DlbCryptoField
	private String interestRateDueClosedBalance;
	@CurrencyValue
	@DlbCryptoField
	private String latePaymentInterest;
	@CurrencyValue
	@DlbCryptoField
	private String aditionalIOF;
	@DlbCryptoField
	private String customerName;
}
