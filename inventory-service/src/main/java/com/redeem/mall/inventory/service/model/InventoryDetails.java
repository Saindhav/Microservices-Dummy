package com.redeem.mall.inventory.service.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InventoryDetails {

	@Id
	public String inventoryName;
	public int inventoryCount;
	public int loyaltyPoints;

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public int getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

}
