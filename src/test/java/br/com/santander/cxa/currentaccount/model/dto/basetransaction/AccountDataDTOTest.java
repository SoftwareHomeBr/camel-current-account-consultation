package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.santander.cxa.currentaccount.model.ContextData;

class AccountDataDTOTest {

	@Test
	void testAccountDataDTO() {
		assertThat(AccountDataDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		AccountDataDTO dto = new AccountDataDTO();
		AccountDataDTO dto2 = new AccountDataDTO();
		AccountDataDTO dto3 = new AccountDataDTO();
		dto2.equals(dto);
		dto3.setBankCode("0033");
		dto3.equals(dto);
		dto.hashCode();
	}

}
