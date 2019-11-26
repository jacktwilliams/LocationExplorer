package controllers;

import javax.servlet.http.HttpServlet;

import dao.DaoManager;

public class BaseServlet extends HttpServlet {
	protected DaoManager daoManager;
	
	public void init() {
		daoManager = (DaoManager) getServletContext().getAttribute("daoManager");
	}
}
