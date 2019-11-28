package dao;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import entities.County;

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
				double z = (c.getAvgHPrice() - avgHPrice) / stdHPrice;
				score = updateScore(score, z, avgHPrice, c.getAvgHPrice(), avgHPriceW);
				z = (c.getAvgIncome() - avgIncome) / stdHPrice;
				score = updateScore(score, z, avgIncome, c.getAvgIncome(), avgIncomeW);
				z = (c.getPopulation() - avgPop) / stdPop;
				score = updateScore(score, z, avgPop, c.getPopulation(), populationW);
				return score;
			}
		});
		
		return Arrays.copyOfRange(counties.stream().sorted(comparator).toArray(County[]::new), 0, size);
	}
	
	private double updateScore(double score, double z, double avg, double point, double weight) {
		if (point < avg) {
			return score - (z * weight);
		} else {
			return score + (z * weight);
		}
	}
}
