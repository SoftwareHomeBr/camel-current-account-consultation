package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerDataDTOTest {

	@Test
	void testCustomerDataDTO() {
		assertThat(CustomerDataDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		CustomerDataDTO dto = new CustomerDataDTO();
		CustomerDataDTO dto2 = new CustomerDataDTO();
		CustomerDataDTO dto3 = new CustomerDataDTO();
		dto2.equals(dto);
		dto3.setName("name");
		dto3.equals(dto);
		dto.hashCode();	
	}

}
