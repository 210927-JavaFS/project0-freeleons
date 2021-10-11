package com.jw.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public ConnectionUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://db-postgre.cthrbkpy4fxr.us-east-1.rds.amazonaws.com:5432/purchaseordersystem";
		String username = "freeleons";
		String password = "Password";
		return DriverManager.getConnection(url, username, password);
	}
	
	

}
