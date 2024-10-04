package br.com.santander.cxa.currentaccount.model.dto.integration;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TapeRecordRequestDTOTest {

	@Test
	void testTapeRecordRequestDTO() {
		assertThat(TapeRecordRequestDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		var dto = new TapeRecordRequestDTO();
		var dto2 = new TapeRecordRequestDTO();
		var dto3 = new TapeRecordRequestDTO();
		dto2.equals(dto);
		dto3.setBankBranchCode("0033");
		dto3.equals(dto);
		dto.hashCode();	
	}

}
