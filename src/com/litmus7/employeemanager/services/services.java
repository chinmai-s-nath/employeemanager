package com.litmus7.employeemanager.services;

import com.litmus7.employeemanager.dto.Employeedto;
import com.litmus7.employeemanager.dao.*;
import com.litmus7.employeemanager.exception.*;
import java.util.*;


public class services {
	private EmployeeDAO employeeDAO=new EmployeeDAO();
	public boolean createEmployeeServices(Employeedto employeeController) throws EmployeeServiceException,	EmployeeNotCreated {
		try {
			 boolean result=employeeDAO.createEmployee(employeeController);
			 return result;
		}
			catch (EmployeeDaoException e) {
				 throw new EmployeeServiceException("Service layer failed to fetch employee.",e);
		}
	}

	public List<String>getAllEmployeeServices()throws EmployeeServiceException,	EmployeeNotFoundException{
		try {
			return employeeDAO.getAllEmployee();
		}
		catch (EmployeeDaoException e) {
				 throw new EmployeeServiceException("Service layer failed to fetch employee.",e);
				 }
		
	}
	public Employeedto getEmployeeByIdServices(int employeeId)throws EmployeeServiceException,	EmployeeNotFoundException { 
		try {
			return employeeDAO.getEmployeeById(employeeId);
		}
		catch (EmployeeDaoException e) {
			 throw new EmployeeServiceException("Service layer failed to fetch employee.",e);
			 }
	}

	public int deleteEmployeeServices(int employeeId) throws EmployeeServiceException,	EmployeeNotFoundException{
		try {
			return employeeDAO.deleteEmployee(employeeId);
		}catch (EmployeeDaoException e) {
			 throw new EmployeeServiceException("Service layer failed to fetch employee.",e);
			 }
		}

	
	public boolean updateEmployeeServices(Employeedto emp) throws EmployeeServiceException,	EmployeeNotFoundException{
		try {
			return employeeDAO.updateEmployee(emp);
		}
		catch (EmployeeDaoException e) {
			 throw new EmployeeServiceException("Service layer failed to fetch employee.",e);
		}
	}
}
