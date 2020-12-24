package com.redeem.mall.inventory.service.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redeem.mall.inventory.service.model.InventoryDetails;
import com.redeem.mall.inventory.service.services.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryServiceResource {
	
	@Autowired
	InventoryService inventoryService;

    @GetMapping("/get-all-inventories")
    public List<InventoryDetails> getAllInventories() {
        return inventoryService.getAllInventories();
    }
    
    @PostMapping(value = "/get-total-loyalty-points", consumes = "application/json", produces = "application/json")
    public int getTotalLoyaltyPoints(@RequestBody List<InventoryDetails> inventoryDetails) {
    	
        return inventoryService.getTotalLoyaltyPoints(inventoryDetails);
    }
    
    @PostMapping(value = "/check-inventory-availability", consumes = "application/json", produces = "application/json")
    public Map<String, Integer> checkInventoryAvailability(@RequestBody List<InventoryDetails> inventoryDetails) {
    	
        return inventoryService.checkInventoryAvailability(inventoryDetails);
    }
   

}
