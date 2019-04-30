package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class RestSslSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestSslSecurityApplication.class, args);
	}

}
