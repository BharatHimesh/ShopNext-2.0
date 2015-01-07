package com.esper.config.loader;

import java.util.ArrayList;
import java.util.List;

import com.esper.event.object.Item;
import com.esper.event.object.TopBuyer;
import com.esper.event.object.TopBuyerList;
import com.esper.event.object.TopSellingItem;
import com.esper.event.object.TopSellingItemList;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.mule.layer.dao.ItemData;
import com.mule.layer.dao.TopBuyerData;
import com.mule.layer.dao.TopSellingItemData;

public class DataLoader {
	private static final EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
	private static final EPRuntime runtime = epService.getEPRuntime();
	
	/**
	 * Adds standard Esper listeners into runtime Esper engine.
	 */
    public static void loadInitialConfigData() {
    	itemInitialLoad();
    	topBuyerInitialLoader();
    	topSellingItemInitialLoader();
    	topHeatMap();
    }
    
    private static void topHeatMap() {
    	
	}

	/**
     * Loads all the Items into Esper during startup
     */
    private static void itemInitialLoad() {
    	
    	Item newItem1 = new Item();
    	
    	newItem1.setItemId("A0001J1");
    	newItem1.setItemName("Butter 50gms");
    	newItem1.setPricePerQty(40);
    	newItem1.setQtyInStock(100);
    	newItem1.setTotalSalesQty(45);
    	newItem1.setPerishableDays(10);
    	newItem1.setSegment("S1");
    	
    	Item newItem2 = new Item();
    	
    	newItem2.setItemId("A0001J2");
    	newItem2.setItemName("Fresh Bread");
    	newItem2.setPricePerQty(26);
    	newItem2.setQtyInStock(60);
    	newItem2.setTotalSalesQty(70);
    	newItem2.setPerishableDays(5);
    	newItem2.setSegment("S1");
    	
    	Item newItem3 = new Item();
    	
    	newItem3.setItemId("A0001J3");
    	newItem3.setItemName("Fruit Jam 100gms");
    	newItem3.setPricePerQty(80);
    	newItem3.setQtyInStock(80);
    	newItem3.setTotalSalesQty(10);
    	newItem3.setPerishableDays(20);
    	newItem3.setSegment("S1");
    	
    	Item newItem4 = new Item();
    	
    	newItem4.setItemId("B0001J1");
    	newItem4.setItemName("Knife Set");
    	newItem4.setPricePerQty(150);
    	newItem4.setQtyInStock(100);
    	newItem4.setTotalSalesQty(2);
    	newItem4.setPerishableDays(999999);
    	newItem4.setSegment("S2");
    	
    	Item newItem5 = new Item();
    	
    	newItem5.setItemId("B0001J2");
    	newItem5.setItemName("DineWare Set");
    	newItem5.setPricePerQty(750);
    	newItem5.setQtyInStock(50);
    	newItem5.setTotalSalesQty(1);
    	newItem5.setPerishableDays(999999);
    	newItem5.setSegment("S2");
    	
    	Item newItem6 = new Item();
    	
    	newItem6.setItemId("B0001J3");
    	newItem6.setItemName("Mixer Grinder");
    	newItem6.setPricePerQty(1750);
    	newItem6.setQtyInStock(24);
    	newItem6.setTotalSalesQty(2);
    	newItem6.setPerishableDays(999999);
    	newItem6.setSegment("S2");
    	
    	Item newItem7 = new Item();
    	
    	newItem7.setItemId("C0001J1");
    	newItem7.setItemName("Hardware Toolkit");
    	newItem7.setPricePerQty(2500);
    	newItem7.setQtyInStock(15);
    	newItem7.setTotalSalesQty(1);
    	newItem7.setPerishableDays(9999);
    	newItem7.setSegment("S3");
    	
    	Item newItem8 = new Item();
    	
    	newItem8.setItemId("C0001J2");
    	newItem8.setItemName("Paint kit");
    	newItem8.setPricePerQty(5500);
    	newItem8.setQtyInStock(25);
    	newItem8.setTotalSalesQty(1);
    	newItem8.setPerishableDays(9999);
    	newItem8.setSegment("S3");
    	
    	Item newItem9 = new Item();
    	
    	newItem9.setItemId("C0001J3");
    	newItem9.setItemName("Chain Saw");
    	newItem9.setPricePerQty(3500);
    	newItem9.setQtyInStock(10);
    	newItem9.setTotalSalesQty(1);
    	newItem9.setPerishableDays(9999);
    	newItem9.setSegment("S3");
    	
    	runtime.sendEvent(newItem1);
    	ItemData.addItemData(newItem1.getItemId(), newItem1);
    	runtime.sendEvent(newItem2);
    	ItemData.addItemData(newItem2.getItemId(), newItem2);
    	runtime.sendEvent(newItem3);
    	ItemData.addItemData(newItem3.getItemId(), newItem3);
    	runtime.sendEvent(newItem4);
    	ItemData.addItemData(newItem4.getItemId(), newItem4);
    	runtime.sendEvent(newItem5);
    	ItemData.addItemData(newItem5.getItemId(), newItem5);
    	runtime.sendEvent(newItem6);
    	ItemData.addItemData(newItem6.getItemId(), newItem6);
    	runtime.sendEvent(newItem7);
    	ItemData.addItemData(newItem7.getItemId(), newItem7);
    	runtime.sendEvent(newItem8);
    	ItemData.addItemData(newItem8.getItemId(), newItem8);
    	runtime.sendEvent(newItem9);
    	ItemData.addItemData(newItem9.getItemId(), newItem9);
    	
    }
    
