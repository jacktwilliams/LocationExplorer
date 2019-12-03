package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import handlers.LoginHandler;  

@WebServlet("/login")
public class LoginServlet extends BaseServlet {
	private LoginHandler loginHandler;
	
	
	public void init() {
		System.out.println("Login Servlet initialized");
		super.init();
		loginHandler = new LoginHandler(daoManager);
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
    	if (loginHandler.login(request)) {
    		redirect(request,response,"/profiles?page=list");
    	} else {
    		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
    				"Failed to login.");
    	}
    }
}
