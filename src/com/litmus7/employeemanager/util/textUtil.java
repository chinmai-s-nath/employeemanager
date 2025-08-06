package com.litmus7.employeemanager.util;

import com.litmus7.employeemanager.exception.*;

public class textUtil {

	public String readFile(String filepath) {
		
		exceptionHandle fileHandle=new exceptionHandle();
		return fileHandle.readFileExceptionHandle(filepath);
		
	}
	
	public String writeFile(String filepath, String data, boolean b) {
		
		exceptionHandle fileHandle=new exceptionHandle();
		return fileHandle.writeFileExceptionHandle(filepath,data,b);
	}
	
}
