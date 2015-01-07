package com.esper.event.object;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class BillItem {
	private String itemId;
	private String itemName;
	private long itemQty;
	private long itemPrice;
	private long discount;
	private long netPrice;
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public long getItemQty() {
		return itemQty;
	}
	public void setItemQty(long itemQty) {
		this.itemQty = itemQty;
	}
	public long getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(long itemPrice) {
		this.itemPrice = itemPrice;
	}
	public long getDiscount() {
		return discount;
	}
	public void setDiscount(long discount) {
		this.discount = discount;
	}
	public long getNetPrice() {
		return netPrice;
	}
	public void setNetPrice(long netPrice) {
		this.netPrice = netPrice;
	}
}
