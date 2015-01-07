package com.esper.event.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class LowStockList {
	private Map<String, ArrayList<String>> itemLowInStock = new HashMap<String, ArrayList<String>>();

	public Map<String, ArrayList<String>> getItemLowInStock() {
		return itemLowInStock;
	}

	public void setItemLowInStock(Map<String, ArrayList<String>> itemLowInStock) {
		this.itemLowInStock = itemLowInStock;
	}

}
