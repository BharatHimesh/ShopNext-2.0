package com.mule.layer.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.esper.event.object.Bill;
import com.esper.event.object.BillItem;
import com.esper.event.object.Customer;
import com.esper.event.object.TransactionLog;

public class CustomerData {

	private static Map<String, Customer> customerData = new HashMap<String, Customer>();
	private static Map<String, TransactionLog> transactionLogData = new HashMap<String, TransactionLog>();
	
	public static Customer getCustomerData(String customerId) {
		Customer outputCustomer = null;
		
		if(customerData.containsKey(customerId)) {
			outputCustomer = customerData.get(customerId);
		}
		else {
			loadCustomerData(customerId);
			outputCustomer = customerData.get(customerId);
		}
		
		return outputCustomer;
	}
	
	public static TransactionLog getTransactionLog(String customerId) {
		TransactionLog outputTransactionLog = null;
		
		if(transactionLogData.containsKey(customerId)) {
			outputTransactionLog = transactionLogData.get(customerId);
		}
		else {
			loadTransactionLog(customerId);
			outputTransactionLog = transactionLogData.get(customerId);
		}
		
		return outputTransactionLog;
	}
	
	public static void putTransactionLog(String customerId, TransactionLog transLog) {
		transactionLogData.put(customerId, transLog);
	}
	
	private static void loadCustomerData(String customerId) {

    	Customer newCustomer = new Customer();
    	
    	if(customerId.equalsIgnoreCase("12345")) {
	    	newCustomer.setCustomerId(customerId);
	    	newCustomer.setCustomerName("Jack");
	    	newCustomer.setContactInfo("1234567890");
	    	
	    	newCustomer.setAvgPastBillAmount(500.0);
	    	newCustomer.setLoyaltyPoints(12);
    	}
    	else if(customerId.equalsIgnoreCase("12346")) {
	    	newCustomer.setCustomerId(customerId);
	    	newCustomer.setCustomerName("Subhadip");
	    	newCustomer.setContactInfo("0987654321");
	    	
	    	newCustomer.setAvgPastBillAmount(400.0);
	    	newCustomer.setLoyaltyPoints(18);    		
    	}
    	else if(customerId.equalsIgnoreCase("12347")) {
	    	newCustomer.setCustomerId(customerId);
	    	newCustomer.setCustomerName("Phani");
	    	newCustomer.setContactInfo("2364378912");
	    	
	    	newCustomer.setAvgPastBillAmount(500.0);
	    	newCustomer.setLoyaltyPoints(23);    		
    	}
    	else if(customerId.equalsIgnoreCase("12348")) {
	    	newCustomer.setCustomerId(customerId);
	    	newCustomer.setCustomerName("Bharat");
	    	newCustomer.setContactInfo("8646325784");
	    	
	    	newCustomer.setAvgPastBillAmount(100.0);
	    	newCustomer.setLoyaltyPoints(10);    		
    	}
    	else if(customerId.equalsIgnoreCase("12349")) {
	    	newCustomer.setCustomerId(customerId);
	    	newCustomer.setCustomerName("Tushar");
	    	newCustomer.setContactInfo("6244257844");
	    	
	    	newCustomer.setAvgPastBillAmount(1000.0);
	    	newCustomer.setLoyaltyPoints(56);    		
    	}
    	
		customerData.put(customerId, newCustomer);		
	}
	
