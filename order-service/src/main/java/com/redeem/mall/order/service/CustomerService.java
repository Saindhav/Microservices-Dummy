package com.redeem.mall.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.redeem.mall.order.util.PropertyUtil;

@Service
public class CustomerService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PropertyUtil props;

	@HystrixCommand(fallbackMethod = "getAvailableLoyaltyPointsFallback")
	public int getAvailableLoyaltyPoints(String userName) {
		int loyaltyPointsAvailable = restTemplate
				.getForObject(props.getCustServURL()+props.getCustServLoyaltyPointAPI() + userName, Integer.class);
		return loyaltyPointsAvailable;
	}

	private int getAvailableLoyaltyPointsFallback(String userName) {
		return -1;
	}

}
