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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeController {
	private Response response=new Response();
	private textUtil util=new textUtil();
	private Validation valid=new Validation();
	
	private static final Logger logger=LogManager.getLogger(EmployeeController.class);
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
			logger.info(sqlConstants.BadRequest()+" :Invalid user input");
			return response.dataMismatch();
		}
	}
	
	public ResponseDTO createEmployeeController(Employeedto employeeDataTransfer) { 

		if (valid.checkStringEmpty(employeeDataTransfer.fname)==true && valid.checkStringEmpty(employeeDataTransfer.phone)==true && valid.checkStringEmpty(employeeDataTransfer.email)==true  &&valid.checkEmail(employeeDataTransfer.email)==true && valid.checkPhoneNumber(employeeDataTransfer.phone)==true )
		{
		try {
			boolean result=Services.createEmployeeServices(employeeDataTransfer);
			logger.info(sqlConstants.successCode()+" :Employee created with id: "+employeeDataTransfer.employeeId);
			 return new ResponseDTO(result, "Employee Created with id: "+employeeDataTransfer.employeeId, null, null);
			 
			 } 
		catch (EmployeeNotCreated e) {
			logger.error(sqlConstants.BadRequest()+" :Employee not created");
			 return new ResponseDTO(false, e.getMessage(), null,null);
			 }  
		catch (EmployeeServiceException e) {
			logger.error(sqlConstants.BadRequest()+" :Service layer error");
			 return new ResponseDTO(false, sqlConstants.BadRequest()+" :Service Error: " + e.getMessage(), null,null);
			 } 
		catch (Exception e) {
			 return new ResponseDTO(false, "System error: " + e.getMessage(), null,null);
			 }
		}
		else {
			logger.info(sqlConstants.BadRequest()+" :Invalid user input");
			return new ResponseDTO(false, "Invalid information", null,null);
		}
	}
	
	public ResponseDTO getAllEmployeeController(){
	
		try {
			 List<String> employees = Services.getAllEmployeeServices();
			logger.info(sqlConstants.successCode()+" :Retrieved employee datas");
			 return new ResponseDTO(true, "Employee found", null, employees);
			 } 
		catch (EmployeeNotFoundException e) {
			logger.error("No employees to display");
			 return new ResponseDTO(false, sqlConstants.ItemNotFound(), null,null);
			 } 
		catch (EmployeeServiceException e) {
			logger.error(sqlConstants.BadRequest()+" :Service layer error");
			 return new ResponseDTO(false, sqlConstants.BadRequest()+" :Service Error: " + e.getMessage(), null,null);
			 } 
		catch (Exception e) {
			 return new ResponseDTO(false, "System error: " + e.getMessage(), null,null);
			 }
		
	}

	
	public ResponseDTO getEmployeeByIdController(int employeeId) {
		
		try {
			 Employeedto employee = Services.getEmployeeByIdServices(employeeId);
			 if (employee!=null) {
					logger.info(sqlConstants.successCode()+" :Retreived data of employee id: "+employeeId);
				 	return new ResponseDTO(true, "Employee found", employee,null);
			 }
			 else
				 throw new EmployeeNotFoundException("Employee with id "+employeeId+" Not found");
			 } 
		catch (EmployeeNotFoundException e) {
			logger.error(sqlConstants.ItemNotFound()+" :Employee not found with id: "+employeeId);
			 return new ResponseDTO(false, sqlConstants.ItemNotFound()+" :Employee not found with id: "+employeeId, null,null);
			 } 
		catch (EmployeeServiceException e) {
			logger.error(sqlConstants.BadRequest()+" :Service layer error");
			 return new ResponseDTO(false, sqlConstants.BadRequest()+" :Service Error: " + e.getMessage(), null,null);
			 } 
		catch (Exception e) {
			 return new ResponseDTO(false, "System error: " + e.getMessage(), null,null);
			 }
		
	}
	public ResponseDTO deleteEmployeeController(int employeeId) {
	
		try {
			 int data =Services.deleteEmployeeServices(employeeId);
			 if (data>0) {
					logger.info(sqlConstants.successCode()+" :Deleted employee : "+employeeId);
			 return new ResponseDTO(true, "Employees deleted: "+data, null,null);
			 } 
			 else {
				 throw new EmployeeNotFoundException(sqlConstants.ItemNotFound());
			 }
		} 
		catch (EmployeeDatabaseException e) {
			return new ResponseDTO(false, e.getMessage(), null,null);
		}
		catch (EmployeeNotFoundException e) {
			logger.error("Employee not found with id: "+employeeId);
			 return new ResponseDTO(false, e.getMessage(), null,null);
			 } 
		catch (EmployeeServiceException e) {
			logger.error(sqlConstants.BadRequest()+" :Service layer error");
			 return new ResponseDTO(false, sqlConstants.BadRequest()+" :Service Error: " + e.getMessage(), null,null);
			 } 
		catch (Exception e) {
			 return new ResponseDTO(false, "System error: " + e.getMessage(), null,null);
			 }
	}
	
	public ResponseDTO updateEmployeeController(Employeedto employee) {
		try {
			 boolean data =Services.updateEmployeeServices(employee);
			 if (valid.checkStringEmpty(employee.fname)==true && valid.checkStringEmpty(employee.phone)==true && valid.checkStringEmpty(employee.email)==true  &&valid.checkEmail(employee.email)==true && valid.checkPhoneNumber(employee.phone)==true )
				{
				
			 if (data==true) {
			 logger.info(sqlConstants.successCode()+" :Updated details of employee : "+employee.employeeId);
			 return new ResponseDTO(data, "Employee details updated of id: "+employee.employeeId, null,null);
			 } 
			 else {
				 throw new EmployeeNotFoundException(sqlConstants.ItemNotFound());
			 }
				}
			 else {
				 logger.info(sqlConstants.BadRequest()+" :Invalid user input");
				 return new ResponseDTO(false,sqlConstants.BadRequest()+" :Invalid information", null,null);
			 }
		}
		catch (EmployeeNotFoundException e) {
			logger.error(sqlConstants.ItemNotFound()+ ": Employee not found with id: "+employee.employeeId);
			 return new ResponseDTO(false, e.getMessage(), null,null);
			 } 
		catch (EmployeeServiceException e) {
			logger.error("Service layer error");
			 return new ResponseDTO(false, sqlConstants.BadRequest()+" :Service Error: " + e.getMessage(), null,null);
			 } 
		catch (Exception e) {
			 return new ResponseDTO(false, "System error: " + e.getMessage(), null,null);
			 }
	}
}