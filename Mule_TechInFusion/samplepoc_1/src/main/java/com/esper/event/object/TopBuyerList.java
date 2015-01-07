package com.esper.event.object;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class TopBuyerList {
	private List<TopBuyer> topBuyer;

	public List<TopBuyer> getTopBuyer() {
		return topBuyer;
	}
	public void setTopBuyer(List<TopBuyer> topBuyer) {
		this.topBuyer = topBuyer;
	}
}
