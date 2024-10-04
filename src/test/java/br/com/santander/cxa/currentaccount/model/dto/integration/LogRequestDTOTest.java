package br.com.santander.cxa.currentaccount.model.dto.integration;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.object.HasToString;
import org.junit.jupiter.api.Test;

class LogRequestDTOTest {

	@Test
	void testGetBancoCliente() {
		assertThat(LogRequestDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		var dto = new LogRequestDTO();
		var dto2 = new LogRequestDTO();
		var dto3 = new LogRequestDTO();
		dto2.equals(dto);
		dto3.setBancoCliente(1L);
		dto3.equals(dto);
		dto.hashCode();	
	}

}
