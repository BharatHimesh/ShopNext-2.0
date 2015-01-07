package com.mule.connector.outbound;

import java.util.Map;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.callback.SourceCallback;
import org.mule.api.processor.MessageProcessor;
import org.mule.module.esper.EsperModule;
import org.mule.module.json.transformers.ObjectToJson;
import org.mule.transport.nio.http.HttpConnector;
import org.mule.util.NumberUtils;

import com.esper.event.object.AlertMessageOM;
import com.esper.event.object.Bill;

public class AlertMessageOMEventSubscriber implements MessageProcessor {

    private EsperModule esperModule;

    public void setEsperModule(final EsperModule esperModule) {
        this.esperModule = esperModule;
    }

	private HttpConnector httpConnector;

    public void setHttpConnector(final HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    private ObjectToJson alertMessageOMToJSON;
    
    public void setAlertMessageOMToJSON(final ObjectToJson alertMessageOMToJSON) {
        this.alertMessageOMToJSON = alertMessageOMToJSON;
    }

    /**
     * Processes the input messages received via WebSockets.
     */
    public MuleEvent process(final MuleEvent muleEvent) throws MuleException {
    	
        final int channelId = NumberUtils.toInt(muleEvent.getMessage().getInboundProperty("nio.channel.id"));
        String statement = "SELECT * FROM AlertMessageOMEvent";

        esperModule.listen(statement, new SourceCallback() {
        	
            public Object process(Object payload) {
            	try {            		
            		AlertMessageOM alertMsg = (AlertMessageOM) payload;
            		Object jsonMsg = alertMessageOMToJSON.transform(alertMsg);
            		
	                MuleMessage muleMessage = new DefaultMuleMessage(jsonMsg, httpConnector.getMuleContext());
	                httpConnector.writeToWebSocket(muleMessage, "events", channelId);
	                return null;
            	}
            	catch(Exception excep) {
            		System.out.println(excep);
            		return null;
            	}
            }

            public Object process() throws Exception {
                throw new UnsupportedOperationException();
            }

            public Object process(final Object payload, 
            					final Map<String, Object> properties)
            												throws Exception {
                throw new UnsupportedOperationException();
            }
            
			public MuleEvent processEvent(final MuleEvent muleEvent) throws MuleException {
				return null;
            }
        });

        return muleEvent;
    }
}
