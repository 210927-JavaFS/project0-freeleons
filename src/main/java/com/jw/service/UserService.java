package com.jw.service;

import java.sql.ResultSet;

import com.jw.dao.UsersDAO;
import com.jw.dao.UsersDAOImplement;

public class UserService {

	public UserService() {
		// TODO Auto-generated constructor stub
	}
	private UsersDAO userDAO = new UsersDAOImplement();
	
	public boolean findWithUserName(String userName) {
		return userDAO.findWithUserName(userName);
	}
	
	public boolean insertAnUser(String username, String salt, String hashed_password, String email)
	{
		return userDAO.insertAnUser(username, salt, hashed_password, email);
	}
	
	public ResultSet findInfoWithUserName(String userName)
	{
		return userDAO.findInfoWithUserName(userName);
	}

}
