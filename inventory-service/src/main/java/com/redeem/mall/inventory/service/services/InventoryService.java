package com.redeem.mall.inventory.service.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redeem.mall.inventory.service.model.InventoryDetails;
import com.redeem.mall.inventory.service.repository.InventoryDetailsRepository;

@Service
public class InventoryService {

	@Autowired
	InventoryDetailsRepository inventoryDetailsRepository;

	public List<InventoryDetails> getAllInventories() {
		List<InventoryDetails> inventoryDetails = new ArrayList<InventoryDetails>();
		inventoryDetailsRepository.findAll().forEach(inventory -> inventoryDetails.add(inventory));
		return inventoryDetails;
	}

	public int getTotalLoyaltyPoints(List<InventoryDetails> inventoryDetailsList) {
		Map<String, Integer> loyaltyMap = new HashMap<String, Integer>();
		inventoryDetailsRepository.findAll()
				.forEach(inventory -> loyaltyMap.put(inventory.getInventoryName(), inventory.getLoyaltyPoints()));
		int totalLoyaltyPoints = 0;
		for (InventoryDetails inventoryDetail : inventoryDetailsList) {
			
			String invName = inventoryDetail.getInventoryName();
			int quantity = inventoryDetail.getInventoryCount();
			int loyaltyPoints = loyaltyMap.get(invName)*quantity;
			
			totalLoyaltyPoints = totalLoyaltyPoints + loyaltyPoints;
		}
		return totalLoyaltyPoints;
	}
	
	public Map<String, Integer> checkInventoryAvailability(List<InventoryDetails> inventoryDetailsList) {
		Map<String, Integer> quantityMap = new HashMap<String, Integer>();
		Map<String, Integer> availabilityMap = new HashMap<String, Integer>();
		inventoryDetailsRepository.findAll()
				.forEach(inventory -> quantityMap.put(inventory.getInventoryName(), inventory.getInventoryCount()));
		for (InventoryDetails inventoryDetail : inventoryDetailsList) {
			
			String invName = inventoryDetail.getInventoryName();
			int quantityOrdered = inventoryDetail.getInventoryCount();
			int quantityAvailable = quantityMap.get(invName);
			
			if(quantityOrdered > quantityAvailable) {
				availabilityMap.put(invName, quantityAvailable);
			}
		}
		
		return availabilityMap;
	}

}
