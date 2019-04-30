/**
 * 
 */
package com.example.demo;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
/**
 * @author MG
 *
 */

public class AppConfig {

	@Bean
	public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
	    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
	    
	    SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
	   
	    
		/*
		 * KeyStore clientStore = KeyStore.getInstance("PKCS12"); clientStore.load(new
		 * FileInputStream("/path/to/certfile"), "certpassword".toCharArray());
		 * 
		 * SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
		 * sslContextBuilder.useProtocol("TLS");
		 * sslContextBuilder.loadKeyMaterial(clientStore, "certpassword".toCharArray());
		 * sslContextBuilder.loadTrustMaterial(new TrustSelfSignedStrategy());
		 */
	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
	    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
	   
	    
	    HttpGet httpget = new HttpGet("https://localhost:8443/helloWorld");
	    String auth = "user" + ":" + "f19c048c-bbe4-42bd-a2c5-6f0f824bea3a";
	    byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.ISO_8859_1));
	    String authHeader = "Basic " + new String(encodedAuth);
	    httpget.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
	    try {
			HttpResponse response = httpClient.execute(httpget);
			String apiOutput = EntityUtils.toString(response.getEntity());
			System.out.println(">>>>>>>>>>>>" + apiOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    HttpComponentsClientHttpRequestFactory requestFactory =  new HttpComponentsClientHttpRequestFactory();
	    requestFactory.setHttpClient(httpClient);
	    requestFactory.setConnectTimeout(10000);
	    requestFactory.setConnectionRequestTimeout(10000);
	    
	    RestTemplate restTemplate = new RestTemplate(requestFactory);
	    return restTemplate;
	 }
}
