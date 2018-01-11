package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.User;
import com.revature.model.Reimbursement;

public interface UserDao {

	//Create
	
	//Read
	public User getUserByUsername(User user);
	public ArrayList<User> getAllUsers();

	//Update
	// public void updateEmployee(Employee user); 
	
	//Delete
	
}
