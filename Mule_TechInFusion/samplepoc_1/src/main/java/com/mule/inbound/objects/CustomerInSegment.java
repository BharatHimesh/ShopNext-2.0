package com.mule.inbound.objects;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CustomerInSegment")
public class CustomerInSegment {
	
	@XmlElement(name = "CustomerId", required = true)
	protected String customerId;
	@XmlElement(name = "CartId", required = true)
	protected String cartId;
	@XmlElement(name = "PreviousSegmentID", required = true)
	protected String previousSegmentID;
	@XmlElement(name = "CurrentSegmentID", required = true)
	protected String currentSegmentID;
	
	public String getCurrentSegmentID(){
	 return currentSegmentID;	
	}
	
	public void setCurrentSegmentID(String currentSegmentID){
		this.currentSegmentID=currentSegmentID;
	}
	
	public String getPreviousSegmentID(){
	 return previousSegmentID;	
	}
	
	public void setPreviousSegmentID(String previousSegmentID){
		this.previousSegmentID=previousSegmentID;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getCartId() {
		return cartId;
	}
	
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
}
