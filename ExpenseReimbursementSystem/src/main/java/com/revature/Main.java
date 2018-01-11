package com.revature;

import java.util.Scanner;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.model.User;
import com.revature.service.AppService;

public class Main {
	// TODO Auto-generated method stub
//	public static void main(String[] args) 
//	{
//		AppService appService = new AppService(); 
//		String username = null, password = null; 
//		
//		System.out.println("Printing all Employees: ");
//		//Print out all Users 
//		for(User emp : appService.getAllEmployees()) {
//			System.out.println(emp);
//		}
//		
//		//LOG INTO AN ACCOUNT
//		boolean logged_in = false; 		
//		while(true) 
//		{
//				System.out.println("=== Please log in to your account ===");
//				System.out.print("username: "); 
//				Scanner scanner = new Scanner(System.in);
//				username = scanner.nextLine();
//				System.out.print("password: "); 
//				password = scanner.nextLine();
//				
//				User employee = new User(username, password);
//				employee = appService.validateUser(employee);
//				System.out.println(employee);
//				if(employee == null)
//				{
//					System.out.println("");
//					System.out.println("Log in failed. Please try again");
//				}
//				else
//				{
//					System.out.println("");
//					System.out.println("Welcome " + username + "!");
//					System.out.println("Would you like to Submit a request?");
//					logged_in = true; 
//					
//					// UserDao user = new UserDaoImpl(); 
//					// user.getUserByUsername(employee).getUsername(); 
//					
//				}
//			while(logged_in)
//			{
//				System.out.println("");
//				printOptions();
//				String option = scanner.nextLine(); 
//				switch(option)
//				{
//					
//				}
//			}
//		}
//	}
//
//	public static void printOptions() 
//	{
//		System.out.println("deposit (d). withdraw(w). view balance(v).");
//		System.out.println("logout(q). view account id (i) view options(h).");  
//	}
//
}
