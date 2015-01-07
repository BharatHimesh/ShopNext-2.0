package com.esper.event.config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.esper.event.object.AlertMessageCC;
import com.esper.event.object.AlertMessageOM;
import com.esper.event.object.Bill;
import com.esper.event.object.BillItem;
import com.esper.event.object.Customer;
import com.esper.event.object.FloorHeatMap;
import com.esper.event.object.Item;
import com.esper.event.object.LowStockList;
import com.esper.event.object.TopBuyer;
import com.esper.event.object.TopBuyerList;
import com.esper.event.object.TopSellingItem;
import com.esper.event.object.TopSellingItemList;
import com.esper.event.object.TransactionLog;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.mule.layer.dao.BillData;
import com.mule.layer.dao.CustomerData;
import com.mule.layer.dao.HeatMapData;
import com.mule.layer.dao.ItemData;
import com.mule.layer.dao.LowStockData;
import com.mule.layer.dao.TopBuyerData;
import com.mule.layer.dao.TopSellingItemData;

public class EsperConfiguration {
	
	private static final EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();
	private static final EPRuntime runtime = epService.getEPRuntime();
	
	/**
	 * Adds standard Esper listeners into runtime Esper engine.
	 */
    public static void addEsperListners() {
    	checkOutProcessor();
    	itemMonitor();
    	signInMonitor();
    	signOutMonitor();
    	customerMovementMonitor();
    	customersAlsoBought();
    	onBillAmountDeal();
    	onComboOfferDeal();
    }
    
    private static void onComboOfferDeal() {
    	String monitorStatement = "SELECT *"
                + " FROM DealsEvent where matchKey in ('ComboOffer')";

    	EPStatement stmtOne = epService.getEPAdministrator().createEPL(monitorStatement);
    	stmtOne.addListener(new UpdateListener() {
    		public void update(EventBean[] newEvents, EventBean[] oldEvents) {
    			for(int i = 0; i < newEvents.length; i++) {
    				for(Customer cust:CustomerData.getAllActiveCustomerData()){
    					AlertMessageCC newAlert = new AlertMessageCC();
				    	 StringBuilder addInfo = new StringBuilder();

				    	 newAlert.setMessageType("success");
				    	 newAlert.setCustomerId(cust.getCustomerId());
				    	 addInfo.append(",\n Floor Offer : " + String.valueOf(newEvents[i].get("dealStatement"))); 
		             		    	   
				    	 newAlert.setAdditionalInfo(addInfo.toString());
				    	 runtime.sendEvent(newAlert);    	   

    				}
    			}
    		}
    	});
	}

	private static void onBillAmountDeal() {
    	String monitorStatement = "SELECT *"
                + " FROM DealsEvent where matchKey in ('BillAmount')";

    	EPStatement stmtOne = epService.getEPAdministrator().createEPL(monitorStatement);
    	stmtOne.addListener(new UpdateListener() {
    		public void update(EventBean[] newEvents, EventBean[] oldEvents) {
    			for(int i = 0; i < newEvents.length; i++) {
    				Double matchCriteria = Double.valueOf(String.valueOf(newEvents[i].get("matchCriteria")));
    				for(Customer cust:CustomerData.getAllActiveCustomerData()){
    					if(isWithinRange(cust.getAvgPastBillAmount(),matchCriteria)){
    						//additional loyalty points on bill of x amount
    						//AlertMessageCC
    						 AlertMessageCC newAlert = new AlertMessageCC();
    				    	 StringBuilder addInfo = new StringBuilder();

    				    	 newAlert.setMessageType("success");
    				    	 newAlert.setCustomerId(cust.getCustomerId());
    				    	 addInfo.append(",\n Offer only for you: " + String.valueOf(newEvents[i].get("dealStatement"))); 
  		             		    	   
    				    	 newAlert.setAdditionalInfo(addInfo.toString());
    				    	 runtime.sendEvent(newAlert);    	   
  		       
    					}
    				}
    			}
    		}

			private boolean isWithinRange(double avgPastBillAmount,
					double matchCriteria) {
				if(avgPastBillAmount>(matchCriteria-(0.1*matchCriteria)))
					return true;
				else
					return false;
			}
    	});

	}

