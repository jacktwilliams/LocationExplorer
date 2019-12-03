package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.County;
import entities.Job;
import entities.Profile;

@WebServlet("/locations")
public class LocationServlet extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
		processRequest(request, response);
		Session s = daoManager.startSession();
		String nav = request.getParameter("page");
		
		if (nav.equals("list")) {
			int profileId = Integer.parseInt(request.getParameter("profileId"));
			Profile p = daoManager.getProfileDao().getProfileById(profileId); //TODO: use better method for looking up profile here. For example, the profile has to be one of the logged in user's profiles, so we could look it up.
			County[] topCounties = daoManager.getCountyDao().
					getTopCountiesWithWeights(20, p.getWeightIncome(), p.getWeightHPrice(), p.getWeightPopulation());
			request.getSession().setAttribute("topCounties", topCounties);
			redirect(request, response, "/info-list.jsp");
		} else if (nav.equals("details")) {
			int cid = Integer.parseInt(request.getParameter("countyId"));
			County c = daoManager.getCountyDao().getCountyById(cid);
			List<Job> jobs = c.getListings();
			request.getSession().setAttribute("jobs", jobs);
			redirect(request, response, "/county-details.jsp");
		}
		s.close();
    }

}
