package br.com.santander.cxa.currentaccount.model.dto.integration;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ErrorDTOTest {

	@Test
	void testErrorDTO() {
		assertThat(ErrorDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		var dto = new ErrorDTO();
		var dto2 = new ErrorDTO();
		var dto3 = new ErrorDTO();
		dto2.equals(dto);
		dto3.setDescription("error");
		dto3.equals(dto);
		dto.hashCode();	
	}

}
