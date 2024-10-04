package br.com.santander.cxa.currentaccount.processor;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.santander.cxa.currentaccount.model.Account;
import br.com.santander.cxa.currentaccount.model.ContextData;
import br.com.santander.cxa.currentaccount.model.dto.BaseRequestDTO;
import br.com.santander.cxa.currentaccount.model.dto.integration.LogResponseDTO;
import br.com.santander.cxa.security.EncryptUtils;

class FormatResponseFieldsAndPrepareForEncryptionProcessorTest {
	
	static CamelContext context;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	    context = new DefaultCamelContext();
	}

	@Test
	public void testDecryptTurboResponseProcessor() throws Exception {
	    context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
		        from("direct:start")
		          .routeId("rota-teste")
		          	.process(e -> {
		          		BaseRequestDTO dto = new BaseRequestDTO();
		          		Account account = new Account();
		          		account.setBankCode("33");
		          		account.setBranchCode("409");
		          		account.setAccountNumber("1");
		          		dto.setAccount(account);
		          		dto.setContext(new ContextData());
		          		e.setProperty("requestData", dto);
						String encryptedObject = "rO0ABXNyAB9jb20uYWx0ZWMuYnNici5hcHAuZGwuZWNjLkRMRUNDKcooZ7jn/poCAAB4cgAiY29tLmFsdGVjLmJzYnIuYXBwLmRsLmVjYy5ETENyeXB0b0hyPt8/TNWSAwAGWgAGY2xpZW50QgADeG9yTAADa2V5dAASTGphdmEvbGFuZy9TdHJpbmc7TAAHa2V5UGFpcnQAF0xqYXZhL3NlY3VyaXR5L0tleVBhaXI7TAAJcHVibGljS2V5cQB+AAJMAAZyYW5kb210ABxMamF2YS9zZWN1cml0eS9TZWN1cmVSYW5kb207eHB3LAAAACeDsLC1sbO0truztbe2trW2u7a2tbK3sLO1sbWxtru3t7C1sLe7trC6eA==";
		          		e.setProperty("X-EncryptedObject", encryptedObject);

		        		String objectJson = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("testB402response.json"), StandardCharsets.UTF_8);
		        		JSONObject json = new JSONObject(objectJson);
		        		json = EncryptUtils.decryptJsonObject(json, encryptedObject, true, true);
		        		e.getMessage().setBody(new ByteArrayInputStream(json.toString().getBytes()));
		          		
		          	})
					.unmarshal().json(JsonLibrary.Gson)
					.setProperty("respostaTurbo", simple("${body}"))
					// Realiza transformação JSON dos formatos AEA para o payload de retorno para o front
					.to("jolt:jolt.balance.spec")
					.process(new FormatResponseFieldsAndPrepareForEncryptionProcessor())
					.to("mock:teste")
		          .end();
				
			}
		});

    	context.setStreamCaching(true);

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
