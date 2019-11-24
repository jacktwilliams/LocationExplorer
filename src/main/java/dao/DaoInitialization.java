package dao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DaoInitialization implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Context initialized");
		ServletContext ctx = sce.getServletContext();
		DaoManager daoManager = new DaoManager();
		ctx.setAttribute("daoManager", daoManager);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
