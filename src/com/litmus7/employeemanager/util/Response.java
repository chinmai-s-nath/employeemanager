package com.litmus7.employeemanager.util;

public class Response {
	
	public String booleanResponse(boolean bool) {
		if (bool==true)
			return "Operation successful\n";
		else {
			return "Error occured";
		}
	}
	
	public String intResponse(int result) {
		if (result>0)
			return "Operation successful with "+result+" rows affected. . . \n";
		else {
			return "Error occured";
		}
	}
}