	/**
     * Creates bills from CheckOut events.
     */
    private static void checkOutProcessor() {	
    	
    	String monitorStatement = "SELECT CheckOutOccurance.customerId as customerId,"
					        		+ "	CheckOutOccurance.cartId as cartId,"
					        		+ "	CheckOutOccurance.itemId as itemId,"
					        		+ "	CheckOutOccurance.quantity as quantity,"
					        		+ " CheckOutOccurance.UOM as UOM,"
					        		+ " ItemOccurance.itemName as itemName,"
					        		+ " ItemOccurance.pricePerQty as pricePerQty,"
					        		+ " ItemOccurance.qtyInStock as qtyInStock,"
					        		+ " ItemOccurance.totalSalesQty as totalSalesQty,"
					        		+ " CheckOutOccurance.quantity*ItemOccurance.pricePerQty as netAmount"
					        + " FROM PATTERN [EVERY ItemOccurance=ItemEvent -> CheckOutOccurance=CheckOutEvent(itemId=ItemOccurance.itemId)]";
    	
		EPStatement stmtOne = epService.getEPAdministrator().createEPL(monitorStatement);
		stmtOne.addListener(new UpdateListener()
		{
		   public void update(EventBean[] newEvents, EventBean[] oldEvents)
		   {
		       for(int i = 0; i < newEvents.length; i++) { 	   

	           		Bill currentBill = BillData.getBillData(String.valueOf(newEvents[i].get("customerId")),
	           												String.valueOf(newEvents[i].get("cartId")));
	           		
	           		if(currentBill==null) {
	           			currentBill = new Bill();
	               		BillItem outputBillItem = new BillItem();
	               		List<BillItem> outBillItemLst = new ArrayList<BillItem>();
	               		outputBillItem.setItemId(String.valueOf(newEvents[i].get("itemId")));
	               		outputBillItem.setItemName(String.valueOf(newEvents[i].get("itemName")));
	               		outputBillItem.setItemPrice(Long.valueOf(String.valueOf(newEvents[i].get("pricePerQty"))));
	               		outputBillItem.setItemQty(Long.valueOf(String.valueOf(newEvents[i].get("quantity"))));
	               		outputBillItem.setDiscount(0);
	               		outputBillItem.setNetPrice(Long.valueOf(String.valueOf(newEvents[i].get("netAmount"))));
	               		
	               		outBillItemLst.add(outputBillItem);
	               		currentBill.setCustomerId(String.valueOf(newEvents[i].get("customerId")));
	               		currentBill.setCartId(String.valueOf(newEvents[i].get("cartId")));
	               		currentBill.setBillItems(outBillItemLst);
	               		currentBill.setBillAmount(Long.valueOf(String.valueOf(newEvents[i].get("netAmount"))));
	               		currentBill.setBillDate(new Date());
	               		
	               		BillData.addBillData(currentBill.getCustomerId(), 
	               							currentBill.getCartId(),
	               							currentBill);
	           		}
	           		else {
	           			BillItem outputBillItem = new BillItem();
	           			List<BillItem> outBillItemLst = currentBill.getBillItems();
	               		outputBillItem.setItemId(String.valueOf(newEvents[i].get("itemId")));
	               		outputBillItem.setItemName(String.valueOf(newEvents[i].get("itemName")));
	               		outputBillItem.setItemPrice(Long.valueOf(String.valueOf(newEvents[i].get("pricePerQty"))));
	               		outputBillItem.setItemQty(Long.valueOf(String.valueOf(newEvents[i].get("quantity"))));
	               		outputBillItem.setDiscount(0);
	               		outputBillItem.setNetPrice(Long.valueOf(String.valueOf(newEvents[i].get("netAmount"))));
	               		
	               		outBillItemLst.add(outputBillItem);
	               		currentBill.setBillItems(outBillItemLst);
	               		currentBill.setBillAmount(currentBill.getBillAmount()+Long.valueOf(String.valueOf(newEvents[i].get("netAmount"))));
	               		
	               		BillData.addBillData(currentBill.getCustomerId(), 
	               							currentBill.getCartId(),
	               							currentBill);
	           		}
	           		
	            	runtime.sendEvent(currentBill);
	            	
	            	Item newItem = new Item();
	            	
	            	newItem.setItemId(String.valueOf(newEvents[i].get("itemId")));
	            	newItem.setItemName(String.valueOf(newEvents[i].get("itemName")));
	            	newItem.setPricePerQty(Long.valueOf(String.valueOf(newEvents[i].get("pricePerQty"))));
	            	newItem.setQtyInStock(Long.valueOf(String.valueOf(newEvents[i].get("qtyInStock")))
	            							-Long.valueOf(String.valueOf(newEvents[i].get("quantity"))));
	            	newItem.setTotalSalesQty(Long.valueOf(String.valueOf(newEvents[i].get("totalSalesQty")))
											+Long.valueOf(String.valueOf(newEvents[i].get("quantity"))));
	            	
	            	runtime.sendEvent(newItem);
		       }
		   }
		});
    }
    
