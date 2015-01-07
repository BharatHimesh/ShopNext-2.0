package com.mule.connector.inbound;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.esper.event.object.Deals;
import com.mule.inbound.objects.DealsOnTheFly;

public class DealEventProducer {
	
	public Deals createEvent(Object Input) {
		try {	
			InputStream stream = new ByteArrayInputStream(Input.toString().getBytes(StandardCharsets.UTF_8));
			
			JAXBContext jc = JAXBContext.newInstance(DealsOnTheFly.class);			
			Unmarshaller unMarshaller = jc.createUnmarshaller();
			DealsOnTheFly inputMsg = (DealsOnTheFly) unMarshaller.unmarshal(stream);
			
			Deals outputEvent = new Deals();
			outputEvent.setDealStatement(inputMsg.getDealStatement());
			outputEvent.setMatchCriteria(inputMsg.getMatchCriteria());
			outputEvent.setMatchKey(inputMsg.getMatchKey());
			
			return outputEvent;
		}
		catch(Exception excep) {
			return null;
		}
    }
}
