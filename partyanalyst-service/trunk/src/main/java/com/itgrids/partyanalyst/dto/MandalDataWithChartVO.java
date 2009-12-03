package com.itgrids.partyanalyst.dto;

import java.util.List;

public class MandalDataWithChartVO {

	String chart;
	List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO;
	
	public MandalDataWithChartVO(){
		
	}
	
	public MandalDataWithChartVO(String chart,
			List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO) {
		this.chart = chart;
		this.mandalAllElectionDetailsVO = mandalAllElectionDetailsVO;
	}

	public String getChart() {
		return chart;
	}
	public void setChart(String chart) {
		this.chart = chart;
	}
	public List<MandalAllElectionDetailsVO> getMandalAllElectionDetailsVO() {
		return mandalAllElectionDetailsVO;
	}
	public void setMandalAllElectionDetailsVO(
			List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO) {
		this.mandalAllElectionDetailsVO = mandalAllElectionDetailsVO;
	}
	
	
}
