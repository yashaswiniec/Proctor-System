package cit.edu.pms.service;

import cit.edu.pms.db.LoginDAO;

public class LoginService {
	
	private static LoginDAO logindao = new LoginDAO();
	
	public static String getName(String username)
	{
		return logindao.getName(username);
	}

	public static String getRole(String username)
	{
		return logindao.getRole(username);
	}
	

}