    private static void topBuyerInitialLoader() {

    	TopBuyer topBuyer1 = new TopBuyer();
    	topBuyer1.setCustomerId("12349");
    	topBuyer1.setCustomerName("Tushar");
    	topBuyer1.setTotalBill(2500);

    	TopBuyer topBuyer2 = new TopBuyer();
    	topBuyer2.setCustomerId("12347");
    	topBuyer2.setCustomerName("Phani");
    	topBuyer2.setTotalBill(750);

    	TopBuyer topBuyer3 = new TopBuyer();
    	topBuyer3.setCustomerId("12346");
    	topBuyer3.setCustomerName("Subhadip");
    	topBuyer3.setTotalBill(80);
    	
    	TopBuyer topBuyer4 = new TopBuyer();
    	topBuyer4.setCustomerId("12345");
    	topBuyer4.setCustomerName("Jack");
    	topBuyer4.setTotalBill(40);
    	
    	TopBuyer topBuyer5 = new TopBuyer();
    	topBuyer5.setCustomerId("12348");
    	topBuyer5.setCustomerName("Bharat");
    	topBuyer5.setTotalBill(26);
    	
    	List<TopBuyer> topBuyerArrList = new ArrayList<TopBuyer>();
    	
    	topBuyerArrList.add(topBuyer1);
    	topBuyerArrList.add(topBuyer2);
    	topBuyerArrList.add(topBuyer3);
    	topBuyerArrList.add(topBuyer4);
    	topBuyerArrList.add(topBuyer5);
    	
    	TopBuyerList topBuyerList = new TopBuyerList();
    	topBuyerList.setTopBuyer(topBuyerArrList);
    	
    	TopBuyerData.setTopBuyerData(topBuyerList);
    }
    
    private static void topSellingItemInitialLoader() {
    	
    	TopSellingItem topSellItem2 = new TopSellingItem();
    	topSellItem2.setItemId("A0001J2");
    	topSellItem2.setItemName("Fresh Bread");
    	topSellItem2.setTotalSalesQty(1820);
    	topSellItem2.setSegmentName("Groceries");

    	TopSellingItem topSellItem1 = new TopSellingItem();
    	topSellItem1.setItemId("A0001J1");
    	topSellItem1.setItemName("Butter 50gms");
    	topSellItem1.setTotalSalesQty(1800);
    	topSellItem1.setSegmentName("Groceries");   	

    	TopSellingItem topSellItem3 = new TopSellingItem();
    	topSellItem3.setItemId("A0001J3");
    	topSellItem3.setItemName("Fruit Jam 100gms");
    	topSellItem3.setTotalSalesQty(800);
    	topSellItem3.setSegmentName("Groceries");   	

    	TopSellingItem topSellItem4 = new TopSellingItem();
    	topSellItem4.setItemId("B0001J1");
    	topSellItem4.setItemName("Knife Set");
    	topSellItem4.setTotalSalesQty(300);
    	topSellItem4.setSegmentName("Kithcen & Appliances");   	

    	TopSellingItem topSellItem5 = new TopSellingItem();
    	topSellItem5.setItemId("B0001J2");
    	topSellItem5.setItemName("DineWare Set");
    	topSellItem5.setTotalSalesQty(750);
    	topSellItem5.setSegmentName("Kithcen & Appliances");

    	TopSellingItem topSellItem6 = new TopSellingItem();
    	topSellItem6.setItemId("B0001J3");
    	topSellItem6.setItemName("Mixer Grinder");
    	topSellItem6.setTotalSalesQty(3500);
    	topSellItem6.setSegmentName("Kithcen & Appliances");

    	TopSellingItem topSellItem7 = new TopSellingItem();
    	topSellItem7.setItemId("C0001J1");
    	topSellItem7.setItemName("Hardware Toolkit");
    	topSellItem7.setTotalSalesQty(2500);
    	topSellItem7.setSegmentName("Hardware & Tools");
    	
    	TopSellingItem topSellItem8 = new TopSellingItem();
    	topSellItem8.setItemId("C0001J2");
    	topSellItem8.setItemName("Paint kit");
    	topSellItem8.setTotalSalesQty(5500);
    	topSellItem8.setSegmentName("Hardware & Tools");

    	TopSellingItem topSellItem9 = new TopSellingItem();
    	topSellItem9.setItemId("C0001J3");
    	topSellItem9.setItemName("Chain Saw");
    	topSellItem9.setTotalSalesQty(3500);
    	topSellItem9.setSegmentName("Hardware & Tools");

    	List<TopSellingItem> topSellItemArrLst = new ArrayList<TopSellingItem>();
    	
    	topSellItemArrLst.add(topSellItem8);
    	topSellItemArrLst.add(topSellItem9);
    	topSellItemArrLst.add(topSellItem6);
    	topSellItemArrLst.add(topSellItem7);
    	topSellItemArrLst.add(topSellItem2);
    	topSellItemArrLst.add(topSellItem1);
    	topSellItemArrLst.add(topSellItem3);
    	topSellItemArrLst.add(topSellItem5);
    	topSellItemArrLst.add(topSellItem4);

    	TopSellingItemList topSellItemList = new TopSellingItemList();    	
    	topSellItemList.setTopSellingItem(topSellItemArrLst);
    	
    	TopSellingItemData.setTopSellingItemData(topSellItemList);
    }
}
