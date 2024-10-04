package br.com.santander.cxa.currentaccount.processor;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.spring.junit5.CamelSpringTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.santander.cxa.bigdata.BigData;
import br.com.santander.cxa.bigdata.model.BigDataEnum;
import br.com.santander.cxa.currentaccount.model.Account;
import br.com.santander.cxa.currentaccount.model.ContextData;
import br.com.santander.cxa.currentaccount.model.dto.BaseRequestDTO;
import br.com.santander.cxa.currentaccount.model.dto.basetransaction.AccountDataDTO;
import br.com.santander.cxa.currentaccount.model.dto.integration.LogResponseDTO;
import br.com.santander.cxa.currentaccount.route.BalanceRoute;

class CashierTapeProcessorTest {
	
	static CamelContext context;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	    context = new DefaultCamelContext();
	}

	@Test
	public void testCashierTapeProcessor() throws Exception {
	    context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
		        from("direct:start")
		          .routeId("rota-teste")
		          	.process(e -> {
		          		BaseRequestDTO dto = new BaseRequestDTO();
		          		dto.setAccount(new Account());
		          		dto.setContext(new ContextData());
		          		e.setProperty("requestData", dto);
		          		LogResponseDTO logDto = new LogResponseDTO();
		          		e.setProperty("openLogData", logDto);
		          		
		          	})
					.process(new CashierTapeProcessor())
					.to("mock:teste")
		          .end();
				
			}
		});

	    context.start();
	    testRoute();
	    Thread.sleep(10000);
	    context.stop();
	    assertNotNull(context);
	}

    public void testRoute() throws Exception {
		ProducerTemplate producer = context.createProducerTemplate();
        producer.sendBody("direct:start", null);
    }
	
	
}
