package com.redeem.mall.customer.service.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redeem.mall.customer.service.entity.CustomerDetails;

@RestController
@RequestMapping("/customer")
public class CustomerServiceResource {
	
	@Autowired
	CustomerService customerService;

    @GetMapping("/get-all-customers")
    public List<CustomerDetails> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    
    @GetMapping("/get-loyalty-points/{userName}")
    public int getLoyaltyPoints(@PathVariable("userName") String userName) {
        return customerService.getLoyaltyPoints(userName);
    }

}
