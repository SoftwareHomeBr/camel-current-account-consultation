package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AuthorizationDataDTOTest {

	@Test
	void testAuthorizationDataDTO() {
		assertThat(AuthorizationDataDTO.class, allOf(hasValidBeanConstructor()));
		AuthorizationDataDTO dto = new AuthorizationDataDTO();
		AuthorizationDataDTO dto2 = new AuthorizationDataDTO();
		AuthorizationDataDTO dto3 = new AuthorizationDataDTO();
		dto2.equals(dto);
		dto3.setCardIdentified("1");
		dto3.equals(dto);
		dto.hashCode();
	}

}
