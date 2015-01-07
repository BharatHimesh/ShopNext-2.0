package com.mule.layer.dao;

import com.esper.event.object.LowStockList;

public class LowStockData {

	private static LowStockList lowStockData = new LowStockList();

	public static LowStockList getLowStockData() {
		return lowStockData;
	}

	public static void setLowStockData(LowStockList lowStockData) {
		LowStockData.lowStockData = lowStockData;
	}
}
