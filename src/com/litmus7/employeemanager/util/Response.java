package com.litmus7.employeemanager.util;

import com.litmus7.employeemanager.constants.*;
import java.sql.SQLException;

public class Response {
	
	public String booleanResponse(boolean bool) {
		if (bool==true)
			return "Operation successful\n";
		else {
			return "Error occured";
		}
	}
	
	public String sqlBooleanResponse(boolean bool) {
		if (bool==true)
			return sqlConstants.successCode();
		else {
			return sqlConstants.dbConnectionErrorCode();
		}
	}
	
	public String intResponse(int result) {
		if (result>0)
			return "Operation successful with "+result+" rows affected. . . \n";
		else {
			return sqlConstants.itemNotFound();
		}
	}
	public String sqlError(Exception e) {
		if (e instanceof SQLException ) {
			return sqlConstants.dbConnectionErrorCode();
		}
		else {
			return sqlConstants.errorCode();
		}
	}
	
	public String dataMismatch() {
		return "Data entered is not correct";
	}
}