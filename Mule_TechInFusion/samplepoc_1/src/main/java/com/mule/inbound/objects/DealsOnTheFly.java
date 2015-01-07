package com.mule.inbound.objects;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "DealsOnTheFly")
public class DealsOnTheFly {
	
	@XmlElement(name = "MatchKey", required = true)
	protected String matchKey;
	@XmlElement(name = "MatchCriteria", required = true)
	protected String matchCriteria;
	@XmlElement(name = "DealStatement", required = true)
	protected String dealStatement;
	
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
