package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Profile;
import entities.User;

@WebServlet("/profiles")
public class ProfileServlet extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
		processRequest(request, response);
		Session s =daoManager.startSession();

		String nav = request.getParameter("page"); //TODO: handling for null nav param
		if (nav.equals("list")) {
			List<Profile> profiles = daoManager.getUserDao().
					getProfiles(daoManager.getSessionUser(request));
			request.getSession().setAttribute("profiles", profiles);
			redirect(request,response, "/profiles-list.jsp");
		} else if (nav.equals("create")) {
			redirect(request, response, "/profile-form.jsp");
		}
		
		daoManager.closeSession();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
		processRequest(request, response);
		Session s = daoManager.startSession();
		double weightIncome = Double.parseDouble(request.getParameter("weightIncome"));
		double weightHPrice = Double.parseDouble(request.getParameter("weightHPrice"));
		double weightPopulation = Double.parseDouble(request.getParameter("weightPopulation"));
		Transaction t = s.beginTransaction();
		Profile p = new Profile(weightIncome, weightHPrice, weightPopulation);
		User u = daoManager.getSessionUser(request);
		u.addProfile(p);
		s.save(p);
		t.commit();
		redirect(request, response, "/profiles?page=list"); //important to re-fetch profiles
		daoManager.closeSession();
    }
}
