package dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Profile;

public class ProfileDao {
	DaoManager daoManager;
	
	public ProfileDao(DaoManager daoManager) {
		this.daoManager = daoManager;
	}
	
	public void deleteProfile(Profile p) {
		Session s = daoManager.getCurrentSession();
		Transaction t = s.beginTransaction();
		s.delete(p);
		t.commit();
	}
	
	public void deleteProfile(int id) {
		deleteProfile(getProfileById(id));
	}
	
	public Profile getProfileById(int id) {
		Session s = daoManager.getCurrentSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("FROM Profile WHERE id = :id");
		q.setParameter("id", id);
		Profile p = (Profile) q.list().get(0);
		t.commit();
		return p;
	}
}
