package com.redeem.mall.customer.service.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redeem.mall.customer.service.entity.CustomerDetails;
import com.redeem.mall.customer.service.exception.CustomerServiceException;
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
		/*
		 *loyaltyMap contains user name as KEY and the total loyalty points of the user as VALUE.
		 */
		Map<String, Integer> loyaltyMap = new HashMap<String, Integer>();
		boolean userExists = checkIfUserExists(userName);
		if (userExists) {
			customerEntityRepository.findAll()
					.forEach(customer -> loyaltyMap.put(customer.getUserName(), customer.getLoyaltyPoints()));
			int loyalty;

			loyalty = loyaltyMap.get(userName);
			return loyalty;
		}else {
			throw new CustomerServiceException("User is Not available Currently");
		}

	}

	private boolean checkIfUserExists(String userName) {
		try {
			customerEntityRepository.findById(userName).get();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
