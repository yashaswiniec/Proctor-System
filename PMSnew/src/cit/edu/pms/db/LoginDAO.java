package cit.edu.pms.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LoginDAO {
	
	
	public String getName(String user)

	{
		String pass = null;
		Connection con = DBConnectionManager.getConnection();
		String sql= "select  password from login where username=? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			while(rs!=null && rs.next())
			{
				pass = rs.getString(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return pass;
		
	}
	
	public String getRole(String user)

	{
		String role = null;
		Connection con = DBConnectionManager.getConnection();
		String sql= "select  role from login where username=? ";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			while(rs!=null && rs.next())
			{
				role = rs.getString(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return role;
	}

}
