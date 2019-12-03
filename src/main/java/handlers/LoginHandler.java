package handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import dao.DaoManager;

public class LoginHandler extends BaseHandler {
	public LoginHandler(DaoManager daoManager) {
		super(daoManager);
	}

	public boolean login(HttpServletRequest request) {
		Session s = daoManager.getSessionF().openSession();
    	//request.getRequestDispatcher("link.html").include(request, response);
    	String name = request.getParameter("name");
    	if (name == null) {
    		return false;
    	}
      	HttpSession session=request.getSession();  
    	session.setAttribute("user",daoManager.getUserDao().getOrCreateUser(name).getId());  
    	s.close();
    	//TODO: maybe catch and return false?
    	return true;
	}
}
