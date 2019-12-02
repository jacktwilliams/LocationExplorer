package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import dao.DaoManager;
import dao.UserDao;
import handlers.LoginHandler;  

@WebServlet("/login")
public class LoginServlet extends BaseServlet { //TODO make a BaseHTTPServlet that abstracts away initialization
	private UserDao userDao;
	private LoginHandler loginHandler;
	
	
	public void init() {
		System.out.println("Login Servlet initialized");
		super.init();
		userDao = daoManager.getUserDao();
		loginHandler = new LoginHandler(daoManager);
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
//    	response.setContentType("text/html");  
//    	PrintWriter out = response.getWriter();  
//    	String name = request.getParameter("name");   
//      	out.print("Welcome, "+name);  
//      	HttpSession session=request.getSession();  
//    	session.setAttribute("user",userDao.getOrCreateUser(name));  
//    	out.close();
    	
    	if (loginHandler.login(request)) {
    		dispatch(request,response,"/profiles-list.jsp");
    	} else {
    		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
    				"Failed to login.");
    	}
    }
}
