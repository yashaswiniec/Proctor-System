package cit.edu.pms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
	static
	{
		try {
			System.out.println("Loading JDBC Driver***");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Driver not found");
		}
	}
	public static Connection getConnection()
	{
		Connection con = null;
		
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "yashu");
			//System.out.println("Connection successfull");
		}
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return con;
	}
}
