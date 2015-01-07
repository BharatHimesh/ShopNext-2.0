package com.esper.event.object;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Customer {
	private String customerId;
	private String customerName;
	private String contactInfo;
	//deal criteria
	private long loyaltyPoints;
	private double avgPastBillAmount;
	private String frequentBuy;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public long getLoyaltyPoints() {
		return loyaltyPoints;
	}
	public void setLoyaltyPoints(long loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
	
	public String getFrequentBuy() {
		return frequentBuy;
	}
	public void setFrequentBuy(String frequentBuy) {
		this.frequentBuy = frequentBuy;
	}
	public double getAvgPastBillAmount() {
		return avgPastBillAmount;
	}
	public void setAvgPastBillAmount(double avgPastBillAmount) {
		this.avgPastBillAmount = avgPastBillAmount;
	}
}
