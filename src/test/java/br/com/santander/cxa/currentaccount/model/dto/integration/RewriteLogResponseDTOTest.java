package br.com.santander.cxa.currentaccount.model.dto.integration;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RewriteLogResponseDTOTest {

	@Test
	void testRewriteLogResponseDTO() {
		assertThat(RewriteLogResponseDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
		var dto = new RewriteLogResponseDTO();
		var dto2 = new RewriteLogResponseDTO();
		var dto3 = new RewriteLogResponseDTO();
		dto2.equals(dto);
		dto3.setNsu(1L);
		dto3.equals(dto);
		dto.hashCode();	
	}

}
