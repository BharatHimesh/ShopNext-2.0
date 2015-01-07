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

import com.esper.event.object.Bill;
import com.esper.event.object.Customer;
import com.esper.event.object.SignIn;
import com.esper.event.object.TransactionLog;
import com.mule.inbound.objects.UserAction;
import com.mule.layer.dao.CustomerData;

public class BillEventSubscriber implements MessageProcessor {

    private EsperModule esperModule;

    public void setEsperModule(final EsperModule esperModule) {
        this.esperModule = esperModule;
    }

	private HttpConnector httpConnector;

    public void setHttpConnector(final HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    private ObjectToJson billToJSON;
    
    public void setBillToJSON(final ObjectToJson billToJSON) {
        this.billToJSON = billToJSON;
    }

    private ObjectToJson transactionLogToJSON;
    
    public void setTransactionLogToJSON(final ObjectToJson transactionLogToJSON) {
        this.transactionLogToJSON = transactionLogToJSON;
    }
    
    private ObjectToJson customerToJSON;
    
    public void setCustomerToJSON(final ObjectToJson customerToJSON) {
        this.customerToJSON = customerToJSON;
    }
    
    /**
     * Processes the input messages received via WebSockets.
     */
    public MuleEvent process(final MuleEvent muleEvent) throws MuleException {
    	
        final int channelId = NumberUtils.toInt(muleEvent.getMessage().getInboundProperty("nio.channel.id"));
        UserAction dataReceived = (UserAction) muleEvent.getMessage().getPayload();
        final String customerId = dataReceived.getCustomerId();
        
//      Load the customer info into esper
        Customer custData = CustomerData.getCustomerData(customerId);       
        Object custDataJsonMsg = transactionLogToJSON.transform(custData);
        MuleMessage custDataMuleMessage = new DefaultMuleMessage(custDataJsonMsg, httpConnector.getMuleContext());
        
//      Load the customer's transaction history
        TransactionLog custTransData = CustomerData.getTransactionLog(customerId);        
        Object transLogJsonMsg = transactionLogToJSON.transform(custTransData);
        MuleMessage transLogMuleMessage = new DefaultMuleMessage(transLogJsonMsg, httpConnector.getMuleContext());
        try {
        	httpConnector.writeToWebSocket(transLogMuleMessage, "events", channelId);
        	httpConnector.writeToWebSocket(custDataMuleMessage, "events", channelId);
        }
        catch(Exception excep) {
        	System.out.println(excep.getStackTrace());
        }
        
        String statement = "SELECT * FROM BillEvent WHERE customerId='"+customerId+"'";
        
        esperModule.listen(statement, new SourceCallback() {
        	
            public Object process(Object payload) {
            	try {            		
            		Bill currentBill = (Bill) payload;
            		Object jsonMsg = billToJSON.transform(currentBill);
            		
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

        SignIn userSignIn = new SignIn();        
        userSignIn.setCustomerId(customerId);
        
        esperModule.send(userSignIn, "SignInEvent");
        
        return muleEvent;
    }
}
