package com.litmus7.employeemanager.services;

import com.litmus7.employeemanager.dto.Employeedto;
import com.litmus7.employeemanager.dao.*;
import java.util.*;

public class services {
	EmployeeDAO employeeDAO=new EmployeeDAO();
	public boolean createEmployeeServices(Employeedto employeeController) {
		boolean result=employeeDAO.createEmployee(employeeController);
		return  result;
	}

	public List<String>getAllEmployeeServices(){
		return employeeDAO.getAllEmployee();
		
	}
	public Employeedto getEmployeeByIdServices(int employeeId) {
		return employeeDAO.getEmployeeById(employeeId);
	}

	public int deleteEmployeeServices(int employeeId) {
		return employeeDAO.deleteEmployee(employeeId);
	}
	
	public boolean updateEmployeeServices(Employeedto emp) {
		return employeeDAO.updateEmployee(emp);
	}
}