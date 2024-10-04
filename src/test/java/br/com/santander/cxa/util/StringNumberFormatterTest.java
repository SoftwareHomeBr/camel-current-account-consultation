package br.com.santander.cxa.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.santander.cxa.currentaccount.model.dto.CurrentAccountBalanceResponseDTO;
import br.com.santander.cxa.currentaccount.model.dto.integration.CurrentAccountBalanceRequestDTO;

class StringNumberFormatterTest {


	@Test
	void testFormatObject() {
		var dto = new CurrentAccountBalanceResponseDTO();
		dto.setAccountingBalance("0");
		dto.setAditionalIOF("0");
		dto.setAvailableBalance("0");
		dto.setBlockedAmountExpiresTodayBalance("0");
		dto.setContractualInterestRateOnOutstandingBalance("0");
		dto.setCustomerName("teste");
		dto.setFortyEightHoursBlockedBalance("0");
		dto.setInterestRateDueClosedBalance("0");
		dto.setLatePaymentInterest("0");
		dto.setOutstandingBalanceAccruedIOF("0");
		dto.setSpecialCheckLimit("0");
		dto.setTotalBlockedBalance("0");
		dto.setTotalDueCharges("0");
		
		StringNumberFormatter.formatObject(dto);
	}

}
