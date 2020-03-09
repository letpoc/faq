package com.faq.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchStringPattern {
	
	private static final String EMAIL_REGEXP_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
	private static final String PASSWORD_REGEXP_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	
	public static boolean forEmail(String email) {		
		Pattern pattern = Pattern.compile(EMAIL_REGEXP_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public static boolean forPassword(String pwd) {
		Pattern pattern = Pattern.compile(PASSWORD_REGEXP_PATTERN);
		Matcher matcher = pattern.matcher(pwd);
		return matcher.matches();
	}

}
