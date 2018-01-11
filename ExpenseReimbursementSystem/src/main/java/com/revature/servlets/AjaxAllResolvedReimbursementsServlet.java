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
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.AppService;

@WebServlet("/ajaxAllResolved")
public class AjaxAllResolvedReimbursementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
/**
 * @see HttpServlet#HttpServlet()
 */
public AjaxAllResolvedReimbursementsServlet() {
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
			
			ArrayList<Reimbursement> reimbursements = service.getAllResolvedReimbursements(); 
			if(reimbursements.size() > 0) 
			{
				System.out.println("---------------Converting Employees to DTO");
				//				BankUserDTO userDTO = service.convertToBankUserDTO(sessionUser, bankAccount);
				
				System.out.println("---------------JSON Mapping DTO------------------");
				ObjectMapper mapper = new ObjectMapper();
				
				String json = mapper.writeValueAsString(reimbursements);
				System.out.println(json);
				
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				out.write(json);
			} 
			else {
				response.setStatus(418);
			}
		}
	}
}
