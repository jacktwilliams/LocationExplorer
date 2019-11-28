package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entities.County;
import entities.Profile;
import entities.State;
import entities.User;

public class DaoManager {
	private UserDao userDao;
	private CountyDao countyDao;
	private SessionFactory sessionF;
	
	public DaoManager() {
		System.out.println("Initializing dao manager");
		this.userDao = new UserDao(this);
		this.countyDao = new CountyDao(this);
		
		StandardServiceRegistry standardRegistry =
		        new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		
		Metadata metadata = new MetadataSources(standardRegistry)
				.addAnnotatedClass(State.class)
				.addAnnotatedClass(County.class)
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Profile.class)
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

	public SessionFactory getSessionF() {
		return sessionF;
	}
}
