package com.redeem.mall.order.service.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.redeem.mall.order.service.model.Customer;
import com.redeem.mall.order.service.model.InventoryDetails;

@RestController
@RequestMapping("/order")
public class OrderServiceRecource {

	@PostMapping(value = "/check-order", consumes = "application/json", produces = "application/json")
	public String checkOrder(@RequestBody Customer customer) {
		
		RestTemplate restTemplate =  new RestTemplate();;

		String msg = "ORDER SUCCESSFULL";

		List<InventoryDetails> itemLists = customer.getItemsList();

		if (itemLists.size() == 0) {
			msg = "PLEASE ADD ITEMS";
			return msg;
		}

		if (itemLists.size() > 5) {
			msg = "MAXIMUM 5 ITEM ALLOWED PER ORDER. PLEASE CHECK YOUR ORDER";
			return msg;
		}

		for (InventoryDetails items : itemLists) {
			if (items.getInventoryCount() > 10) {
				msg = "QUANTITY FOR " + items.getInventoryName() + " EXCEEDS 10. PLEASE CHECK YOUR ORDER";
				return msg;
			}
		}

		int loyaltyPointsAvailable = restTemplate.getForObject("http://localhost:8887/customer/get-loyalty-points/" + customer.getUserName(),
				Integer.class);
		
		
		int loyaltyPointsOrdered =restTemplate.postForObject("http://localhost:8888/inventory/get-total-loyalty-points", 
				itemLists, Integer.class);
		
		if(loyaltyPointsOrdered > loyaltyPointsAvailable) {
			msg = "YOUR ORDER PRICE IS "+loyaltyPointsOrdered+" AND YOUR AVAILABLE LOYALTY POINT IS "+loyaltyPointsAvailable+" KINDLY REDUCE YOUR QUANTITY";
			return msg;
		}
		
		Map<String, Integer> availabilityMap = restTemplate.postForObject("http://localhost:8888/inventory/check-inventory-availability", 
				itemLists, Map.class);
		
		if(availabilityMap.size() > 0) {
			msg = "AVAILABLE QUANTITY FOR";
			for (Map.Entry<String,Integer> entry : availabilityMap.entrySet())  
	            msg = msg +" "+ entry.getKey() + " IS " + entry.getValue(); 
			msg = msg+" PLEASE CHECK QUANTITY";
	    } 
		
		

		return msg;
	}

}
