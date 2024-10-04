package br.com.santander.cxa.currentaccount.model.dto.integration;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.santander.cxa.currentaccount.model.dto.basetransaction.TransactionDataDTO;

class ContextDataDTOTest {

	@Test
	void testGetBank() {
		assertThat(ContextDataDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		var dto = new ContextDataDTO();
		var dto2 = new ContextDataDTO();
		var dto3 = new ContextDataDTO();
		dto2.equals(dto);
		dto3.setBank("0033");
		dto3.equals(dto);
		dto.hashCode();	
	}

}
