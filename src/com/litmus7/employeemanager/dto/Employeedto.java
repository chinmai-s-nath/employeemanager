package com.litmus7.employeemanager.dto;

public class Employeedto {
	public int employeeId;
	public String fname;
	public String lname;
	public String  phone;
	public String email;
	public String doj;
	public int active;
	

	public Employeedto() {
		
	}
	
	public Employeedto(int id2,String fname,String lname,String phone,String email,String doj, int active1){	
		
		this.employeeId=id2;
		this.fname=fname;
		this.lname=lname;
		this.phone=phone;
		this.email=email;
		this.doj=doj;
		this.active=active1;
		
	}
	
	public String EmployeeDTODisplay() {
		return (employeeId+" , "+fname+" , "+lname+" , "+phone+" , "+email+" , "+doj+" , "+active);
	}

}
