package com.redeem.mall.order.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redeem.mall.order.exception.OrderServiceException;
import com.redeem.mall.order.model.Customer;
import com.redeem.mall.order.model.InventoryDetails;
import com.redeem.mall.order.model.Response;
import com.redeem.mall.order.util.OrderUtil;

@RestController
@RequestMapping("/order")
public class OrderServiceRecource {

	@Autowired
	private OrderUtil orderUtil;

	@PostMapping(value = "/order-details", consumes = "application/json", produces = "application/json")
	public Response checkOrder(@RequestBody Customer customer) throws OrderServiceException {
		Response response = null;
		try {
			List<InventoryDetails> itemLists = customer.getItemsList();
			
			response = orderUtil.validateQuantities(itemLists);

			if (null != response)
				return response;

			response = orderUtil.checkLoyaltyPointsAvailbility(customer.getUserName(), itemLists);

			if (null != response)
				return response;
			
			response = orderUtil.checkInventoryAvailability(itemLists);

			if (null != response)
				return response;
		
		} catch (RuntimeException e) {
			throw new OrderServiceException(response.getStatusMsg());
		}

		return new Response(HttpStatus.OK, "ORDER SUCCESSFULL");
	}
}
