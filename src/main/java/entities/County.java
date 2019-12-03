package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity (name="County")
@Table (name="county")
public class County {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int id;
	
	private String name;
	
	private int population;
	
	private int avgIncome;
	
	private int avgHPrice;
	
	private String stateName;
	
	@Transient
	private int rank;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getAvgIncome() {
		return avgIncome;
	}

	public void setAvgIncome(int avgIncome) {
		this.avgIncome = avgIncome;
	}

	public int getAvgHPrice() {
		return avgHPrice;
	}

	public void setAvgHPrice(int avgHPrice) {
		this.avgHPrice = avgHPrice;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String toString() {
		return name + ", " + stateName + ": " + "Population = " + population + ", " +
				"Avg Home Price = " + avgHPrice + ", Avg Income = " + avgIncome;
	}
}
