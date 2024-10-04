package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CardDataDTOTest {

	@Test
	void testCardDataDTO() {
		assertThat(CardDataDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		CardDataDTO dto = new CardDataDTO();
		CardDataDTO dto2 = new CardDataDTO();
		CardDataDTO dto3 = new CardDataDTO();
		dto2.equals(dto);
		dto3.setCardVia("DNA");
		dto3.equals(dto);
		dto.hashCode();	}

}
