package com.faq.security;

import com.faq.SpringApplicationContext;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 100000;
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	// public static final String TOKEN_SECRET = "sadadasdad";
	public static String getSecretToken() {
		ApplicationProperties appProp = (ApplicationProperties) SpringApplicationContext.getBean("applicationProperties");
		return appProp.getSecretToken();
	}
}
