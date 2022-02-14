package ca.sheridancollege.bask.week7.beans;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Inventory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int itemId;
	private String itemName;
	private int quantity;
	private double price;

	
	public Inventory(String name, int qty, double price) {		
		setItemName(name);
		setQuantity(qty);
		setPrice(price);
	}
	
	public Inventory(int id, String name, int qty, double price) {
		setItemId(id);
		setItemName(name);
		setQuantity(quantity);
		setPrice(price);
	}
	
	public void setQuantity(int quantity) {
		if (quantity >= 0)
			this.quantity = quantity;
		else
			throw new IllegalArgumentException("Quantity must be 0 or  greater.");
	}
	
	public void setPrice(double price) {
		if (quantity >= 0)
			this.price = price;
		else
			throw new IllegalArgumentException("Price must be 0 or  greater.");
	}


}
