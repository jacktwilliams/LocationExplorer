package dao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.County;
import entities.Profile;
import entities.User;

public class UserDao {
	DaoManager daoManager;
	
	public UserDao(DaoManager daoManager) {
		System.out.println("Initializing user dao.");
		this.daoManager = daoManager;
	}
	
	public User getOrCreateUser(String username) {
		Session s = daoManager.getSessionF().getCurrentSession();
		Transaction transaction = s.beginTransaction();
		Query q = s.createQuery("FROM User U WHERE U.name = :username");
		q.setParameter("username", username);
		List<User> user = (List<User>) q.list();
		
		if (user.size() > 0) {
			System.out.println("Found user");
			transaction.commit();
			return user.get(0);
		} else {
			System.out.println("Creating user");
			User newU = new User();
			newU.setName(username);
			s.save(newU);
			transaction.commit();
			return newU;
		}
	}
	
	public List<Profile> getProfiles(User user) {
		if (user.getProfiles() != null) {
			return user.getProfiles();
		}
		Session s = daoManager.getSessionF().getCurrentSession();
		Transaction transaction = s.beginTransaction();
		Query q = s.createQuery("From Profile WHERE user.id = :uid");
		q.setParameter("uid", user.getId());
		List<Profile> profiles = (List<Profile>) q.list();
		transaction.commit();
		return profiles;
	}
	
	public User getUserById(int id, Session s) {
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("FROM User WHERE id = :id");
		q.setParameter("id", id);
		User u = (User) q.list().get(0);
		t.commit();
		s.saveOrUpdate(u);
		return u;
	}
}
