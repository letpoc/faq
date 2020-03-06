package com.faq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FaqApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaqApplication.class, args);	
		System.out.println("Hello World 1");
	}

	// Add this bean to encrypt the password -->
	@Bean BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
}
