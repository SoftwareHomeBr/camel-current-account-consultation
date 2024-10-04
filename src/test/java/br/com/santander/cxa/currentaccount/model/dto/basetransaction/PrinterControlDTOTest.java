package br.com.santander.cxa.currentaccount.model.dto.basetransaction;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PrinterControlDTOTest {

	@Test
	void testPrinterControlDTO() {
		assertThat(PrinterControlDTO.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
		var dto = new PrinterControlDTO();
		var dto2 = new PrinterControlDTO();
		var dto3 = new PrinterControlDTO();
		dto2.equals(dto);
		dto3.setNumberOfPrintCopies("1");
		dto3.equals(dto);
		dto.hashCode();	
	}

}
