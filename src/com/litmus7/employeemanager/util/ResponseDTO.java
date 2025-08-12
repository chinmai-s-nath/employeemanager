package com.litmus7.employeemanager.util;

import java.util.List;

import com.litmus7.employeemanager.dto.Employeedto;

public class ResponseDTO {
	private boolean success;
	private String message;
	private Employeedto data;
	private List<String> list;
	public ResponseDTO(boolean success, String message, Employeedto data, List<String> list) {
		 this.success = success;
		this.message = message;
		this.data = data;
		this.list=list;
	 }
	public boolean isSuccess() { return success; }
	public String getMessage() { return message; }
	public Employeedto getData() { return data; }
	public List<String> getList() {return list;}
	
	
}