package br.com.santander.cxa.currentaccount.model.dto;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.santander.cxa.currentaccount.model.ContextData;
import br.com.santander.cxa.currentaccount.model.dto.integration.ErrorDTO;
import br.com.santander.cxa.currentaccount.model.dto.integration.TapeRecordResponseDTO;

class CurrentAccountBalanceResponseDTOTest {

	@Test
	void testBuilder() {
		assertThat(CurrentAccountBalanceResponseDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		var dto = new CurrentAccountBalanceResponseDTO();
		var dto2 = new CurrentAccountBalanceResponseDTO();
		var dto3 = new CurrentAccountBalanceResponseDTO();
		dto2.equals(dto);
		dto3.setBlockedAmountExpiresTodayBalance("1");
		dto3.equals(dto);
		dto.hashCode();	
	}

}
