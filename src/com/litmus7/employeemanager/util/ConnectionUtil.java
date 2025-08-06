package com.litmus7.employeemanager.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {

	static String dburl="jdbc:mysql://localhost:3306/empdb";
	static String user="root";
	static String pass="root";
	
	public Connection ConnectionCreate() {
		Connection connection=null;
		try {
		connection=DriverManager.getConnection(dburl,user,pass);		
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		return connection;
	}
}
