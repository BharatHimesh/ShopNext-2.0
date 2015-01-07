package com.esper.event.object;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Bill {
	private String customerId;
	private String cartId;
	private List<BillItem> billItems;
	private long billAmount;
	private Date billDate;
	
	public List<BillItem> getBillItems() {
		return billItems;
	}
	public void setBillItems(List<BillItem> billItems) {
		this.billItems = billItems;
	}
	public long getBillAmount() {
		return billAmount;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public void setBillAmount(long billAmount) {
		this.billAmount = billAmount;
	}
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
}
