package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BiometryDataDTOTest {

	@Test
	void testBiometryDataDTO() {
		assertThat(BiometryDataDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		BiometryDataDTO dto = new BiometryDataDTO();
		BiometryDataDTO dto2 = new BiometryDataDTO();
		BiometryDataDTO dto3 = new BiometryDataDTO();
		dto2.equals(dto);
		dto3.setDna("DNA");
		dto3.equals(dto);
		dto.hashCode();
	}

}
