package com.mule.connector.inbound;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.esper.event.object.CheckOut;
import com.mule.inbound.objects.ItemCheckOut;

public class CheckOutEventProducer {
	
	public CheckOut createEvent(Object Input) {
		try {	
			InputStream stream = new ByteArrayInputStream(Input.toString().getBytes(StandardCharsets.UTF_8));
			
			JAXBContext jc = JAXBContext.newInstance(ItemCheckOut.class);			
			Unmarshaller unMarshaller = jc.createUnmarshaller();
			ItemCheckOut inputMsg = (ItemCheckOut) unMarshaller.unmarshal(stream);
			
			CheckOut outputEvent = new CheckOut();
			outputEvent.setCustomerId(inputMsg.getCustomerId());
			outputEvent.setCartId(inputMsg.getCartId());
			outputEvent.setItemId(inputMsg.getItemId());
			outputEvent.setQuantity(inputMsg.getQuantity());
			outputEvent.setUOM(inputMsg.getUOM());
			
			return outputEvent;
		}
		catch(Exception excep) {
			return null;
		}
    }
}
