package br.com.santander.cxa.currentaccount.model.dto;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.santander.cxa.currentaccount.model.ContextData;

class BalanceDTOTest {

	@Test
	void testBalanceDTO() {
		assertThat(BalanceDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		var dto = new BalanceDTO(1, "name");
		var dto2 = new BalanceDTO(1, "name");
		dto.equals(dto2);
		dto.hashCode();
		
	}

}
