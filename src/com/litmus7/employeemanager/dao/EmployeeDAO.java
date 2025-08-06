package com.litmus7.employeemanager.dao;

import java.sql.*;
import java.util.List;
import java.util.*;

import com.litmus7.employeemanager.dto.*;
import com.litmus7.employeemanager.util.*;

public class EmployeeDAO {
	ConnectionUtil  connection=new ConnectionUtil();
	public boolean createEmployee(Employeedto employeeController) {	
		Connection dbconnect=connection.ConnectionCreate();
		PreparedStatement preparedstatement=null;
		try {
			preparedstatement=dbconnect.prepareStatement("Insert into employee values(?,?,?,?,?,?,?)");
			preparedstatement.setInt(1, employeeController.id);
			preparedstatement.setString(2, employeeController.fname);
			preparedstatement.setString(3, employeeController.lname);
			preparedstatement.setString(4, employeeController.phone);
			preparedstatement.setString(5, employeeController.email);
			preparedstatement.setString(6, employeeController.doj);
			preparedstatement.setInt(7, employeeController.active);
			preparedstatement.execute();
			return true;
		}catch (Exception e){
			return false;
		}
		finally {
			try {
				preparedstatement.close();
				dbconnect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<String> getAllEmployee() {
		Connection dbconnect=connection.ConnectionCreate();
		ResultSet resultSet=null;
		Statement queryStatement=null;
		List<String>result=new ArrayList<>();
		try {
			queryStatement=dbconnect.createStatement();
			resultSet=queryStatement.executeQuery("select id,first_name,last_name,mobile_number,email_address,joining_date, active_status from employee");
			while (resultSet.next()) {
	            String row = resultSet.getInt("id") + " , " +
	                         resultSet.getString("first_name") + " , " +
	                         resultSet.getString("last_name") + " , " +
	                         resultSet.getString("mobile_number") + " , " +
	                         resultSet.getString("email_address") + " , " +
	                         resultSet.getDate("joining_date") + " , " +
	                         resultSet.getBoolean("active_status");
	            result.add(row);
	        }
			//resultSet.close();
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			try {
				queryStatement.close();
				dbconnect.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public Employeedto getEmployeeById(int empId){
		Connection dbConnect=connection.ConnectionCreate();
		ResultSet resultSet=null;
		Employeedto dto1 = null;
		PreparedStatement preparedStatement=null;
		try {
			preparedStatement=dbConnect.prepareStatement("Select id,first_name,last_name,mobile_number,email_address,joining_date, active_status from employee where id=?");
			preparedStatement.setInt(1, empId);
			resultSet=preparedStatement.executeQuery();
		
			if (resultSet.next()) {
	            dto1 = new Employeedto(
	                resultSet.getInt("id"),
	                resultSet.getString("first_name"),
	                resultSet.getString("last_name"),
	                resultSet.getString("mobile_number"),
	                resultSet.getString("email_address"),
	                resultSet.getString("joining_date"),
	                resultSet.getInt("active_status"));
	            }
			
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			try {
				dbConnect.close();
				preparedStatement.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto1;
	}
	
	public int deleteEmployee(int empId){
		Connection dbconnect=null;
		int rowsDeleted=0;
		PreparedStatement st=null;
		try {
			dbconnect=connection.ConnectionCreate();
			st=dbconnect.prepareStatement("Delete from employee where id=?");
			st.setInt(1, empId);
			rowsDeleted=st.executeUpdate();
			System.out.println("Deleted the data :");
		}catch (Exception exc) {
			exc.printStackTrace();
			
		}
		finally {
			try {
				dbconnect.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsDeleted;
	}
	
	public boolean updateEmployee(Employeedto emp)  {
	    Connection dbConnect = null;
	    boolean rowsUpdated = false;
	    PreparedStatement statement=null;
	    
	    try {
	        dbConnect = connection.ConnectionCreate();
	        statement = dbConnect.prepareStatement(
	            "UPDATE employee SET first_name = ?, last_name = ?, mobile_number = ?, email_address = ?, joining_date = ?, active_status = ? WHERE id = ?"
	        );

	        statement.setString(1, emp.fname);
	        statement.setString(2, emp.lname);
	        statement.setString(3, emp.phone);
	        statement.setString(4, emp.email);
	        statement.setString(5, emp.doj);
	        statement.setInt(6, emp.active);
	        statement.setInt(7, emp.id);

	        rowsUpdated = (statement.executeUpdate() > 0);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    finally {
	    	try {
				dbConnect.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }

	    return rowsUpdated;
	}


}