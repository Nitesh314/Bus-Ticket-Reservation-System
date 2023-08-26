package com.bus.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.bus.Colours.Colours;

public class DBUtil {
	
	
	public static Connection provideConnection() {
		
		Connection conn=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
		
	try {
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/busreservation","root","nitesh");
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
		
		
		return conn;
	}
	
	
}
