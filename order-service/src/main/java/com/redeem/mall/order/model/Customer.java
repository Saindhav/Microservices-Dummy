package com.redeem.mall.order.service.model;

import java.util.List;

public class Customer {
	
	public String userName;
	public String firstName;
	public String lastName;
	public List<InventoryDetails> itemsList;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<InventoryDetails> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<InventoryDetails> itemsList) {
		this.itemsList = itemsList;
	}
	
	
}
