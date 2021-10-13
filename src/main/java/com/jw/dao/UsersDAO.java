package com.jw.dao;

import java.sql.ResultSet;

public interface UsersDAO {
	public boolean findWithUserName(String userName);
	public boolean insertAnUser(String username, String salt, String hashed_password, String email);
	public ResultSet findInfoWithUserName(String userName);
	public void printUserTable();

}
