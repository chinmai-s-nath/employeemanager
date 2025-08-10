package com.litmus7.employeemanager.app;

import com.litmus7.employeemanager.controller.*;
import com.litmus7.employeemanager.dto.Employeedto;
import com.litmus7.employeemanager.util.*;
import java.util.Scanner;
import java.util.*;

public class EmployeeManagerApp {
	
	private static final String INPUT_FILE ="E:\\Eclipse workspace\\employeemanager\\employees.txt";
	private static final String OUTPUT_FILE="employee.csv";
	public static void main(String[] args) {
		
	    EmployeeController employeeController=new EmployeeController(INPUT_FILE, OUTPUT_FILE);
	    Validation valid = new Validation();
		Employeedto employeeDTO=null;
		
		String firstName;
		String lastName;
		String phoneNumber;
		String emailAddress;
		String dateOfJoining;
		int active;
		int employeeID;
		String data="";
	
		int choice;
		String  result = "";
		Scanner sc=new Scanner(System.in);
				
		do {	
			System.out.println("1. Employee Data Ingestion & Console Display\n2. Data Transformation & CSV Export \n3. Interactive Data Entry & Appending\n4. Create Employee\n5.Get all employee data\n6.Get employee data by id\n7.Delete employee\n8. Update employee\n\n Enter your choice: ");
			
			choice=sc.nextInt();
		switch(choice) {
			case 1:
				result=employeeController.getDataFromTextFile();
				System.out.println("Details from the file: ");
				System.out.println(result.replace('$', ' '));
				break;
			case 2:
				String output=employeeController.writeDataToCSV();
				System.out.println(output);
				//method
				break;
			case 3:
				
				
				System.out.println("Enter employee details: ");
				
				System.out.println("Employee id: ");
				employeeID=sc.nextInt();
				
				System.out.println("First name: ");
				firstName=sc.next();
				
				System.out.println("Last name: ");
				lastName=sc.next();
				
				System.out.println("Phone number: ");
				phoneNumber=sc.next();
				
				System.out.println("Email id: ");
				emailAddress=sc.next();
				
				System.out.println("Date of Joining(YYYY-MM-DD): ");
				dateOfJoining=sc.next();
				
				System.out.println("Active (true/false): ");
				active=sc.nextInt();
				
				if (valid.checkStringEmpty(firstName)==true && valid.checkStringEmpty(phoneNumber)==true && valid.checkStringEmpty(emailAddress)==true  &&valid.checkEmail(emailAddress)==true && valid.checkPhoneNumber(phoneNumber)==true)
				{
					employeeDTO=new Employeedto(employeeID,firstName,lastName,phoneNumber,emailAddress,dateOfJoining,active);
					String out=employeeController.writeInputToCSV(employeeDTO);
					System.out.println(out);
				}
				 
				else {
					System.out.println("Enter a valid data\n");
				}
				break;
			case 4:
				data="";
				System.out.println("Enter employee details: ");
				
				System.out.println("Employee id: ");
				employeeID=sc.nextInt();
				
				System.out.println("First name: ");
				firstName=sc.next();
				
				System.out.println("Last name: ");
				 lastName=sc.next();
				
				System.out.println("Phone number: ");
				 phoneNumber=sc.next();
				
				System.out.println("Email id: ");
				 emailAddress=sc.next();
				
				System.out.println("Date of Joining(YYYY-MM-DD): ");
				 dateOfJoining=sc.next();
				
				System.out.println("Active (1/0): ");
				 active=sc.nextInt();
				
//				if (valid.checkStringEmpty(firstName)==true && valid.checkStringEmpty(phoneNumber)==true && valid.checkStringEmpty(emailAddress)==true  &&valid.checkEmail(emailAddress)==true && valid.checkPhoneNumber(phoneNumber)==true)
//				{
//					employeeDTO=new Employeedto(id,firstName,lastName,phoneNumber,emailAddress,dateOfJoining,active);
//					data=employeeController.createEmployeeController(employeeDTO);
//				}
//			
//				else {
//					System.out.println("Enter a valid data\n");
//				}
				 
				employeeDTO=new Employeedto(employeeID,firstName,lastName,phoneNumber,emailAddress,dateOfJoining,active);
				data=employeeController.createEmployeeController(employeeDTO);
				
				System.out.println(data);
				break;
			case 5:
				//employeeController.getAllEmployeeController();
				List<String> employees = employeeController.getAllEmployeeController();

				for (String employee : employees) {
				    System.out.println(employee);
				}
				System.out.println();
				break;

			case 6:
				System.out.println("Enter id to search: ");
				int cho=sc.nextInt();
				data=employeeController.getEmployeeByIdController(cho);
//				if(employeeController.getEmployeeByIdController(cho)!=null) {
//					System.out.println(employeeDTO.employeeId+" , "+employeeDTO.fname+" , "+employeeDTO.lname+" , "+employeeDTO.phone+" , "+employeeDTO.email+" , "+employeeDTO.doj+" , "+employeeDTO.active);
//					System.out.println();
//				}
//				else {
//					System.out.println("No item found");
//				}
				System.out.println(data);
				break;
			case 7:
				System.out.println("Enter id to search: ");
				employeeID=sc.nextInt();
				data=employeeController.deleteEmployeeController(employeeID);
				System.out.println(data);
				break;
 			case 8:
 			
				System.out.println("Enter Employee id to update information: ");
				employeeID=sc.nextInt();
				
				System.out.println("New First name: ");
				firstName=sc.next();
				
				System.out.println("New Last name: ");
				lastName=sc.next();
				
				System.out.println("New Phone number: ");
				phoneNumber=sc.next();
				
				System.out.println("New Email id: ");
				emailAddress=sc.next();
				
				System.out.println("New Date of Joining(YYYY-MM-DD): ");
				dateOfJoining=sc.next();
				
				System.out.println("New Active (1/0): ");
				active=sc.nextInt();
				
				if (valid.checkStringEmpty(firstName)==true && valid.checkStringEmpty(phoneNumber)==true && valid.checkStringEmpty(emailAddress)==true  &&valid.checkEmail(emailAddress)==true && valid.checkPhoneNumber(phoneNumber)==true)
				{
					employeeDTO=new Employeedto(employeeID,firstName,lastName,phoneNumber,emailAddress,dateOfJoining,active);
					
					data=employeeController.updateEmployeeController(employeeDTO);
					System.out.println(data);
				} 
			
				else {
					System.out.println("Enter a valid data\n");
				}
				break;
			default:
				System.out.println("Enter valid option\n");
		}
		}while(choice<=8);
		sc.close();
	}
	

}
