package br.com.santander.cxa.currentaccount.model.dto.integration;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TapeRecordResponseDTOTest {

	@Test
	void testTapeRecordResponseDTO() {
		assertThat(TapeRecordResponseDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		var dto = new TapeRecordResponseDTO();
		var dto2 = new TapeRecordResponseDTO();
		var dto3 = new TapeRecordResponseDTO();
		dto2.equals(dto);
		dto3.setError(new ErrorDTO());
		dto3.equals(dto);
		dto.hashCode();	
	}

}
