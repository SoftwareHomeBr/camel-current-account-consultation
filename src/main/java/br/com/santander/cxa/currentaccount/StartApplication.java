package br.com.santander.cxa.currentaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.santander.bhs.camel.embeddedcrypto.annotation.EnableIntegrationEmbeddedCrypto;
import br.com.santander.bhs.integration.annotation.EnableIntegrationCore;

@EnableIntegrationCore
@EnableIntegrationEmbeddedCrypto
@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
}
