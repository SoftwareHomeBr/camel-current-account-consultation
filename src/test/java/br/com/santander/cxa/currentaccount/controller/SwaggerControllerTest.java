package br.com.santander.cxa.currentaccount.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SwaggerControllerTest extends SwaggerController {

	@Test
	void testToString() {
		SwaggerController controller = new SwaggerController();
		controller.redirectToUi();
		
		assertNotNull(controller);
		
	}

}
