package com.litmus7.employeemanager.dao;

import java.sql.*;
import java.util.List;
import java.util.*;
import java.sql.SQLException;
import com.litmus7.employeemanager.dto.*;
import com.litmus7.employeemanager.util.*;
import com.litmus7.employeemanager.constants.*;
import com.litmus7.employeemanager.exception.*;

public class EmployeeDAO {
	
	
	ConnectionUtil  connection=new ConnectionUtil();
	public boolean createEmployee(Employeedto employeeController) {	
		Connection dbconnect=null;
		PreparedStatement preparedstatement=null;
		try {
			dbconnect=ConnectionUtil.ConnectionCreate();
			preparedstatement=dbconnect.prepareStatement("Insert into employee values(?,?,?,?,?,?,?)");
			preparedstatement.setInt(1, employeeController.employeeId);
			preparedstatement.setString(2, employeeController.fname);
			preparedstatement.setString(3, employeeController.lname);
			preparedstatement.setString(4, employeeController.phone);
			preparedstatement.setString(5, employeeController.email);
			preparedstatement.setString(6, employeeController.doj);
			preparedstatement.setInt(7, employeeController.active);
			preparedstatement.execute(); 
			return true;
		}
		catch(Exception e) {
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
	
	public List<String> getAllEmployee(){
		Connection dbconnect=null;
		ResultSet resultSet=null;
		Statement queryStatement=null;
		List<String>result=new ArrayList<>();
		try {
			dbconnect=ConnectionUtil.ConnectionCreate();
			queryStatement=dbconnect.createStatement();
			resultSet=queryStatement.executeQuery("select "+sqlConstants.employeeId()+","+sqlConstants.firstName()+","+sqlConstants.lastName()+","+sqlConstants.Phone()+","+sqlConstants.Email()+","+sqlConstants.joiningDate()+","+sqlConstants.activeStatus()+" from employee");
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
		}
			catch(Exception e) {
				
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
	
	public Employeedto getEmployeeById(int employeeID) {
		Connection dbConnect=null;
		ResultSet resultSet=null;
		Employeedto employeeDTO = null;
		PreparedStatement preparedStatement=null;
		try {
			dbConnect=ConnectionUtil.ConnectionCreate();;
			preparedStatement=dbConnect.prepareStatement(sqlConstants.fetchDataById());
			preparedStatement.setInt(1, employeeID);
			resultSet=preparedStatement.executeQuery();
		
			if (resultSet.next()) {
				employeeDTO = new Employeedto(
	                resultSet.getInt("id"),
	                resultSet.getString("first_name"),
	                resultSet.getString("last_name"),
	                resultSet.getString("mobile_number"),
	                resultSet.getString("email_address"),
	                resultSet.getString("joining_date"),
	                resultSet.getInt("active_status"));
	            }
			else {
			
				throw new EmployeeNotFoundException("Employee with ID " + employeeID + " not found");
			}
			
		}
		catch(Exception sqlException) {
			
		}
		finally {
			try {
				dbConnect.close();
				preparedStatement.close();
				resultSet.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employeeDTO;
	}
	
	public int deleteEmployee(int employeeID) {
		Connection dbconnect=null;
		int rowsDeleted=-1;
		PreparedStatement st=null;
		try {
			dbconnect=ConnectionUtil.ConnectionCreate();
			st=dbconnect.prepareStatement("Delete from employee where id=?");
			st.setInt(1, employeeID);
			rowsDeleted=st.executeUpdate();
		
		}
		catch(Exception e) {

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
	
	public boolean updateEmployee(Employeedto employeeDTO)  {
	    Connection dbConnect = null;
	    boolean rowsUpdated = false;
	    PreparedStatement statement=null;
	    
	    try {
	        dbConnect = ConnectionUtil.ConnectionCreate();
	        statement = dbConnect.prepareStatement(
	            "UPDATE employee SET first_name = ?, last_name = ?, mobile_number = ?, email_address = ?, joining_date = ?, active_status = ? WHERE id = ?"
	        );

	        statement.setString(1, employeeDTO.fname);
	        statement.setString(2, employeeDTO.lname);
	        statement.setString(3, employeeDTO.phone);
	        statement.setString(4, employeeDTO.email);
	        statement.setString(5, employeeDTO.doj);
	        statement.setInt(6, employeeDTO.active);
	        statement.setInt(7, employeeDTO.employeeId);

	        rowsUpdated = (statement.executeUpdate() > 0);
	        
	    } catch(Exception sqlException) {
			//throw new EmployeeDaoException("Database error while fetching employee.",sqlException);
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