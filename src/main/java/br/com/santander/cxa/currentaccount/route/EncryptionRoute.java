package br.com.santander.cxa.currentaccount.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EncryptionRoute extends RouteBuilder {

	@Override
    public void configure() throws Exception {
		from("direct:validate-decrypt")
		.routeId("validate-decrypt")
			.setProperty("payload", body())
			.choice().when(simple("${header.X-EncryptedObject} != null"))
				.setProperty("X-EncryptedObject", header("X-EncryptedObject"))
				.setProperty("integrationheader", header("integrationheader"))
				.to("dlb:decrypt")
			.endChoice()
		.end()
		.setProperty("payload", body());
		
		from("direct:validate-encrypt")
		.routeId("validate-encrypt")
			.choice().when(simple("${header.X-EncryptedObject} != null"))
				.setHeader("X-EncryptedObject", exchangeProperty("X-EncryptedObject"))
				.setHeader("integrationheader", exchangeProperty("integrationheader"))
				.to("dlb:encrypt")
			.endChoice()
		.end()
		.setProperty("response", body());
    }
}
