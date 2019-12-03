package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.County;
import entities.User;

//TODO: fold this servlet into LocationServlet
@WebServlet("/favorites")
public class FavoriteServlet extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
		processRequest(request, response);
		daoManager.startSession();

		String nav = request.getParameter("page"); //TODO: handling for null nav param
		if (nav.equals("list")) {
			List<County> favorites = ((User) request.getSession().getAttribute("user")).getFavorites();
			request.getSession().setAttribute("favorites", favorites);
			redirect(request,response, "/favorites-list.jsp");
		} 
		daoManager.closeSession();
    }
}
