package com.mule.layer.dao;

import com.esper.event.object.FloorHeatMap;

public class HeatMapData {

	private static FloorHeatMap heatMapData = new FloorHeatMap();

	public static FloorHeatMap getHeatMapData() {
		return heatMapData;
	}

	public static void setHeatMapData(FloorHeatMap heatMapData) {
		HeatMapData.heatMapData = heatMapData;
	}
}
