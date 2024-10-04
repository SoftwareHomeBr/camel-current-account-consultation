package br.com.santander.cxa.util;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.MessageHelper;
import org.apache.camel.test.spring.junit5.CamelSpringTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.santander.cxa.bigdata.BigData;
import br.com.santander.cxa.bigdata.model.BigDataEnum;

@CamelSpringTest
class CamelHelperTest {
	static CamelContext context;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	    context = new DefaultCamelContext();
	}

	@Test
	public void testCashierTapeRoute() throws Exception {
	    context.addRoutes(createRouteBuilder());

	    context.start();
	    testRoute();
	    Thread.sleep(1000);
	    context.stop();
	}
	  protected RouteBuilder createRouteBuilder() throws Exception 
	  {
	    return new RouteBuilder() 
	    {
	      public void configure() {
	        from("direct:start")
	          .routeId("direct:start")
				.process(e -> {
					String dump = MessageHelper.dumpMessageHistoryStacktrace(e, CamelHelper.getOrCreateExchangeFormatter(e.getContext()), false);				})
	          .to("mock:result")
	          .end();
	      }
	    };
	
	  }	
	    public void testRoute() throws Exception {
			ProducerTemplate producer = context.createProducerTemplate();
	        producer.sendBody("direct:start", null);
	    }
	
}
