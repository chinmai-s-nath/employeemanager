package com.litmus7.employeemanager.util;

import java.util.regex.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;

public class Validation {
	
	public boolean checkStringEmpty(String input) {
		if (input.length()>0) {
			return true;
		}
		else
			return false;
	}
	
	public boolean checkPhoneNumber(String input) {
		if (input.length()>=10) {
			return true;
		}
		else
			return false;
	}
	
	public boolean checkEmail(String input) {
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern email=Pattern.compile(emailRegex);
		Matcher match=email.matcher(input);
		
		return match.matches();
	}
	
	public boolean checkValidDate(String date) {
		try {
			LocalTime.parse(date, DateTimeFormatter.ofPattern("yyyy-mm-dd"));
			return true;
		}catch (DateTimeParseException e) {
			return false;
		}
	}
	
}