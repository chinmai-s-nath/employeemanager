package com.litmus7.employeemanager.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.litmus7.employeemanager.constants.*;
import com.litmus7.employeemanager.exception.EmployeeDatabaseException;

import java.io.FileInputStream;

public class ConnectionUtil {
    
	private static final Logger logger=LogManager.getLogger(ConnectionUtil.class);
    private static Properties properties = new Properties();

    // Load properties only once
    static {
        try {
            properties.load(new FileInputStream("jdbc.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection ConnectionCreate() throws  EmployeeDatabaseException {
        Connection connection = null;
        try {
            String thedburl = properties.getProperty("dburl");
            String theuser = properties.getProperty("user");
            String thepass = properties.getProperty("password");
            connection = DriverManager.getConnection(thedburl, theuser, thepass);   
            
        } catch (Exception e) {
        	logger.error(sqlConstants.dbConnectionErrorCode()+": Connection not established. . .");
        	throw new EmployeeDatabaseException("Database error occured");
        }
  
        
        return connection;
    }
}

