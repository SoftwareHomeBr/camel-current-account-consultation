package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OperatorDataDTOTest {

	@Test
	void testOperatorDataDTO() {
		assertThat(OperatorDataDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		var dto = new OperatorDataDTO();
		var dto2 = new OperatorDataDTO();
		var dto3 = new OperatorDataDTO();
		dto2.equals(dto);
		dto3.setTerminal("1");
		dto3.equals(dto);
		dto.hashCode();	
	}

}
