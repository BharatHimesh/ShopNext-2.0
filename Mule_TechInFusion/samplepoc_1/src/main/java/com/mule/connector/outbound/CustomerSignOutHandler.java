package com.mule.connector.outbound;

import com.esper.event.object.SignOut;
import com.mule.inbound.objects.UserAction;

public class CustomerSignOutHandler {

	public SignOut ProcessPerson(UserAction userActionInput) {
		
		SignOut signOut = new SignOut();
		
		signOut.setCustomerId(userActionInput.getCustomerId());
		signOut.setCartId(userActionInput.getAdditionalInfo());
		
		return signOut;		
	}
}
