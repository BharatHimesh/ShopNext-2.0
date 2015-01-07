package com.mule.layer.dao;

import java.util.HashMap;
import java.util.Map;

import com.esper.event.object.Item;

public class ItemData {

	private static Map<String, Item> itemData = new HashMap<String, Item>();
	
	public static Item getItemData(String itemId) {
		Item outputItem = null;
		
		if(itemData.containsKey(itemId)) {
			outputItem = itemData.get(itemId);
		}
		
		return outputItem;
	}
	
	public static void addItemData(String itemId, Item inputItemData) {		
		itemData.put(itemId, inputItemData);		
	}
}
