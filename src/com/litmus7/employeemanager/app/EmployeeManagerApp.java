package com.litmus7.employeemanager.app;

import com.litmus7.employeemanager.controller.*;
import com.litmus7.employeemanager.dto.Employeedto;
import com.litmus7.employeemanager.util.*;
import java.util.Scanner;
import java.util.*;

public class EmployeeManagerApp {
	
	private static final String INPUT_FILE ="E:\\Eclipse workspace\\employeemanager\\employees.txt";
	private static final String OUTPUT_FILE="employee.csv";
	private static ResponseDTO response=null;
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

				employeeDTO=new Employeedto(employeeID,firstName,lastName,phoneNumber,emailAddress,dateOfJoining,active);
				response=employeeController.createEmployeeController(employeeDTO);
				if (response.isSuccess()) {
					System.out.println(response.getMessage());
				}
				else {
					System.out.println(response.getMessage()+" :"+response.getData());
				}
				break;
			case 5:
				//employeeController.getAllEmployeeController();
				response = employeeController.getAllEmployeeController();
				if (response.isSuccess()) {
					List<String> list=response.getList();
					for (String employee : list) {
					    System.out.println(employee);
					}
				}
				System.out.println();
				break;

			case 6:
				System.out.println("Enter id to search: ");
				int cho=sc.nextInt();
				response=employeeController.getEmployeeByIdController(cho);
				
				if (response.isSuccess()) {
					 Employeedto emp = response.getData();
					 System.out.println("Employee Found: " + emp.fname + ", joining date: " +emp.doj);
					 } else {
					 System.out.println("Error: " + response.getMessage());
					 }
				
				System.out.println(data);
				break;
			case 7:
				System.out.println("Enter id to search: ");
				employeeID=sc.nextInt();
				response=employeeController.deleteEmployeeController(employeeID);
				if(response.isSuccess()) {
					System.out.println(response.getMessage());
				}
				else {
					System.out.println(response.getMessage());
				}
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
					
					response=employeeController.updateEmployeeController(employeeDTO);
					System.out.println(data);
				} 
			
				else {
					System.out.println("Enter a valid data\n");
				}
				
				if (response.isSuccess()) {
					System.out.println(response.getMessage());
				}
				else {
					System.out.println(response.getMessage());
				}
				break;
			default:
				System.out.println("Enter valid option\n");
		}
		}while(choice<=8);
		sc.close();
	}
	

}
