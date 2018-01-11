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

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LogoutServlet -GET"); 
		HttpSession session = req.getSession(); 
		
		session.removeAttribute("user");
		req.getRequestDispatcher("logout.html").forward(req, resp); 

		System.err.println("Logging out -sending user back to login.html");
		resp.sendRedirect("login.html");
	}
	 
	
	@Override 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("LogoutServlet -POST");		
		
		HttpSession session = req.getSession(); 
		
		session.removeAttribute("user");

		req.getRequestDispatcher("logout.html").forward(req, resp); 
	
		System.err.println("Logging out -sending user back to login.html");
//		resp.sendRedirect("login.html");
		req.getRequestDispatcher("login").forward(req, resp);
	}
}
