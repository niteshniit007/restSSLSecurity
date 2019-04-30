package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestSSLSecurityController {

	@GetMapping("/helloWorld")
	public String helloWorld() {
		
		/*
		 * C:\Users\MG>keytool -genkey -alias selfsigned_localhost_sslserver -keyalg RSA -keysize 2048 -validity 700 -keypass changeit -storepass changeit -key
		store ssl-server.jks
		 * */
		return "hello world";
	}
	
}
