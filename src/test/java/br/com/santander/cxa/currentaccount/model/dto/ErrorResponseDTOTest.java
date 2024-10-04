package br.com.santander.cxa.currentaccount.model.dto;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.com.santander.cxa.currentaccount.model.ContextData;
import br.com.santander.cxa.currentaccount.model.dto.basetransaction.ErrorDTO;

class ErrorResponseDTOTest {

	@Test
	void testErrorResponseDTO() {
		assertThat(ErrorResponseDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		var dto = new ErrorDTO();
		var dto2 = new ErrorDTO();
		var dto3 = new ErrorDTO();
		dto2.equals(dto);
		dto3.setDescription("error");
		dto3.equals(dto);
		dto.hashCode();	
	}

}
