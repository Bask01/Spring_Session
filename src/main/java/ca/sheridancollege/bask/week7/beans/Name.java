package ca.sheridancollege.bask.week7.beans;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Name implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	private String name;

	
	public Name(String name) {
		
		setName(name);
	}
	
	

}
