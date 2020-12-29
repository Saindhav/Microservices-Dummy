package com.redeem.mall.order.util;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.redeem.mall.order.exception.OrderServiceException;
import com.redeem.mall.order.model.InventoryDetails;
import com.redeem.mall.order.model.Response;
import com.redeem.mall.order.service.CustomerService;
import com.redeem.mall.order.service.InventoryService;

@Component
public class OrderUtil {
	
	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PropertyUtil props;
	
	public Response validateQuantities(List<InventoryDetails> itemLists) {
		String msg = null;
		try {
			if (itemLists.size() == 0)
				return new Response(HttpStatus.OK, "PLEASE ADD ITEMS");
			if (itemLists.size() > props.getMaxItemAllowed())
				return new Response(HttpStatus.OK, "MAXIMUM 5 ITEM ALLOWED PER ORDER. PLEASE CHECK YOUR ORDER");

			for (InventoryDetails items : itemLists) {
				if (items.getInventoryCount() > props.getMaxInventotyAllowed())
					msg = "QUANTITY FOR " + items.getInventoryName() + " EXCEEDS 10. PLEASE CHECK YOUR ORDER";
			}
			if (null != msg)
				return new Response(HttpStatus.OK, msg);
			return null;
		} catch (Exception e) {
			return new Response(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	public Response checkLoyaltyPointsAvailbility(String userName, List<InventoryDetails> itemLists) throws OrderServiceException {

			int loyaltyPointsAvailable;
			try {
				/*
				 * loyaltyPointsAvailable contains the value of total loyalty point a user has.
				 */
				loyaltyPointsAvailable = customerService.getAvailableLoyaltyPoints(userName);
			} catch (Exception e) {
				return new Response(HttpStatus.NOT_FOUND, "USER NOT FOUND");
			}
				
			/*
			 * loyaltyPointsOrdered contains the value of total loyalty point required for the order.
			 */
			int loyaltyPointsOrdered = inventoryService.getTotalLoyaltyCost(itemLists);
			if(-1 == loyaltyPointsOrdered)
				return new Response(HttpStatus.GATEWAY_TIMEOUT, "We're cheking your order. Please Wait!!");

			if (loyaltyPointsOrdered > loyaltyPointsAvailable) {
				return new Response(HttpStatus.OK,
						"YOUR ORDER PRICE IS " + loyaltyPointsOrdered + " AND YOUR AVAILABLE LOYALTY POINT IS "
								+ loyaltyPointsAvailable + " KINDLY REDUCE YOUR QUANTITY");
			} else {
				return null;
			}
	}
	
	public Response checkInventoryAvailability(List<InventoryDetails> itemLists) {
		Map<String, Integer> availabilityMap = inventoryService.getInventoryAvailabilityMap(itemLists);
		if(null != availabilityMap && availabilityMap.size() > 0 && availabilityMap.get("MINUS") == -1) {
			return new Response(HttpStatus.GATEWAY_TIMEOUT, "We're cheking your order. Please Wait!!");
		}

		if (null != availabilityMap && availabilityMap.size() > 0) {
			String msg = "AVAILABLE QUANTITY FOR";
			for (Map.Entry<String, Integer> entry : availabilityMap.entrySet())
				msg = msg + " " + entry.getKey() + " IS " + entry.getValue();
			msg = msg + " PLEASE CHECK QUANTITY";
			
			return new Response(HttpStatus.OK, msg);
		}else {
			return null;
		}
	}

}
