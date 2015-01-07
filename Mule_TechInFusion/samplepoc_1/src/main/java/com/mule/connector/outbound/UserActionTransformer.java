package com.mule.connector.outbound;

import org.mule.module.json.JsonData;

import com.mule.inbound.objects.UserAction;

public class UserActionTransformer {
	
	public UserAction ProcessPerson(Object userActionInput) {
		
		UserAction dataReceived = new UserAction();		
		String userActionInputStr = userActionInput.toString();
		
		try {
			JsonData userActionInputPar = new JsonData(userActionInputStr);

			dataReceived.setCustomerId(String.valueOf(userActionInputPar.get("customerId")).replace("\"", ""));
			dataReceived.setUserAction(String.valueOf(userActionInputPar.get("userAction")).replace("\"", ""));
			
			if(userActionInputPar.get("additionalInfo")!=null) {
				dataReceived.setAdditionalInfo(String.valueOf(userActionInputPar.get("additionalInfo")).replace("\"", ""));
			}
		}
		catch(Exception excep) {
			System.out.println(excep.getStackTrace());
			dataReceived.setCustomerId("12345");
			dataReceived.setUserAction("SignIn");
		}
		
		return dataReceived;
	}
}
