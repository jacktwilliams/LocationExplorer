package thirdparty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.County;
import entities.Profile;
import entities.State;
import entities.User;

public class HibernateTests {
	
	public static SessionFactory sessionF;
	
	//means
	public static final int avgIncome = 53611;
	public static final int avgHPrice = 213427;
	public static final int avgPop = 231225;
	
	//standard deviations
	public static final int stdIncome = 13529;
	public static final int stdHPrice = 118603;
	public static final int stdPop = 499656;
	
	@BeforeAll
	static void setUp() {
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
	
	@Test
	void checkFetching() {
		Session s = sessionF.openSession();
		s.beginTransaction();
		List<State> states = (List<State>) s.createQuery("FROM State").list();
		assertEquals(50, states.size());
		s.close();
	}
	
	@Test
	void calculateAveragesForCounties() {
		Session s = sessionF.openSession();
		s.beginTransaction();
		List<County> counties = (List<County>) s.createQuery("FROM County").list();
		long income = 0;
		long hprice = 0;
		long population = 0;
		for (County c : counties) {
			income += c.getAvgIncome();
			hprice += c.getAvgHPrice();
			population += c.getPopulation();
		}
		int avgIncome = (int) income / counties.size();
		int avghprice = (int) hprice / counties.size();
		int avgPop = (int) population / counties.size();
		System.out.println(avgIncome);
		System.out.println(avghprice);
		System.out.println(avgPop);
		s.close();
	}
	
	@Test
	void calculateStdDevForCounties() {
		Session s = sessionF.openSession();
		s.beginTransaction();
		List<County> counties = (List<County>) s.createQuery("FROM County").list();
		long income = 0;
		long hprice = 0;
		long population = 0;
		for (County c : counties) {
			income += Math.pow((c.getAvgIncome() - avgIncome), 2);
			hprice += Math.pow((c.getAvgHPrice() - avgHPrice), 2);
			population += Math.pow((c.getPopulation() - avgPop), 2);
		}
		double stdIncome = Math.sqrt(income / counties.size());
		double stdhprice = Math.sqrt(hprice / counties.size());
		double stdPop = Math.sqrt(population / counties.size());
		
		System.out.println(stdIncome);
		System.out.println(stdhprice);
		System.out.println(stdPop);
		s.close();
	}
	
	@Test
	void createUser() {
		Session s = sessionF.openSession();
		s.beginTransaction();
		User u = new User();
		u.setName("testCreateUser");
		Profile p = new Profile();
		p.setWeightHPrice(-1);
		p.setWeightIncome(1);
		p.setWeightPopulation(-1);
		u.addProfile(p);
		s.save(u);
		s.getTransaction().commit();
		System.out.println("Check for user in database.");
		s.beginTransaction();
		s.delete(u);
		s.delete(p);
		s.getTransaction().commit();
		s.close();
	}
	
	@Test
	void checkManyToMany() {
		Session s = sessionF.openSession();
		Transaction t = s.beginTransaction();
		User u = new User();
		u.setName("checkManyToMany");
		s.save(u);
		County c = (County) s.createQuery("FROM County").list().get(0);
		u.addFavorite(c);
		t.commit();
		System.out.println("Check for user with favorited county");
		t = s.beginTransaction();
		s.delete(u);
		t.commit();
		s.close();
	}
}
