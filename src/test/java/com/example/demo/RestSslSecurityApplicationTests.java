package com.example.demo;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { AppConfig.class })
public class RestSslSecurityApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	RestTemplate restTemplate;

	@Test
	public void test() {
		String uri = "https://localhost:8443/helloWorld";
		 HttpHeaders headers = new HttpHeaders();
		 headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		 //headers.set(headerName, headerValue);
		 HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		    
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
		
		  System.out.println(result.getStatusCode());
		  System.out.println(result.getBody());
		 
	}

}
