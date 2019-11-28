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
	
	protected void dispatch(HttpServletRequest request, 
			HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}
}
