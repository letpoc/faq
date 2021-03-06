package com.faq.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

	@Autowired
	private Environment env;
	
	public String getSecretToken() {
		return env.getProperty("secretToken");
	}
	
	public int getExpirationTime() {
		return Integer.parseInt((env.getProperty("expirationTime")));
	}
	
	
}
