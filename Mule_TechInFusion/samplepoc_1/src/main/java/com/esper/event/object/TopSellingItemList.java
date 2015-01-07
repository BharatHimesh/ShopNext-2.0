package com.esper.event.object;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class TopSellingItemList {

	private List<TopSellingItem> topSellingItem;

	public List<TopSellingItem> getTopSellingItem() {
		return topSellingItem;
	}

	public void setTopSellingItem(List<TopSellingItem> topSellingItem) {
		this.topSellingItem = topSellingItem;
	}
}
