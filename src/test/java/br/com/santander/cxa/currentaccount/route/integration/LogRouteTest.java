package br.com.santander.cxa.currentaccount.route.integration;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.spring.junit5.CamelSpringTest;
import org.junit.jupiter.api.Test;

@CamelSpringTest
class LogRouteTest {

	@Test
	public void testLogRoute() throws Exception {
	    CamelContext context = new DefaultCamelContext();
	    context.addRoutes(new LogRoute());

	    context.start();
	    Thread.sleep(2000);
	    context.stop();
	}
}
