package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.constants.sqlConstants;
import com.litmus7.employeemanager.dto.Employeedto;
import com.litmus7.employeemanager.util.Response;
import com.litmus7.employeemanager.util.ResponseDTO;
import com.litmus7.employeemanager.util.Validation;
import com.litmus7.employeemanager.util.textUtil;
import com.litmus7.employeemanager.services.*;
import com.litmus7.employeemanager.exception.*;
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
	
	public ResponseDTO createEmployeeController(Employeedto employeeDataTransfer) { 

		if (valid.checkStringEmpty(employeeDataTransfer.fname)==true && valid.checkStringEmpty(employeeDataTransfer.phone)==true && valid.checkStringEmpty(employeeDataTransfer.email)==true  &&valid.checkEmail(employeeDataTransfer.email)==true && valid.checkPhoneNumber(employeeDataTransfer.phone)==true )
		{
		try {
			boolean result=Services.createEmployeeServices(employeeDataTransfer);
			 return new ResponseDTO(result, "Employee Created with id: "+employeeDataTransfer.employeeId, null, null);
			 } 
		catch (EmployeeNotCreated e) {
			 return new ResponseDTO(false, e.getMessage(), null,null);
			 }  
		catch (EmployeeServiceException e) {
			 return new ResponseDTO(false, "Service Error: " + e.getMessage(), null,null);
			 } 
		catch (Exception e) {
			 return new ResponseDTO(false, "System error: " + e.getMessage(), null,null);
			 }
		}
		else {
			return new ResponseDTO(false, "Invalid information", null,null);
		}
	}
	
	public ResponseDTO getAllEmployeeController(){
	
		try {
			 List<String> employees = Services.getAllEmployeeServices();
			 return new ResponseDTO(true, "Employee found", null, employees);
			 } 
		catch (EmployeeNotFoundException e) {
			 return new ResponseDTO(false, e.getMessage(), null,null);
			 } 
		catch (EmployeeServiceException e) {
			 return new ResponseDTO(false, "Service Error: " + e.getMessage(), null,null);
			 } 
		catch (Exception e) {
			 return new ResponseDTO(false, "System error: " + e.getMessage(), null,null);
			 }
		
	}

	
	public ResponseDTO getEmployeeByIdController(int employeeId) {
		
		try {
			 Employeedto employee = Services.getEmployeeByIdServices(employeeId);
			 return new ResponseDTO(true, "Employee found", employee,null);
			 } 
		catch (EmployeeNotFoundException e) {
			 return new ResponseDTO(false, e.getMessage(), null,null);
			 } 
		catch (EmployeeServiceException e) {
			 return new ResponseDTO(false, "Service Error: " + e.getMessage(), null,null);
			 } 
		catch (Exception e) {
			 return new ResponseDTO(false, "System error: " + e.getMessage(), null,null);
			 }
		
	}
	public ResponseDTO deleteEmployeeController(int employeeId) {
	
		try {
			 int data =Services.deleteEmployeeServices(employeeId);
			 return new ResponseDTO(true, "Employees deleted: "+data, null,null);
			 } 
		catch (EmployeeNotFoundException e) {
			 return new ResponseDTO(false, e.getMessage(), null,null);
			 } 
		catch (EmployeeServiceException e) {
			 return new ResponseDTO(false, "Service Error: " + e.getMessage(), null,null);
			 } 
		catch (Exception e) {
			 return new ResponseDTO(false, "System error: " + e.getMessage(), null,null);
			 }
	}
	
	public ResponseDTO updateEmployeeController(Employeedto employee) {
		try {
			 boolean data =Services.updateEmployeeServices(employee);
			 return new ResponseDTO(data, "Employee details updated of id: "+employee.employeeId, null,null);
			 } 
		catch (EmployeeNotFoundException e) {
			 return new ResponseDTO(false, e.getMessage(), null,null);
			 } 
		catch (EmployeeServiceException e) {
			 return new ResponseDTO(false, "Service Error: " + e.getMessage(), null,null);
			 } 
		catch (Exception e) {
			 return new ResponseDTO(false, "System error: " + e.getMessage(), null,null);
			 }
	}
}