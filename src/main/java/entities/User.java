package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
//		p.setUser(this);
		if (profiles == null) {
			profiles = new ArrayList<Profile>();
		}
		this.profiles.add(p);
	}
	
	public void removeProfile(Profile p) {
//		p.setUser(null);
		this.profiles.remove(p);
	}
}
