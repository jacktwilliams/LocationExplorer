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
		processRequest(request, response);
		Session s = daoManager.startSession();
		double weightIncome = Double.parseDouble(request.getParameter("weightIncome"));
		double weightHPrice = Double.parseDouble(request.getParameter("weightHPrice"));
		double weightPopulation = Double.parseDouble(request.getParameter("weightPopulation"));
		Transaction t = s.beginTransaction();
		Profile p = new Profile(weightIncome, weightHPrice, weightPopulation);
		User u = (User) request.getSession().getAttribute("user");
		u.addProfile(p);
		s.save(p);
		t.commit();
		redirect(request, response, "/profiles-list.jsp");
		daoManager.closeSession();
    }
}
