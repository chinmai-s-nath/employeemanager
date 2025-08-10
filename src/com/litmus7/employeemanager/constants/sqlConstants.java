package com.litmus7.employeemanager.constants;

public class sqlConstants {
	
	public static String firstName() {
		return "first_name";
	}
	
	public static String lastName() {
		return "last_name";
	}
	
	public static String Phone() {
		return "mobile_number";
	}
	
	public static String Email() {
		return "email_address";
	}
	
	public static String joiningDate() {
		return "joining_date";
	}
	
	public static String activeStatus() {
		return "active_status";
	}
	
	public static String employeeId() {
		return "id";
	}
	public static String successCode() {
		return "Success";
	}
	public static String dbConnectionErrorCode() {
		return "503";   //service unavailable
	}
	public static String itemNotFound() {
		return "404 Item not Found";
	}
	
	public static String fetchDataById() {
		return "select id,first_name,last_name, mobile_number, email_address, joining_date,active_status from employee where id=?";
	}
	
	public static String errorCode(){
		return "Error_code";
	}
}


