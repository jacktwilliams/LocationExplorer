package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

/**
 * Translates GETs from our jsp pages into the appropriate DAO actions and then handles navigation.
 */
@WebServlet("/action")
public class ActionServlet extends BaseServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
		processRequest(request, response);
		Session s = daoManager.startSession();
		String action = request.getParameter("action");
		
		if (action.equals("deleteProfile")) {
			int pid = Integer.parseInt(request.getParameter("id"));
			daoManager.getProfileDao().deleteProfile(pid);
			redirect(request, response, "/profiles?page=list");
		}
		s.close();
    }
}
