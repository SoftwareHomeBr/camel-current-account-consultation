package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BaseTransactionResponseDTOTest {

	@Test
	void testBaseTransactionResponseDTO() {
		assertThat(BaseTransactionResponseDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		BaseTransactionResponseDTO dto = new BaseTransactionResponseDTO();
		BaseTransactionResponseDTO dto2 = new BaseTransactionResponseDTO();
		BaseTransactionResponseDTO dto3 = new BaseTransactionResponseDTO();
		dto2.equals(dto);
		dto3.setTransactionData(new TransactionDataDTO());
		dto3.equals(dto);
		dto.hashCode();
	}

}
