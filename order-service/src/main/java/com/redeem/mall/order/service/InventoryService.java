package com.redeem.mall.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.redeem.mall.order.model.InventoryDetails;
import com.redeem.mall.order.util.PropertyUtil;

@Service
public class InventoryService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PropertyUtil props;
	
	@HystrixCommand(fallbackMethod = "getTotalLoyaltyCostFallback")
	public int getTotalLoyaltyCost(List<InventoryDetails> itemLists) {
		int loyaltyPointsOrdered =restTemplate.postForObject(props.getInvtServURL()+props.getInvtServGetTotalLoyaltyAPI(), 
				itemLists, Integer.class);
		
		return loyaltyPointsOrdered;
	}
	
	@HystrixCommand(fallbackMethod = "getInventoryAvailabilityMapFallback")
	public Map<String, Integer> getInventoryAvailabilityMap(List<InventoryDetails> itemLists){
		Map<String, Integer> inventoryAvailabilityMap = restTemplate.postForObject(props.getInvtServURL()+props.getInvtServGetTotalLoyaltyAPI(), 
				itemLists, Map.class);
		
		return inventoryAvailabilityMap;
	}
	
	private int getTotalLoyaltyCostFallback(List<InventoryDetails> itemLists) {
		return -1;
	}
	
	private Map<String, Integer> getInventoryAvailabilityMapFallback(List<InventoryDetails> itemLists) {
		Map<String, Integer> fallbackMap = new HashMap<String, Integer>();
		fallbackMap.put("MINUS", -1);
		return fallbackMap;
	}

}
