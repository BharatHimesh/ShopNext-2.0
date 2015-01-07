package com.mule.layer.dao;

import com.esper.event.object.TopBuyerList;

public class TopBuyerData {

	private static TopBuyerList topBuyerData = new TopBuyerList();
	
	public static TopBuyerList getTopBuyerData() {
		return topBuyerData;
	}
	
	public static void setTopBuyerData(TopBuyerList topBuyerList) {		
		topBuyerData=topBuyerList;
	}
}
