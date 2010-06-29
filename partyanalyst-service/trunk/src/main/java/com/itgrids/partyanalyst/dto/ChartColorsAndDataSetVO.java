package com.itgrids.partyanalyst.dto;

import java.awt.Color;
import java.util.Set;

public class ChartColorsAndDataSetVO {

	private Object dataSet;
	private Set<Color> colorsSet;
	private String chartName;
	
	public Object getDataSet() {
		return dataSet;
	}
	public void setDataSet(Object dataSet) {
		this.dataSet = dataSet;
	}
	public Set<Color> getColorsSet() {
		return colorsSet;
	}
	public void setColorsSet(Set<Color> colorsSet) {
		this.colorsSet = colorsSet;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	
}
