package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.constants.sqlConstants;
import com.litmus7.employeemanager.dto.Employeedto;
import com.litmus7.employeemanager.util.Response;
import com.litmus7.employeemanager.util.Validation;
import com.litmus7.employeemanager.util.textUtil;
import com.litmus7.employeemanager.services.*;
import java.util.*;

public class EmployeeController {
	private Response response=new Response();
	private textUtil util=new textUtil();
	private Validation valid=new Validation();
	
	
	private String INPUT_FILE;
	private String OUTPUT_FILE;
	private services Services=new services();
	
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
		if (valid.checkStringEmpty(employeeDataTransfer.fname)==true && valid.checkStringEmpty(employeeDataTransfer.phone)==true && valid.checkStringEmpty(employeeDataTransfer.email)==true  &&valid.checkEmail(employeeDataTransfer.email)==true && valid.checkPhoneNumber(employeeDataTransfer.phone)==true )
		{
		String details=employeeDataTransfer.employeeId+","+employeeDataTransfer.fname+","+employeeDataTransfer.lname+","+employeeDataTransfer.phone+","+employeeDataTransfer.email+","+employeeDataTransfer.doj+",true";
		
		return (util.writeFile(OUTPUT_FILE,details,true));
		}
		else {
			return response.dataMismatch();
		}
	}
	
	public String createEmployeeController(Employeedto employeeDataTransfer) { 
		
		if (valid.checkStringEmpty(employeeDataTransfer.fname)==true && valid.checkStringEmpty(employeeDataTransfer.phone)==true && valid.checkStringEmpty(employeeDataTransfer.email)==true  &&valid.checkEmail(employeeDataTransfer.email)==true && valid.checkPhoneNumber(employeeDataTransfer.phone)==true )
		{
			boolean result=Services.createEmployeeServices(employeeDataTransfer);
			return response.sqlBooleanResponse(result);
		}
		
		else {
			
			return response.dataMismatch();
		}
	}
	
	public List<String> getAllEmployeeController(){
		List<String>result=new ArrayList<>();
		
		if (Services.getAllEmployeeServices()!=null) {
			return Services.getAllEmployeeServices();
		}
		else {
			 result.add("No item found");
		}
		
		return result;
	}
	
	public String getEmployeeByIdController(int employeeId) {
		
		Employeedto employeeDTO= Services.getEmployeeByIdServices(employeeId);
		if (employeeDTO!=null) {
			return employeeDTO.EmployeeDTODisplay();
		}
		else {
			return sqlConstants.itemNotFound();
		}
		
	}
	public String deleteEmployeeController(int employeeId) {
	
		try {
			return response.intResponse(Services.deleteEmployeeServices(employeeId));
		} catch (Exception e) {
			return sqlConstants.itemNotFound();
		}
	}
	
	public String updateEmployeeController(Employeedto employee) {
		try {
			return response.booleanResponse(Services.updateEmployeeServices(employee));
		} catch (Exception e) {
			return sqlConstants.errorCode();
		}
	}
}