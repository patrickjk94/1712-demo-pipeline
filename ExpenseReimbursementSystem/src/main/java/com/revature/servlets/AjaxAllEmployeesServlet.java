package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.service.AppService;

/**
 * Servlet implementation class AjaxBankInfoServlet
 */
@WebServlet("/ajaxAllEmployees")
public class AjaxAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
/**
 * @see HttpServlet#HttpServlet()
 */
public AjaxAllEmployeesServlet() {
    super();
}

/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		System.out.println("AjaxAllEmployeesServlet -GET");
		AppService service = new AppService();
		HttpSession session = request.getSession();
		
		User sessionUser = (User) session.getAttribute("user");
		System.out.println("User " + sessionUser.getFirstName());
		
		if(sessionUser != null) {
			ArrayList<User> employees = service.getAllEmployees(); 
			if(employees.size() > 0) 
			{
				System.out.println("---------------Converting Employees to DTO");
				//				BankUserDTO userDTO = service.convertToBankUserDTO(sessionUser, bankAccount);
				
				System.out.println("---------------JSON Mapping DTO------------------");
				ObjectMapper mapper = new ObjectMapper();
				
				String json = mapper.writeValueAsString(employees);
				System.out.println(json);
				
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				out.write(json);
			} else {
				response.setStatus(418);
			}
		}
		else{
			response.setStatus(418);
		}
	}
}
