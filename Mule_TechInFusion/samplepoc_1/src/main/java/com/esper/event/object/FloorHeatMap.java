package com.esper.event.object;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class FloorHeatMap {
	private int totalCustomersOnFloor;
	private int totalCustomersInSection1;
	private int totalCustomersInSection2;
	private int totalCustomersInSection3;
	public int getTotalCustomersOnFloor() {
		return totalCustomersOnFloor;
	}
	public void setTotalCustomersOnFloor(int totalCustomersOnFloor) {
		this.totalCustomersOnFloor = totalCustomersOnFloor;
	}
	public int getTotalCustomersInSection1() {
		return totalCustomersInSection1;
	}
	public void setTotalCustomersInSection1(int totalCustomersInSection1) {
		this.totalCustomersInSection1 = totalCustomersInSection1;
	}
	public int getTotalCustomersInSection2() {
		return totalCustomersInSection2;
	}
	public void setTotalCustomersInSection2(int totalCustomersInSection2) {
		this.totalCustomersInSection2 = totalCustomersInSection2;
	}
	public int getTotalCustomersInSection3() {
		return totalCustomersInSection3;
	}
	public void setTotalCustomersInSection3(int totalCustomersInSection3) {
		this.totalCustomersInSection3 = totalCustomersInSection3;
	}
	
}
