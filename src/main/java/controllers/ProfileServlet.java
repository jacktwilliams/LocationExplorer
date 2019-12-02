package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import entities.Profile;
import entities.User;

@WebServlet("/profiles")
public class ProfileServlet extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
		processRequest(request, response);

		String nav = request.getParameter("page"); //TODO: handling for null nav param
		if (nav.equals("list")) {
			daoManager.startSession();
			List<Profile> profiles = daoManager.getUserDao().
					getProfiles((User)(request.getSession().getAttribute("user")));
			daoManager.closeSession();
			request.getSession().setAttribute("profiles", profiles);
			redirect(request,response, "/profiles-list.jsp");
		} else if (nav.equals("create")) {
			dispatch(request, response, "/profile-form.jsp");
		}
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
		System.out.println("Made it to profile"); //testing if my dispatch is keeping the request as a POST
    }
}
