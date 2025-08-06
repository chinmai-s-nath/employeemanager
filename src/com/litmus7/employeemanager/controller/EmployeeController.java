package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.dto.Employeedto;
import com.litmus7.employeemanager.util.Response;
import com.litmus7.employeemanager.util.textUtil;
import com.litmus7.employeemanager.services.*;
import java.util.*;

public class EmployeeController {
	
	Response response=new Response();
	
	private String INPUT_FILE;
	private String OUTPUT_FILE;
	services Services=new services();
	
	public EmployeeController(String INPUT_FILE, String OUTPUT_FILE){
		this.INPUT_FILE=INPUT_FILE;
		this.OUTPUT_FILE=OUTPUT_FILE;
	}

	public String getDataFromTextFile() {
		
		textUtil read=new textUtil();
		return read.readFile(INPUT_FILE);
	}

	//Phase2
	
	public String writeDataToCSV() {

		textUtil read=new textUtil();
		String str=read.readFile(INPUT_FILE);
		return (read.writeFile(OUTPUT_FILE,str.replace('$', ','),false));
	
	}
	
	//Phase 3
	
	public String writeInputToCSV(Employeedto employeeDataTransfer) {
		
		textUtil util=new textUtil();
		String details=employeeDataTransfer.id+","+employeeDataTransfer.fname+","+employeeDataTransfer.lname+","+employeeDataTransfer.phone+","+employeeDataTransfer.email+","+employeeDataTransfer.doj+",true";
		return (util.writeFile(OUTPUT_FILE,details,true));
		
	}
	
	public String createEmployeeController(Employeedto employeeDataTransfer) { 

		boolean result=Services.createEmployeeServices(employeeDataTransfer);
		return response.booleanResponse(result);
		
	}
	
	public List<String> getAllEmployeeController(){
		
		return Services.getAllEmployeeServices();
	}
	
	public Employeedto getEmployeeByIdController(int employeeId) {
		
		return Services.getEmployeeByIdServices(employeeId);
	}
	public String deleteEmployeeController(int employeeId) {
	
		return response.intResponse(Services.deleteEmployeeServices(employeeId));
	}
	
	public String updateEmployeeController(Employeedto employee) {
		return response.booleanResponse(Services.updateEmployeeServices(employee));
	}
}