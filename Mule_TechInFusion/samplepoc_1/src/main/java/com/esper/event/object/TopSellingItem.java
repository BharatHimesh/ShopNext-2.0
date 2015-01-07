package com.esper.event.object;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class TopSellingItem implements Comparable<TopSellingItem> {
	private String itemId;
	private String itemName;
	private long totalSalesQty;
	private String segmentName;
	
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
	public long getTotalSalesQty() {
		return totalSalesQty;
	}
	public void setTotalSalesQty(long totalSalesQty) {
		this.totalSalesQty = totalSalesQty;
	}
	
	public int compareTo(TopSellingItem input) {
        
		if(totalSalesQty==input.getTotalSalesQty()) {
			return 0;
		}
		else if(totalSalesQty<input.getTotalSalesQty()) {
			return 1;
		}
		else {
			return -1;
		}
    }
	public String getSegmentName() {
		return segmentName;
	}
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
}
