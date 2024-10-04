package br.com.santander.cxa.currentaccount.route;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.spring.junit5.CamelSpringTest;
import org.junit.jupiter.api.Test;

import br.com.santander.cxa.bigdata.BigData;
import br.com.santander.cxa.bigdata.model.BigDataEnum;

@CamelSpringTest
public class BalanceRouteTest {

	@Test
	public void testBalanceRoute() throws Exception {
	    CamelContext context = new DefaultCamelContext();
	    context.addRoutes(new BalanceRoute());

	    context.start();
	    Thread.sleep(1000);
	    context.stop();
	}

}
