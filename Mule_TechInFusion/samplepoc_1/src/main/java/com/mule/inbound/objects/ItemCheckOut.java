package com.mule.inbound.objects;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ItemCheckOut")
public class ItemCheckOut {
	
	@XmlElement(name = "CustomerId", required = true)
	protected String customerId;
	@XmlElement(name = "CartId", required = true)
	protected String cartId;
	@XmlElement(name = "ItemId", required = true)
	protected String itemId;
	@XmlElement(name = "Quantity", required = true)
	protected long quantity;
	@XmlElement(name = "UOM", required = true)
	protected String UOM;
	
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
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	public long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	public String getUOM() {
		return UOM;
	}
	
	public void setUOM(String uOM) {
		UOM = uOM;
	}
}
