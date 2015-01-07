package com.esper.event.object;

public class Item {
	private String itemId;
	private String itemName;
	private long pricePerQty;
	private long qtyInStock;
	private long totalSalesQty;
	private long perishableDays;
	private String segment;
	
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
	public long getPricePerQty() {
		return pricePerQty;
	}
	public void setPricePerQty(long pricePerQty) {
		this.pricePerQty = pricePerQty;
	}
	public long getQtyInStock() {
		return qtyInStock;
	}
	public void setQtyInStock(long qtyInStock) {
		this.qtyInStock = qtyInStock;
	}
	public long getTotalSalesQty() {
		return totalSalesQty;
	}
	public void setTotalSalesQty(long totalSalesQty) {
		this.totalSalesQty = totalSalesQty;
	}
	public long getPerishableDays() {
		return perishableDays;
	}
	public void setPerishableDays(long perishableDays) {
		this.perishableDays = perishableDays;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
}
