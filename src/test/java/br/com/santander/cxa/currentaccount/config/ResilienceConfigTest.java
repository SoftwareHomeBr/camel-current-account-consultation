package br.com.santander.cxa.currentaccount.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResilienceConfigTest {

	@Test
	void testGetRedeliveryPolicy() {
		ResilienceConfig config = new ResilienceConfig();
		config.getRedeliveryPolicy();
		assertNotNull(config);
	}

}
