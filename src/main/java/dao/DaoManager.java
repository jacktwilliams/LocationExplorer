package dao;

import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.County;
import entities.Job;
import entities.Profile;
import entities.State;
import entities.User;

public class DaoManager {
	private UserDao userDao;
	private CountyDao countyDao;
	private ProfileDao profileDao;
	private SessionFactory sessionF;
	
	public DaoManager() {
		System.out.println("Initializing dao manager");
		this.userDao = new UserDao(this);
		this.countyDao = new CountyDao(this);
		this.profileDao = new ProfileDao(this);
		
		StandardServiceRegistry standardRegistry =
		        new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		
		Metadata metadata = new MetadataSources(standardRegistry)
				.addAnnotatedClass(State.class)
				.addAnnotatedClass(County.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Profile.class)
				.addAnnotatedClass(Job.class)
				.addPackage("entities")
				.getMetadataBuilder()
				.applyImplicitSchemaName("mydb").build();

		sessionF = metadata.getSessionFactoryBuilder().build();
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public CountyDao getCountyDao() {
		return countyDao;
	}

	public ProfileDao getProfileDao() {
		return profileDao;
	}

	public SessionFactory getSessionF() {
		return sessionF;
	}
	
	public Session startSession() {
		return sessionF.openSession();
	}
	
	public Session getCurrentSession() {
		return sessionF.getCurrentSession();
	}
	
	public Transaction beginTransaction() {
		return sessionF.getCurrentSession().beginTransaction();
	}
	
	public void closeSession() {
		sessionF.getCurrentSession().close();
	}
	
	public User getSessionUser(HttpServletRequest r) {
		User u = (User) r.getSession().getAttribute("user");
		Transaction t = beginTransaction();
		getCurrentSession().saveOrUpdate(u);
		t.commit();
		return u;
	}
}
