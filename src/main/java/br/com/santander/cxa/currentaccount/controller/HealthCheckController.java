package br.com.santander.cxa.currentaccount.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthCheckController {
	
	@GetMapping(value = "/health")
	public ResponseEntity<String> check() {
		return new ResponseEntity<>("UP", HttpStatus.OK);
	}
}
