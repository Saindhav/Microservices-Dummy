package com.redeem.mall.customer.service.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redeem.mall.customer.service.entity.CustomerDetails;
import com.redeem.mall.customer.service.repository.CustomerDetaisRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerDetaisRepository customerEntityRepository;

	public List<CustomerDetails> getAllCustomers() {
		List<CustomerDetails> customers = new ArrayList<CustomerDetails>();
		customerEntityRepository.findAll().forEach(customer -> customers.add(customer));
		return customers;
	}

	public int getLoyaltyPoints(String userName) {
		Map<String, Integer> loyaltyMap = new HashMap<String, Integer>();
		customerEntityRepository.findAll()
				.forEach(customer -> loyaltyMap.put(customer.getUserName(), customer.getLoyaltyPoints()));
		int loyalty;
		try {
			loyalty = loyaltyMap.get(userName);
		} catch (NullPointerException e) {
			return 99999;
		}
		return loyalty;
	}

}