    public static void loadTransactionLog(String customerId) {
    	
    	TransactionLog newLog = new TransactionLog();
    	SimpleDateFormat sft = new SimpleDateFormat ("yyyy-MM-dd");
    	Date billDate = null;
    	
    	try {
    		billDate = sft.parse("2014-11-01");
    	}
    	catch(Exception excep) {
    		System.out.println(excep.getStackTrace());
    	}
    	
    	if(customerId.equalsIgnoreCase("12345")) {
	    	newLog.setCustomerId(customerId);
	    	
	    	Bill newBill = new Bill();
	    	newBill.setCustomerId(customerId);
	    	newBill.setCartId("A234GH23");
	    	newBill.setBillAmount(40);
	    	newBill.setBillDate(billDate);
	    	
	    	BillItem newBillItem = new BillItem();
	    	newBillItem.setItemId("A0001J1");
	    	newBillItem.setItemName("Butter 50gms");
	    	newBillItem.setItemPrice(40);
	    	newBillItem.setItemQty(1);
	    	newBillItem.setDiscount(0);
	    	newBillItem.setNetPrice(40);
	    	
	    	List<BillItem> newBillItems = new ArrayList<BillItem>();
	    	newBillItems.add(newBillItem);
	    	
	    	newBill.setBillItems(newBillItems);
	    	
	    	List<Bill> billList = new ArrayList<Bill>();
	    	billList.add(newBill);
	    	
	    	newLog.setBills(billList);
    	}
    	else if(customerId.equalsIgnoreCase("12346")) {
	    	newLog.setCustomerId(customerId);
	    	
	    	Bill newBill = new Bill();
	    	newBill.setCustomerId(customerId);
	    	newBill.setCartId("A234GH24");
	    	newBill.setBillAmount(80);
	    	newBill.setBillDate(billDate);
	    	
	    	BillItem newBillItem = new BillItem();
	    	newBillItem.setItemId("A0001J3");
	    	newBillItem.setItemName("Fruit Jam 100gms");
	    	newBillItem.setItemPrice(80);
	    	newBillItem.setItemQty(1);
	    	newBillItem.setDiscount(0);
	    	newBillItem.setNetPrice(80);
	    	
	    	List<BillItem> newBillItems = new ArrayList<BillItem>();
	    	newBillItems.add(newBillItem);
	    	
	    	newBill.setBillItems(newBillItems);
	    	
	    	List<Bill> billList = new ArrayList<Bill>();
	    	billList.add(newBill);
	    	
	    	newLog.setBills(billList);
    	}
    	else if(customerId.equalsIgnoreCase("12347")) {
	    	newLog.setCustomerId(customerId);
	    	
	    	Bill newBill = new Bill();
	    	newBill.setCustomerId(customerId);
	    	newBill.setCartId("A234GH25");
	    	newBill.setBillAmount(150);
	    	newBill.setBillDate(billDate);
	    	
	    	BillItem newBillItem = new BillItem();
	    	newBillItem.setItemId("B0001J1");
	    	newBillItem.setItemName("Knife Set");
	    	newBillItem.setItemPrice(150);
	    	newBillItem.setItemQty(1);
	    	newBillItem.setDiscount(0);
	    	newBillItem.setNetPrice(150);
	    	
	    	List<BillItem> newBillItems = new ArrayList<BillItem>();
	    	newBillItems.add(newBillItem);
	    	
	    	newBill.setBillItems(newBillItems);
	    	
	    	List<Bill> billList = new ArrayList<Bill>();
	    	billList.add(newBill);
	    	
	    	newLog.setBills(billList);
    	}
    	else if(customerId.equalsIgnoreCase("12348")) {
	    	newLog.setCustomerId(customerId);
	    	
	    	Bill newBill = new Bill();
	    	newBill.setCustomerId(customerId);
	    	newBill.setCartId("A234GH26");
	    	newBill.setBillAmount(26);
	    	newBill.setBillDate(billDate);
	    	
	    	BillItem newBillItem = new BillItem();
	    	newBillItem.setItemId("A0001J2");
	    	newBillItem.setItemName("Fresh Bread");
	    	newBillItem.setItemPrice(26);
	    	newBillItem.setItemQty(1);
	    	newBillItem.setDiscount(0);
	    	newBillItem.setNetPrice(26);
	    	
	    	List<BillItem> newBillItems = new ArrayList<BillItem>();
	    	newBillItems.add(newBillItem);
	    	
	    	newBill.setBillItems(newBillItems);
	    	
	    	List<Bill> billList = new ArrayList<Bill>();
	    	billList.add(newBill);
	    	
	    	newLog.setBills(billList);
    	}
    	else if(customerId.equalsIgnoreCase("12349")) {
	    	newLog.setCustomerId(customerId);
	    	
	    	Bill newBill = new Bill();
	    	newBill.setCustomerId(customerId);
	    	newBill.setCartId("A234GH27");
	    	newBill.setBillAmount(2500);
	    	newBill.setBillDate(billDate);
	    	
	    	BillItem newBillItem = new BillItem();
	    	newBillItem.setItemId("C0001J1");
	    	newBillItem.setItemName("Hardware Toolkit");
	    	newBillItem.setItemPrice(2500);
	    	newBillItem.setItemQty(1);
	    	newBillItem.setDiscount(0);
	    	newBillItem.setNetPrice(2500);
	    	
	    	List<BillItem> newBillItems = new ArrayList<BillItem>();
	    	newBillItems.add(newBillItem);
	    	
	    	newBill.setBillItems(newBillItems);
	    	
	    	List<Bill> billList = new ArrayList<Bill>();
	    	billList.add(newBill);
	    	
	    	newLog.setBills(billList);
    	}
    	
    	transactionLogData.put(customerId, newLog);
    }

	public static Customer[] getAllActiveCustomerData() {
		
		Collection<Customer> outputCustColl = customerData.values();
		Customer[] outputCustArr = outputCustColl.toArray(new Customer[0]);
		return outputCustArr;	
		}
}
