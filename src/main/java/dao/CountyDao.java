package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.County;
import entities.Job;
import entities.User;

public class CountyDao {
	private DaoManager daoManager;
	
	//means
	public static final int avgIncome = 53611;
	public static final int avgHPrice = 213427;
	public static final int avgPop = 231225;
	
	//standard deviations
	public static final int stdIncome = 13529;
	public static final int stdHPrice = 118603;
	public static final int stdPop = 499656;
	
	public CountyDao(DaoManager daoManager) {
		this.daoManager = daoManager;
	}
	
	public County[] getTopCountiesWithWeights(int size, double avgIncomeW, 
			double avgHPriceW, double populationW) {
		Session s = daoManager.getSessionF().getCurrentSession();
		Transaction t = s.beginTransaction();
		List<County> counties = (List<County>) s.createQuery("FROM County").list();
		t.commit();
		Comparator<County> comparator = Comparator.comparingDouble(new ToDoubleFunction<County>() {
			@Override
			public double applyAsDouble(County c) {
				double score = 0;
				// score += z-score * weight
				score += ((c.getAvgHPrice() - avgHPrice) / (double)stdHPrice) * avgHPriceW;
				score += ((c.getAvgIncome() - avgIncome) / (double)stdIncome) * avgIncomeW;
				score += ((c.getPopulation() - avgPop) / (double)stdPop) * populationW;
				return 0 - score; //hackish way to sort in descending order
			}
		});
		
		County[] res =Arrays.copyOfRange(counties.stream().sorted(comparator).toArray(County[]::new), 0, size);
		int rank = 1;
		for (County c : res) {
			c.setRank(rank++);
		}
		return res;
	}
	
	public County getCountyById(int id) {
		Session s = daoManager.getCurrentSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("FROM County WHERE id = :id");
		q.setParameter("id", id);
		County c = (County) q.list().get(0);
		t.commit();
		return c;
	}
	
	public County getCountyByName(String name) {
		Session s = daoManager.getCurrentSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("FROM County WHERE name = :name");
		q.setParameter("name", name);
		County c = (County) q.list().get(0);
		t.commit();
		return c;
	}
	
	public void addJobForCounty(County c, String title, String description) {
		Session s = daoManager.getCurrentSession();
		Transaction t = s.beginTransaction();
		Job j = new Job();
		j.setTitle(title);
		j.setDescription(description);
		s.save(j);
		s.flush();
		s.refresh(j);
		SQLQuery q = s.createSQLQuery("insert into job_county (countyId, jobListingId) VALUES " +
				"(" + c.getId() + ", " + j.getId() + ");");
		q.executeUpdate();
		t.commit();
	}
	
	public Job getJobById(int jid) {
		Session s = daoManager.getCurrentSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("FROM Job WHERE id = :id");
		q.setParameter("id", jid);
		Job j = (Job) q.list().get(0);
		t.commit();
		return j;
	}
	
	public List<Job> getJobsForCounty(int countyId) {
		Session s = daoManager.getCurrentSession();
		Transaction t = s.beginTransaction();
		SQLQuery q = s.createSQLQuery("select * from job_county");
		List<Object[]> res = (List<Object[]>) q.list();
		List<Job> jobs = new ArrayList<Job>();
		for (Object[] tup : res) {
			if (tup[0].equals(countyId)) {
				jobs.add(getJobById((int)tup[1]));
			}
		}
		return jobs;
	}
}
