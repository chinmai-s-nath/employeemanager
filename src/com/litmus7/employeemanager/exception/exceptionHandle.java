package com.litmus7.employeemanager.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class exceptionHandle {
	
	
	public String readFileExceptionHandle(String FilePath) {
		BufferedReader bufferReader = null;
		String data="";
		String line="";

		try {
			bufferReader=new BufferedReader(new FileReader(FilePath));
			while((line=bufferReader.readLine())!=null) {
				
				data=data+line+"\n";	
			
			}
			data=data.substring(0, data.length()-1);
			bufferReader.close();
		}catch(IOException e) {
			
			return (e.getMessage());
		}
		
		return data;
			
	}		
	
	public String writeFileExceptionHandle(String filepath, String data, boolean b) {
		
		PrintWriter pw;
		try {
			pw=new PrintWriter(new FileWriter(filepath,b));
			pw.println(data);
			pw.close();
			return "Successfully created file:\n";
		}
		catch(IOException e) {
			System.out.println("Error occured: "+e.getMessage());
			return "Aborting process:\n";
		}
	}
}
