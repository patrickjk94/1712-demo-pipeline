package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.User;
import com.revature.model.User;
import com.revature.service.AppService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LoginServlet -GET"); 
		req.getRequestDispatcher("login.html").forward(req, resp);
	}
	 
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LoginServlet -POST");
		User user = new User();
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		user.setUsername(username);
		user.setPassword(password);
		
		user = new AppService().validateUser(user);
		
		System.out.println("The user enter: " + username + " & " + password);
		if(user != null)
		{
			HttpSession session = req.getSession(); 
			
			session.setAttribute("user", user);

			//			req.getRequestDispatcher("app.html").forward(req, resp);

			switch(user.getUser_role())
			{
				case "employee": 
					System.out.println("Forwarding to Employee Page");
					req.getRequestDispatcher("features/employeepage/emp_master_page.html").forward(req, resp);
					break; 
				case "manager": 
					System.out.println("Forwarding to Manager Page");
					req.getRequestDispatcher("features/managerpage/man_master_page.html").forward(req, resp);
					break; 
				default: 
					System.err.println("invalid credentials -sending user back to login.html");
					resp.sendRedirect("login.html");
					break; 
			}
		}
		else
		{
			System.err.println("invalid credentials -sending user back to login.html");
			resp.sendRedirect("login.html");
		}
	}
}
