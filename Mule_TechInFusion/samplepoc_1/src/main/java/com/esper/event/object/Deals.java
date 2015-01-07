package com.esper.event.object;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Deals {
	private String matchKey;
	private String matchCriteria;
	private String dealStatement;
	
	public String getMatchCriteria() {
		return matchCriteria;
	}
	public void setMatchCriteria(String matchCriteria) {
		this.matchCriteria = matchCriteria;
	}
	public String getDealStatement() {
		return dealStatement;
	}
	public void setDealStatement(String dealStatement) {
		this.dealStatement = dealStatement;
	}
	public String getMatchKey() {
		return matchKey;
	}
	public void setMatchKey(String matchKey) {
		this.matchKey = matchKey;
	}
	
}
