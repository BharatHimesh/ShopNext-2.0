package com.esper.event.object;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class TopBuyer implements Comparable<TopBuyer>{
	private String customerId;
	private String customerName;
	private long totalBill;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public long getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(long totalBill) {
		this.totalBill = totalBill;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public int compareTo(TopBuyer input) {
        
		if(totalBill==input.getTotalBill()) {
			return 0;
		}
		else if(totalBill<input.getTotalBill()) {
			return 1;
		}
		else {
			return -1;
		}
    }
}
