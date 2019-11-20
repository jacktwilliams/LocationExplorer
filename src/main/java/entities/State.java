package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name="State")
@Table (name="state")
public class State {
	@Id
	private String stateName;
	
	public String getStateName() {
		return stateName;
	}
}
