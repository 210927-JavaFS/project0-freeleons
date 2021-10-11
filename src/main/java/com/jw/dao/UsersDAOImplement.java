package com.jw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jw.jdbc.ConnectionUtil;

public class UsersDAOImplement implements UsersDAO {

	public UsersDAOImplement() {
		// TODO Auto-generated constructor stub
	}
	public static Logger log = LoggerFactory.getLogger(UsersDAOImplement.class);
	@Override
	public boolean findWithUserName(String userName) {
		// TODO Auto-generated method stub
		
		try{ //try-with-resources
			Connection conn = ConnectionUtil.getConnection();
//			String sql = "SELECT COUNT(*) FROM Users WHERE user_name =?;";
			String sql = "SELECT * FROM Users WHERE user_name =?;";
			log.debug(sql);
			log.debug("conn.isClosed(): " + conn.isClosed());
			log.debug("conn.toString(): " + conn.toString());
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			log.debug(statement.toString());
			ResultSet result = statement.executeQuery();
//			log.debug("result.next(): " + result.next());
			if(result.next())
			{
				statement.close();
				conn.close();
				return true;
			}
			else
			{
				statement.close();
				conn.close();
				return false;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}
	
	public ResultSet findInfoWithUserName(String userName)
	{
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT * FROM Users WHERE user_name =?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, userName);
			ResultSet result = statement.executeQuery();
			
			conn.close();
			return result;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	@Override
	public boolean insertAnUser(String username, String salt, String hashed_password, String email) {
		// TODO Auto-generated method stub
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "INSERT INTO Users (user_name, email, salt, hashed_password, account_type) VALUES (?, ?, ?, ?, 'Customer');";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setString(3, salt);
			statement.setString(4, hashed_password);
			statement.execute();
			int result = statement.getUpdateCount();
			statement.close();
			conn.close();
			log.debug("getUpdateCount: " + result);
			return result > 0;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}

}
