package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.revature.Constants;
import com.revature.model.ManToRiePair;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class ReimbursementDaoImpl implements ReimbursementDao {
	
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
	
	public ArrayList<Reimbursement> getPendingReimbursementsForUser(User user)
	{
			System.out.println("Calling getPendingReimbursementsForUser");
			User dbUser = null;
			ArrayList<Reimbursement> reimbursements = new ArrayList<>(); 
			
			try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);)
			{
				String sql = " SELECT *" + 
						"      FROM ERS_REIMBURSEMENTS " + 
						"      WHERE U_ID_AUTHOR = ?";
				
				PreparedStatement ps = con.prepareStatement(sql); 
				ps.setInt(1, user.getId());
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					System.out.println("RS NEXT IN getPendingRei");
					//TODO: Update R_RECEIPT IN CODE 
				    /** (R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS);  **/ 
					reimbursements.add(new Reimbursement(
							rs.getInt("R_ID"),rs.getInt("R_AMOUNT"),rs.getString("R_DESCRIPTION"), "R_RECEIPT", 
							rs.getTimestamp("R_SUBMITTED"), rs.getTimestamp("R_RESOLVED"), rs.getInt("U_ID_AUTHOR"), 
							rs.getInt("U_ID_RESOLVER"), rs.getInt("RT_TYPE"), rs.getInt("RT_STATUS")));
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		
		return reimbursements;
	}

	@Override
	public boolean createReimbursement(User user, Reimbursement reimbursement) 
	{
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);)
		{    
			String sql = "{call createReimbursement(\'user01\', 200, \'Test\', NULL, 1)}"; 

			CallableStatement cs = con.prepareCall(sql); 
			
			//cs.setString(1, user.getUsername());
			//cs.setInt(2, reimbursement.getAmmount());
			//cs.setString(3, reimbursement.getDescription());
			//cs.setNull(4, java.sql.Types.BLOB);
			
			cs.executeUpdate(); 
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false; 
		}
		return true;
	}

	@Override
	public ArrayList<ManToRiePair> readApprovedRiembursements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approveReimbursement(User user, Reimbursement rei) {
		//TODO: Update reimbursement to be approved and set approver to manager id 
		if(user.getUser_role() == Constants.EMPLOYEE) return false; 

		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);)
		{    
			String sql = "{call approveReimbursement(?, ?)}"; 

			CallableStatement cs = con.prepareCall(sql); 
			
			cs.setString(1, user.getUsername());
			cs.setInt(2, rei.getId());
			
			cs.executeUpdate(); 
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return false; 
		}
		return true;
	}

	@Override
	public boolean denyReimbursement(Reimbursement rei) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Reimbursement> getResolvedReimbursementsForUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> getAllResolvedReimbursements() {
		System.out.println("Calling getAllPendingReimbursements");

		ArrayList<Reimbursement> reimbursements = new ArrayList<>(); 
		
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);)
		{
			String sql = " SELECT *" + 
					"      FROM ERS_REIMBURSEMENTS "+ 
					"	   WHERE RT_STATUS = 1";  
			
			PreparedStatement ps = con.prepareStatement(sql); 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
			    /** (R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS);  **/ 
				reimbursements.add(new Reimbursement(
						rs.getInt("R_ID"),rs.getInt("R_AMOUNT"),rs.getString("R_DESCRIPTION"), "R_RECEIPT", 
						rs.getTimestamp("R_SUBMITTED"), rs.getTimestamp("R_RESOLVED"), rs.getInt("U_ID_AUTHOR"), 
						rs.getInt("U_ID_RESOLVER"), rs.getInt("RT_TYPE"), rs.getInt("RT_STATUS")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	
	return reimbursements;
	}

	@Override
	public ArrayList<Reimbursement> getAllPendingReimbursements() {
		System.out.println("Calling getAllPendingReimbursements");

		ArrayList<Reimbursement> reimbursements = new ArrayList<>(); 
		
		try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);)
		{
			String sql = " SELECT *" + 
					"      FROM ERS_REIMBURSEMENTS " +
					"	   WHERE RT_STATUS = 0 ";  

			
			PreparedStatement ps = con.prepareStatement(sql); 
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
			    /** (R_ID, R_AMOUNT, R_DESCRIPTION, R_RECEIPT, R_SUBMITTED, R_RESOLVED, U_ID_AUTHOR, U_ID_RESOLVER, RT_TYPE, RT_STATUS);  **/ 
				reimbursements.add(new Reimbursement(
						rs.getInt("R_ID"),rs.getInt("R_AMOUNT"),rs.getString("R_DESCRIPTION"), "R_RECEIPT", 
						rs.getTimestamp("R_SUBMITTED"), rs.getTimestamp("R_RESOLVED"), rs.getInt("U_ID_AUTHOR"), 
						rs.getInt("U_ID_RESOLVER"), rs.getInt("RT_TYPE"), rs.getInt("RT_STATUS")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	
	return reimbursements;
	}
	
}
