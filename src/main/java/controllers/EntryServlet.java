package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class EntryServlet extends BaseServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
		processRequest(request, response);
    }
}
