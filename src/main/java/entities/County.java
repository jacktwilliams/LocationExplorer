package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "job_county",   
    joinColumns = { @JoinColumn(name= "jobListingId") },   
    inverseJoinColumns = { @JoinColumn(name = "countyId") }) 
	private List<Job> listings = new ArrayList<Job>();
	
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

	public List<Job> getListings() {
		return listings;
	}

	public void setListings(List<Job> listings) {
		this.listings = listings;
	}
	
	public void addListing(Job j) {
		//maybe this little scheme will prevent the issue in user's many to many user->county mapping where I had to user EAGER.
		List<Job> jobs = getListings();
		jobs.add(j);
		setListings(jobs);
	}

	public String toString() {
		return name + ", " + stateName + ": " + "Population = " + population + ", " +
				"Avg Home Price = " + avgHPrice + ", Avg Income = " + avgIncome;
	}
}
