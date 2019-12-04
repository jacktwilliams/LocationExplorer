package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import entities.County;

@WebServlet("/ws/locations")
public class LocationService extends BaseServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
		Session s = daoManager.getSessionF().openSession();
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		County[] topCounties = daoManager.getCountyDao().
				getTopCountiesWithWeights(10, -1, -1, -1);
		for (County c : topCounties) {
			pw.write(c + "\n");
		}
		pw.close();
		response.setStatus(HttpServletResponse.SC_OK);
		s.close();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Session s = daoManager.getSessionF().openSession();
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		County c = daoManager.getCountyDao().getCountyByName(request.getParameter("county"));
		String title = request.getParameter("title");
		String desc = request.getParameter("description");
		daoManager.getCountyDao().addJobForCounty(c, title, desc);
		pw.write("OK\n");
		s.close();
    }
}
