package br.com.santander.cxa.currentaccount.config;

import org.apache.camel.processor.errorhandler.RedeliveryPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResilienceConfig {

	@Bean
	public RedeliveryPolicy getRedeliveryPolicy() {
		RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
		redeliveryPolicy.setMaximumRedeliveries(3);
		redeliveryPolicy.setMaximumRedeliveryDelay(500);
		redeliveryPolicy.setRedeliveryDelay(500);
		return redeliveryPolicy;
	}

}