    /**
     * Monitors the Item stock.
     */
    private static void itemMonitor() {	
    	
    	String monitorStatement = "SELECT *"
			                + " FROM ItemEvent.std:lastevent()"
			                + " WHERE qtyInStock <= 5";
    	
		EPStatement stmtOne = epService.getEPAdministrator().createEPL(monitorStatement);
		stmtOne.addListener(new UpdateListener() {
		   public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		       for(int i = 0; i < newEvents.length; i++) {
		    	   
		    	   AlertMessageOM newAlert = new AlertMessageOM();
		    	   newAlert.setMessageType("danger");
		    	   newAlert.setMessage("Item running low in Stock!");
		    	   
		    	   StringBuilder addInfo = new StringBuilder();
		    	   addInfo.append("Item Id: "+String.valueOf(newEvents[i].get("itemId")));
		    	   addInfo.append(",\n Item Name: "+String.valueOf(newEvents[i].get("itemName")));
		    	   addInfo.append(",\n Current Stock: "+String.valueOf(newEvents[i].get("qtyInStock")));
		    	   
		    	   newAlert.setAdditionalInfo(addInfo.toString());
		    	   runtime.sendEvent(newAlert);
		    	   
//		    	   Update Items Low in Stock Event
		    	   LowStockList oldLowStockData = LowStockData.getLowStockData();
		    	   Map<String, ArrayList<String>> itemLowInStock = oldLowStockData.getItemLowInStock();
		    	   
		    	   ArrayList<String> itemInformation = new ArrayList<String>();
		    	   itemInformation.add(String.valueOf(newEvents[i].get("itemId")));
		    	   itemInformation.add(String.valueOf(newEvents[i].get("itemName")));
		    	   itemInformation.add(String.valueOf(newEvents[i].get("qtyInStock")));
		    	   
		    	   itemLowInStock.put(String.valueOf(newEvents[i].get("itemId")), itemInformation);
		    	   
		    	   runtime.sendEvent(oldLowStockData);
		       }
		   }
		});
    }
    
    
    private static void customerMovementMonitor() {
    	String monitorStatement = "SELECT * FROM CustomerMovedIntoSegmentEvent";
    	
    	EPStatement stmtOne = epService.getEPAdministrator().createEPL(monitorStatement);
		stmtOne.addListener(new UpdateListener() {
		   public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		       for(int i = 0; i < newEvents.length; i++) {
		    	   
		    	   FloorHeatMap heatMapMsgIni = HeatMapData.getHeatMapData();
		    	   String currentSegmentID=String.valueOf(newEvents[i].get("currentSegmentID"));
		    	   String previousSegmentID=String.valueOf(newEvents[i].get("previousSegmentID"));
		    	   String currentSegmentName = null;
		    	   
		    	   switch(currentSegmentID){
		    	   case "S1":
				    	   heatMapMsgIni.setTotalCustomersInSection1(heatMapMsgIni.getTotalCustomersInSection1() + 1);
				    	   currentSegmentName = "Groceries";
				    	   break;
		    	   case "S2":

			    	   heatMapMsgIni.setTotalCustomersInSection2(heatMapMsgIni.getTotalCustomersInSection2() + 1);
			    	   currentSegmentName = "Kithcen & Appliances";
			    	   break;
		    	   case "S3":

			    	   heatMapMsgIni.setTotalCustomersInSection3(heatMapMsgIni.getTotalCustomersInSection3() + 1);
			    	   currentSegmentName = "Hardware & Tools";
			    	   break;		    	   
		    	   }
		    	   
		    	   switch(previousSegmentID){
		    	   case "S1":
				    	   heatMapMsgIni.setTotalCustomersInSection1(heatMapMsgIni.getTotalCustomersInSection1() - 1);
		    			   break;
		    	   case "S2":

			    	   heatMapMsgIni.setTotalCustomersInSection2(heatMapMsgIni.getTotalCustomersInSection2() - 1);
	    			   break;
		    	   case "S3":

			    	   heatMapMsgIni.setTotalCustomersInSection3(heatMapMsgIni.getTotalCustomersInSection3() - 1);
	    			   break;		    	   
		    	   }
		    	   
//		    	   HeatMapData.setHeatMapData(heatMapMsgIni);
                   runtime.sendEvent(heatMapMsgIni);
                   
                   
                   AlertMessageCC newAlert = new AlertMessageCC();
		    	   StringBuilder addInfo = new StringBuilder();
		    	   StringBuilder addInfoForMV = new StringBuilder();

		    	   newAlert.setMessageType("success");
		    	   newAlert.setCustomerId(String.valueOf(newEvents[i].get("customerId")));
                  
		    	   //notify the customer of the section of the store
		    	   addInfoForMV.append(",\n You are now in the "+ currentSegmentName + " section of the store"); 
    			   newAlert.setAdditionalInfo(addInfoForMV.toString());
                   runtime.sendEvent(newAlert);
                   
                   //product reco
                   //send best selling item in segment to the carousel
                   		    	   
		    	   TopSellingItemList topSellingItemListDat = TopSellingItemData.getTopSellingItemData();
		    	   List<TopSellingItem> topSellingItemDat = topSellingItemListDat.getTopSellingItem();
		    	   
		    	   for(TopSellingItem topSellingItemIndvDat: topSellingItemDat) {
		    		   Item item = ItemData.getItemData(topSellingItemIndvDat.getItemId());
		    		   if(item.getSegment().equalsIgnoreCase(currentSegmentID)){
		    			   addInfo.append(",\n Most people in this part of the store are buying: "); 
		    			   addInfo.append(",\n "+ item.getItemName());
		                   break;
		    		   }
		    	   }
		    	   
                   newAlert.setAdditionalInfo(addInfo.toString());
                   runtime.sendEvent(newAlert);    	   
		       }
		   }
		});
    }
    
    private static void customersAlsoBought() {
    	String monitorStatement = "SELECT CheckOutOccurance1.itemId as item1, CheckOutOccurance2.itemId as item2 FROM PATTERN " +
    			"[every CheckOutOccurance1=CheckOutEvent -> CheckOutOccurance2=CheckOutEvent(itemId!=CheckOutOccurance1.itemId AND customerId=CheckOutOccurance1.customerId) " +
    			"-> CheckOutOccurance3=CheckOutEvent(itemId=CheckOutOccurance1.itemId AND customerId!=CheckOutOccurance1.customerId) -> CheckOutOccurance4=CheckOutEvent(itemId=CheckOutOccurance2.itemId AND customerId=CheckOutOccurance3.customerId)]";
    	
    	EPStatement stmtOne = epService.getEPAdministrator().createEPL(monitorStatement);
		stmtOne.addListener(new UpdateListener() {
		   public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		       for(int i = 0; i < newEvents.length; i++) {
		    	   AlertMessageOM newAlert = new AlertMessageOM();
		    	   newAlert.setMessageType("danger");
		    	   newAlert.setMessage("New trend detected on the floor: People who bought Item 1 also are buying Item 2!");
		    	   
		    	   StringBuilder addInfo = new StringBuilder();
		    	   addInfo.append("Item 1: "+String.valueOf(newEvents[i].get("item1")));
		    	   addInfo.append("Item 2: "+String.valueOf(newEvents[i].get("item2")));
		    	   
		    	   newAlert.setAdditionalInfo(addInfo.toString());
		    	   runtime.sendEvent(newAlert);
		       }
		   }
		});
    }
    
    private static void signInMonitor() {
    	String monitorStatement = "SELECT * FROM SignInEvent";
    	
    	EPStatement stmtOne = epService.getEPAdministrator().createEPL(monitorStatement);
		stmtOne.addListener(new UpdateListener() {
		   public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		       for(int i = 0; i < newEvents.length; i++) {
		    	   
		    	   //update heat map
		    	   FloorHeatMap heatMapMsgIni = HeatMapData.getHeatMapData();
		    	   heatMapMsgIni.setTotalCustomersOnFloor(heatMapMsgIni.getTotalCustomersOnFloor() + 1);
		    	   runtime.sendEvent(heatMapMsgIni);
		    	  
		    	   //notification based on past bills 
		    	   AlertMessageCC newAlert = new AlertMessageCC();
		    	   newAlert.setMessageType("success");
		    	   String customerId= String.valueOf(newEvents[i].get("customerId"));
		    	   newAlert.setCustomerId(customerId);
		    	   		    	   
                   TransactionLog pastLog = CustomerData.getTransactionLog(String.valueOf(newEvents[i].get("customerId")));
                   Bill lastBill = pastLog.getBills().get(pastLog.getBills().size()-1);
                   
                   Calendar cal = Calendar.getInstance();
                   long nmbrOfDaysSinceLastVisit = (cal.getTime().getTime() - lastBill.getBillDate().getTime())/ (1000 * 60 * 60 * 24);
                   
                   for(BillItem items: lastBill.getBillItems()) {
                	   
                         Item item = ItemData.getItemData(items.getItemId());
                         
                         if(item.getPerishableDays()<=nmbrOfDaysSinceLastVisit) {
                               newAlert.setMessage("\n You might need new stock for: ");
                   
                               StringBuilder addInfo = new StringBuilder();
                               addInfo.append(",\n "+items.getItemName());
                               addInfo.append(",\n You had last bought it on: "+ lastBill.getBillDate()); 
                   
                               newAlert.setAdditionalInfo(addInfo.toString());
                               runtime.sendEvent(newAlert);
                         }
                   }
                  
                   //product reco
                   //send frequent buy to the carousel
                   Customer cust = CustomerData.getCustomerData(customerId);
                   StringBuilder addInfo = new StringBuilder();
                   addInfo.append(",\n We recommend you item name: "+cust.getFrequentBuy());
                   newAlert.setAdditionalInfo(addInfo.toString());

		       }
		   }
		});
    }
    
    private static void signOutMonitor() {
    	String monitorStatement = "SELECT * FROM SignOutEvent";
    	
    	EPStatement stmtOne = epService.getEPAdministrator().createEPL(monitorStatement);
		stmtOne.addListener(new UpdateListener() {
		   public void update(EventBean[] newEvents, EventBean[] oldEvents) {
		       for(int i = 0; i < newEvents.length; i++) {
		    	   String customerId = String.valueOf(newEvents[i].get("customerId"));
		    	   String cartId = String.valueOf(newEvents[i].get("cartId"));
		    	   
		    	   Bill oldBill = BillData.getBillData(customerId, cartId);
		    	   BillData.removeBillData(customerId, cartId);
		    	   
		    	   TransactionLog oldTransLog = CustomerData.getTransactionLog(customerId);
		    	   List<Bill> oldBillList = oldTransLog.getBills();
		    	   oldBillList.add(oldBill);
		    	   
		    	   oldTransLog.setBills(oldBillList);
		    	   
		    	   CustomerData.putTransactionLog(customerId, oldTransLog);
		       
//		    	   Create Top Buyer Data
		    	   TopBuyerList topBuyerListDat = TopBuyerData.getTopBuyerData();
		    	   List<TopBuyer> topBuyersDat = topBuyerListDat.getTopBuyer();
		    	   for(TopBuyer topBuyerIndvDat: topBuyersDat) {
		    		   if(topBuyerIndvDat.getCustomerId().equalsIgnoreCase(customerId)) {
		    			   topBuyerIndvDat.setTotalBill(topBuyerIndvDat.getTotalBill()+oldBill.getBillAmount());
		    		   }
		    	   }
		    	   Collections.sort(topBuyersDat);
		    	   runtime.sendEvent(topBuyerListDat);
		    	   
//		    	   Create Top Selling Item Data
		    	   TopSellingItemList topSellingItemListDat = TopSellingItemData.getTopSellingItemData();
		    	   List<TopSellingItem> topSellingItemDat = topSellingItemListDat.getTopSellingItem();
		    	   
		    	   List<BillItem> oldBillItemList = oldBill.getBillItems();
		    	   
		    	   for(BillItem oldBillItem: oldBillItemList) {
			    	   for(TopSellingItem topSellingItemIndvDat: topSellingItemDat) {
			    		   if(topSellingItemIndvDat.getItemId().equalsIgnoreCase(oldBillItem.getItemId())) {
			    			   topSellingItemIndvDat.setTotalSalesQty(topSellingItemIndvDat.getTotalSalesQty()+oldBillItem.getNetPrice());
			    			   break;
			    		   }
			    	   }
		    	   }
		    	   
		    	   Collections.sort(topSellingItemDat);
		    	   runtime.sendEvent(topSellingItemListDat);
		    	   
		    	   //update heat map
		    	   FloorHeatMap heatMapMsgIni = HeatMapData.getHeatMapData();
		    	   heatMapMsgIni.setTotalCustomersOnFloor(heatMapMsgIni.getTotalCustomersOnFloor() - 1);
		    	   runtime.sendEvent(heatMapMsgIni);

		       }
		   }
		});
    }
    
}
