package br.com.santander.cxa.currentaccount.model.dto.integration;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CurrentAccountBalanceRequestDTOTest {

	@Test
	void testCurrentAccountBalanceRequestDTO() {
		assertThat(CurrentAccountBalanceRequestDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		var dto = new CurrentAccountBalanceRequestDTO();
		var dto2 = new CurrentAccountBalanceRequestDTO();
		var dto3 = new CurrentAccountBalanceRequestDTO();
		dto2.equals(dto);
		dto3.setCodigoCtaCliente("1");
		dto3.equals(dto);
		dto.hashCode();	
	}

}
