package com.redeem.mall.order.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
@PropertySource("classpath:application.properties")
public class PropertyUtil {

	private int maxItemAllowed;
	private int maxInventotyAllowed;
	private String custServURL;
	private String custServLoyaltyPointAPI;
	private String invtServURL;
	private String invtServGetTotalLoyaltyAPI;
	private String invtServChkInventoryAvailAPI;
	
	
	public int getMaxItemAllowed() {
		return maxItemAllowed;
	}
	public void setMaxItemAllowed(int maxItemAllowed) {
		this.maxItemAllowed = maxItemAllowed;
	}
	public int getMaxInventotyAllowed() {
		return maxInventotyAllowed;
	}
	public void setMaxInventotyAllowed(int maxInventotyAllowed) {
		this.maxInventotyAllowed = maxInventotyAllowed;
	}
	public String getCustServURL() {
		return custServURL;
	}
	public void setCustServURL(String custServURL) {
		this.custServURL = custServURL;
	}
	public String getCustServLoyaltyPointAPI() {
		return custServLoyaltyPointAPI;
	}
	public void setCustServLoyaltyPointAPI(String custServLoyaltyPointAPI) {
		this.custServLoyaltyPointAPI = custServLoyaltyPointAPI;
	}
	public String getInvtServURL() {
		return invtServURL;
	}
	public void setInvtServURL(String invtServURL) {
		this.invtServURL = invtServURL;
	}
	public String getInvtServGetTotalLoyaltyAPI() {
		return invtServGetTotalLoyaltyAPI;
	}
	public void setInvtServGetTotalLoyaltyAPI(String invtServGetTotalLoyaltyAPI) {
		this.invtServGetTotalLoyaltyAPI = invtServGetTotalLoyaltyAPI;
	}
	public String getInvtServChkInventoryAvailAPI() {
		return invtServChkInventoryAvailAPI;
	}
	public void setInvtServChkInventoryAvailAPI(String invtServChkInventoryAvailAPI) {
		this.invtServChkInventoryAvailAPI = invtServChkInventoryAvailAPI;
	}
	
}
