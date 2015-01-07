package com.mule.layer.dao;

import java.util.HashMap;
import java.util.Map;

import com.esper.event.object.Bill;

public class BillData {
	
	private static Map<String, Bill> billData = new HashMap<String, Bill>();
	
	public static Bill getBillData(String customerId, String cartId) {
		Bill outputBill = null;
		
		if(billData.containsKey(customerId+cartId)) {
			outputBill = billData.get(customerId+cartId);
		}
		
		return outputBill;
	}
	
	public static void addBillData(String customerId, String cartId, Bill inputBillData) {		
		billData.put(customerId+cartId, inputBillData);		
	}
	
	public static void removeBillData(String customerId, String cartId) {
		if(billData.containsKey(customerId+cartId)) {
			billData.remove(customerId+cartId);
		}
	}
}
