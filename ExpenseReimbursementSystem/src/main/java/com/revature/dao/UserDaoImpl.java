package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.User;
import com.revature.model.Reimbursement;

public class UserDaoImpl implements UserDao {

	private static final String USERNAME = "patrick";
	private static final String PASSWORD = "pass1234";
	private static final String URL = "jdbc:oracle:thin:@usfdbjava.cvkuvhta6l54.us-east-2.rds.amazonaws.com:1521:orcl";
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public User getUserByUsername(User user)
	{
			User dbUser = null;
			try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);)
			{
				String sql = "SELECT *" + 
						"  FROM ERS_USERS LEFT JOIN ERS_USER_ROLES" + 
						"    ON ERS_USERS.UR_ID = ERS_USER_ROLES.UR_ID" + 
						"    WHERE U_USERNAME = ?";
				
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getUsername());
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					dbUser = new User(rs.getInt("U_ID"), rs.getString("U_USERNAME"), rs.getString("U_FIRSTNAME"),
							rs.getString("U_LASTNAME"), rs.getString("U_PASSWORD"), rs.getString("UR_ROLE"));
				};
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
		return dbUser;
	}

	@Override
	public ArrayList<User> getAllUsers() 
	{
		ArrayList<User> users = new ArrayList<User>();		
		try(Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);)
		{
			//TODO: Do a join to get all the user roles
			String sql = "SELECT U_ID, U_USERNAME, U_PASSWORD, U_FIRSTNAME, U_LASTNAME, U_EMAIL, UR_ROLE" + 
				    	 " FROM ERS_USERS JOIN ERS_USER_ROLES" +
				    	 " ON ERS_USERS.UR_ID = ERS_USER_ROLES.UR_ID"; 

			
			PreparedStatement ps = conn.prepareStatement(sql); 

			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				users.add(new User(
						rs.getInt("U_ID"),rs.getString("U_USERNAME"),rs.getString("U_FIRSTNAME"),
						rs.getString("U_LASTNAME"), rs.getString("U_PASSWORD"), rs.getString("UR_ROLE")));
			}
		}
		
		catch (SQLException e)  
		{
			System.out.println("Checkpoint3");
			e.printStackTrace();
		}
		return users;
	}

}
