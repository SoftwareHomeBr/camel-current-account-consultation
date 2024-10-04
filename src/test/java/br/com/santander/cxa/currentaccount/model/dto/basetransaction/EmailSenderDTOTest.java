package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailSenderDTOTest {

	@Test
	void testEmailSenderDTO() {
		assertThat(EmailSenderDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		EmailSenderDTO dto = new EmailSenderDTO();
		EmailSenderDTO dto2 = new EmailSenderDTO();
		EmailSenderDTO dto3 = new EmailSenderDTO();
		dto2.equals(dto);
		dto3.setEmail(new String[] {"name"});
		dto3.equals(dto);
		dto.hashCode();	
	}

}
