package com.revature.dao;

import java.util.ArrayList;

import com.revature.model.ManToRiePair;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public interface ReimbursementDao {

	//Create 
	boolean createReimbursement(User user, Reimbursement reimbursement); 
	
	//Read 
	ArrayList<ManToRiePair> readApprovedRiembursements(); 
	ArrayList<Reimbursement> getPendingReimbursementsForUser(User user);
	ArrayList<Reimbursement> getResolvedReimbursementsForUser(User user);
	ArrayList<Reimbursement> getAllResolvedReimbursements(); 
	
	//Update 
	boolean approveReimbursement(User user, Reimbursement rei); 
	boolean denyReimbursement(Reimbursement rei);

	ArrayList<Reimbursement> getAllPendingReimbursements(); 
	
	//Delete
	
}