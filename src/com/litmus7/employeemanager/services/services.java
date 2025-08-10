package com.litmus7.employeemanager.services;

import com.litmus7.employeemanager.dto.Employeedto;
import com.litmus7.employeemanager.dao.*;
import java.util.*;

public class services {
	private EmployeeDAO employeeDAO=new EmployeeDAO();
	public boolean createEmployeeServices(Employeedto employeeController) {
		try {
			boolean result=employeeDAO.createEmployee(employeeController);
			return  result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public List<String>getAllEmployeeServices(){
		try {
			return employeeDAO.getAllEmployee();
		} catch (Exception e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public Employeedto getEmployeeByIdServices(int employeeId) {
		try {
			return employeeDAO.getEmployeeById(employeeId);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public int deleteEmployeeServices(int employeeId) {
		try {
			return employeeDAO.deleteEmployee(employeeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean updateEmployeeServices(Employeedto emp) {
		try {
			return employeeDAO.updateEmployee(emp);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}