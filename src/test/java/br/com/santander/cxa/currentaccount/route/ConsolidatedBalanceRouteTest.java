package br.com.santander.cxa.currentaccount.route;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Test;

class ConsolidatedBalanceRouteTest {

	@Test
	public void testAddInformation() throws Exception {
	    CamelContext context = new DefaultCamelContext();
	    context.addRoutes(new ConsolidatedBalanceRoute());

	    context.start();
	    Thread.sleep(1000);
	    context.stop();
	}

}
