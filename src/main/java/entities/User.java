package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="User")
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	private int id;
	
	private String name;
	
	@Transient
	private List<Profile> profiles;
	
	//TODO: Find way to turn off eager fetching. Without it I am getting linked issue, even though I had a transaction started https://stackoverflow.com/questions/11746499/how-to-solve-the-failed-to-lazily-initialize-a-collection-of-role-hibernate-ex
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "user_fav_county",   
    joinColumns = { @JoinColumn(name= "userId") },   
    inverseJoinColumns = { @JoinColumn(name = "countyId") }) 
	private List<County> favorites = new ArrayList<County>();
	
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

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
	
	public void addProfile(Profile p) {
		p.setUser(this);
		if (profiles == null) {
			profiles = new ArrayList<Profile>();
		}
		this.profiles.add(p);
	}
	
	public List<County> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<County> favorites) {
		this.favorites = favorites;
	}
	
	public void addFavorite(County c) {
		getFavorites().add(c);
	}

	public void removeProfile(Profile p) {
		p.setUser(null);
		this.profiles.remove(p);
	}
}
