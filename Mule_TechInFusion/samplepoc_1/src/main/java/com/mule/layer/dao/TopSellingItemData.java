package com.mule.layer.dao;

import com.esper.event.object.TopSellingItemList;

public class TopSellingItemData {

	private static TopSellingItemList topSellingItemData = new TopSellingItemList();

	public static TopSellingItemList getTopSellingItemData() {
		return topSellingItemData;
	}

	public static void setTopSellingItemData(TopSellingItemList topSellingItemData) {
		TopSellingItemData.topSellingItemData = topSellingItemData;
	}
}
