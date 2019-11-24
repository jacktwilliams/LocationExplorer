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

@WebServlet("/login")
public class LoginServlet extends HttpServlet { //TODO make a BaseHTTPServlet that abstracts away initialization
	private DaoManager daoManager;
	private UserDao userDao;
	
	public void init() {
		daoManager = (DaoManager) getServletContext().getAttribute("daoManager");
		userDao = daoManager.getUserDao();
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
		Session s = daoManager.getSessionF().openSession();
    	System.out.println(this.getClass().getCanonicalName());
    	response.setContentType("text/html");  
    	PrintWriter out = response.getWriter();  
    	//request.getRequestDispatcher("link.html").include(request, response);
    	Map<String, String[]> e = request.getParameterMap();
    	for (Entry<String, String[]> entry : e.entrySet()) {
    		System.out.println(entry.getKey() + " : " + entry.getValue());
    	}
    	String name = request.getParameter("name");   
      	out.print("Welcome, "+name);  
      	HttpSession session=request.getSession();  
    	session.setAttribute("user",userDao.getOrCreateUser(name));  
    	out.close();  
    	s.close();
    }
}
