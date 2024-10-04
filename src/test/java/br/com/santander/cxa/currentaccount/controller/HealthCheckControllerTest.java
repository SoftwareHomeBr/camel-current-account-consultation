package br.com.santander.cxa.currentaccount.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HealthCheckControllerTest extends SwaggerController {

	@Test
	void testToString() {
		HealthCheckController controller = new HealthCheckController();
		controller.check();
		
		assertNotNull(controller);
		
	}

}
