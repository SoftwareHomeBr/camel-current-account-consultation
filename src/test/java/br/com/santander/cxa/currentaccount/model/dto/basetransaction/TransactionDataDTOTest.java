package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TransactionDataDTOTest {

	@Test
	void testTransactionDataDTO() {
		assertThat(TransactionDataDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		var dto = new TransactionDataDTO();
		var dto2 = new TransactionDataDTO();
		var dto3 = new TransactionDataDTO();
		dto2.equals(dto);
		dto3.setAttendanceNsu("1");
		dto3.equals(dto);
		dto.hashCode();	
	}

}
