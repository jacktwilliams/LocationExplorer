package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoManager;

public class BaseServlet extends HttpServlet {
	protected DaoManager daoManager;
	
	public void init() {
		System.out.println("Base Servlet initialized");
		daoManager = (DaoManager) getServletContext().getAttribute("daoManager");
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// disable page caching
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		//send to login if no user is logged in
		if (request.getSession().getAttribute("user") == null) {
			dispatch(request, response, "/login.jsp");
		}
	}
	
	protected void dispatch(HttpServletRequest request, 
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
	
	protected void redirect(HttpServletRequest request, 
			HttpServletResponse response, String page) throws ServletException, IOException {
		daoManager.closeSession(); //close hibernate session for dao ops to take affect before we nav
		response.sendRedirect(page);
	}
}
