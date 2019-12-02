package entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="Profile")
@Table(name="profile")
public class Profile {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int id;
	private double weightIncome;
	private double weightHPrice;
	private double weightPopulation;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	public Profile() {}
	
	public Profile(double weightIncome, double weightHPrice, double weightPopulation) {
		this.weightIncome = weightIncome;
		this.weightHPrice = weightHPrice;
		this.weightPopulation = weightPopulation;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getWeightIncome() {
		return weightIncome;
	}
	public void setWeightIncome(double weightIncome) {
		this.weightIncome = weightIncome;
	}
	public double getWeightHPrice() {
		return weightHPrice;
	}
	public void setWeightHPrice(double weightHPrice) {
		this.weightHPrice = weightHPrice;
	}
	public double getWeightPopulation() {
		return weightPopulation;
	}
	public void setWeightPopulation(double weightPopulation) {
		this.weightPopulation = weightPopulation;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Profile p = (Profile) o;
		return weightIncome == p.getWeightIncome() && weightHPrice == p.getWeightHPrice() && 
				weightPopulation == p.getWeightPopulation();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(new Object[]{weightIncome, weightHPrice, weightPopulation});
	}
}
