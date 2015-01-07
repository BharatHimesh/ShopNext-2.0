package com.mule.connector.inbound;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.esper.event.object.CustomerMovedIntoSegment;
import com.mule.inbound.objects.CustomerInSegment;

public class CustomerMovedIntoSegEventProducer {
	
	public CustomerMovedIntoSegment createEvent(Object Input) {
		try {	
			InputStream stream = new ByteArrayInputStream(Input.toString().getBytes(StandardCharsets.UTF_8));
			
			JAXBContext jc = JAXBContext.newInstance(CustomerInSegment.class);			
			Unmarshaller unMarshaller = jc.createUnmarshaller();
			CustomerInSegment inputMsg = (CustomerInSegment) unMarshaller.unmarshal(stream);
			
			CustomerMovedIntoSegment outputEvent = new CustomerMovedIntoSegment();
			outputEvent.setCustomerId(inputMsg.getCustomerId());
			outputEvent.setCartId(inputMsg.getCartId());
			outputEvent.setPreviousSegmentID(inputMsg.getPreviousSegmentID());
			outputEvent.setCurrentSegmentID(inputMsg.getCurrentSegmentID());
			
			return outputEvent;
		}
		catch(Exception excep) {
			return null;
		}
    }
}
