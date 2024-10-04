package br.com.santander.cxa.currentaccount.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContextDataTest {

	@Test
	void testContextData() {
		assertThat(ContextData.class, allOf(hasValidBeanConstructor(), hasValidGettersAndSetters(), hasValidBeanToString()));
	}

}
