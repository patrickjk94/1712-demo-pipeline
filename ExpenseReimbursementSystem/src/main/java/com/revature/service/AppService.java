package com.revature.service;

import java.util.ArrayList;

import com.revature.Constants;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class AppService {
	
	public User validateUser(User user){
		
		UserDao dao = new UserDaoImpl();
		
		System.out.println("=== Calling validate User ===");
		User dbUser = dao.getUserByUsername(user);
		if(dbUser != null)
		{
			if( dbUser.getUsername().equals(user.getUsername() ) &&
				dbUser.getPassword().equals(user.getPassword()))
			{
				return dbUser;
			}
		}
		return null;
	}

	public ArrayList<User> getAllEmployees() {
		UserDao dao = new UserDaoImpl(); 

		return dao.getAllUsers(); 
	}
	
	public ArrayList<Reimbursement> getAllPendingReimbursements(){
		ReimbursementDao dao = new ReimbursementDaoImpl(); 
		return dao.getAllPendingReimbursements(); 
	}
	
	public ArrayList<Reimbursement> getAllResolvedReimbursements(){
		ReimbursementDao dao = new ReimbursementDaoImpl(); 
		return dao.getAllResolvedReimbursements(); 
	}

}






























