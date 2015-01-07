package com.esper.event.object;

public class CustomerMovedIntoSegment {
	private String customerId;
	private String cartId;

	private String previousSegmentID;
	private String currentSegmentID;
	public String getPreviousSegmentID() {
		return previousSegmentID;
	}
	public void setPreviousSegmentID(String previousSegmentID) {
		this.previousSegmentID = previousSegmentID;
	}
	public String getCurrentSegmentID() {
		return currentSegmentID;
	}
	public void setCurrentSegmentID(String currentSegmentID) {
		this.currentSegmentID = currentSegmentID;
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
