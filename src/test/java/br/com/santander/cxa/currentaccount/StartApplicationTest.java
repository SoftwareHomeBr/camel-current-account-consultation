package br.com.santander.cxa.currentaccount;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@EnableAutoConfiguration
@ActiveProfiles("local")
class StartApplicationTest {

	@Test
	void testMain() {
		System.setProperty("server.servlet.context-path", "/app");
		StartApplication.main(new String[] {});
	}

}
