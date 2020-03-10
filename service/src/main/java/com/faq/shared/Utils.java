package com.faq.shared;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Utils {

	private static final String EMAIL_REGEXP_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private static final String PASSWORD_REGEXP_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static boolean CheckEmailPattern(String email) {
		Pattern pattern = Pattern.compile(EMAIL_REGEXP_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean CheckPasswordPattern(String pwd) {
		Pattern pattern = Pattern.compile(PASSWORD_REGEXP_PATTERN);
		Matcher matcher = pattern.matcher(pwd);
		return matcher.matches();
	}

	public static int GenerateRandomNumber(int min, int max) {
		return (int)(Math.random() * (max - min + 1) + min);		
	}

	public static String generateUserId(int length) {
		return generateRandomString(length);
	}

	public static String generateRandomString(int length) {
		StringBuilder stringBuilder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			stringBuilder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(stringBuilder);
	}

}
