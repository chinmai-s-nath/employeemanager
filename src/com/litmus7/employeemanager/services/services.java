package com.litmus7.employeemanager.services;

import com.litmus7.employeemanager.dto.Employeedto;
import com.litmus7.employeemanager.dao.*;
import com.litmus7.employeemanager.exception.*;
import com.litmus7.employeemanager.util.ConnectionUtil;

import java.util.*;



public class services {
	private EmployeeDAO employeeDAO=new EmployeeDAO();
	public boolean createEmployeeServices(Employeedto employeeController) throws EmployeeServiceException,	EmployeeNotCreated, EmployeeDaoException {
		try {
			 boolean result=employeeDAO.createEmployee(employeeController);
			 if (result==true) {
				 return result;
		}
			 else {
				 throw new EmployeeNotCreated("Employee not created");
			 }
		}
	
		catch (Exception e) {	 
			throw new EmployeeServiceException ("Database Connection error/ Service layer error ", e);
		}
	}

	public List<String>getAllEmployeeServices()throws EmployeeServiceException,	EmployeeNotFoundException{
		try {
			return employeeDAO.getAllEmployee();
		}
		catch (Exception e) {
				 throw new EmployeeServiceException("Service layer failed to fetch employee.",e);
				 }
		
	}
	public Employeedto getEmployeeByIdServices(int employeeId)throws EmployeeServiceException,	EmployeeNotFoundException { 
		try {
			return employeeDAO.getEmployeeById(employeeId);
		}
		catch (Exception e) {
			 throw new EmployeeServiceException("Service layer failed to fetch employee.",e);
			 }
	}

	public int deleteEmployeeServices(int employeeId) throws EmployeeServiceException,	EmployeeNotFoundException, EmployeeDatabaseException{
		try {
			int rowsAffected= employeeDAO.deleteEmployee(employeeId);
			return  rowsAffected;
			
		}
//		catch (EmployeeDatabaseException e) {
//			throw new EmployeeDatabaseException("datavalse");
//		}
//		catch(EmployeeNotFoundException e) {
//			throw new EmployeeNotFoundException("Employee not found");
//		}
		catch (Exception e) {
			 throw new EmployeeServiceException("Service layer failed to delete employee.",e);
			 }
		}

	
	public boolean updateEmployeeServices(Employeedto emp) throws EmployeeServiceException,	EmployeeNotFoundException{
		try {
			return employeeDAO.updateEmployee(emp);
		}
		catch (Exception e) {
			 throw new EmployeeServiceException("Service layer failed to update employee.",e);
		}
	}
}
