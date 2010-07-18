package com.itgrids.partyanalyst.dto;

public class DataTransferVO extends ResultStatus{

	private static final long serialVersionUID = 1L;
	private Object presentYearChart;
	private Object previousYearChart;
	
	public Object getPresentYearChart() {
		return presentYearChart;
	}
	
	public void setPresentYearChart(Object presentYearChart) {
		this.presentYearChart = presentYearChart;
	}
	
	public Object getPreviousYearChart() {
		return previousYearChart;
	}
	
	public void setPreviousYearChart(Object previousYearChart) {
		this.previousYearChart = previousYearChart;
	}

}
