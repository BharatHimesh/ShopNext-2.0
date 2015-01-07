package com.esper.event.object;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class AlertMessageOM {
	private String messageType;
	private String message;
	private String additionalInfo;
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
}
