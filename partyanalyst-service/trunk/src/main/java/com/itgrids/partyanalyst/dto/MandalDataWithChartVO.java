package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class MandalDataWithChartVO {

	String chart;
	List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO;
	private List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO1 = new ArrayList<MandalAllElectionDetailsVO>(0);
	public MandalDataWithChartVO(){
		
	}
	
	public MandalDataWithChartVO(String chart,
			List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO) {
		this.chart = chart;
		this.mandalAllElectionDetailsVO = mandalAllElectionDetailsVO;
	}

	

	public List<MandalAllElectionDetailsVO> getMandalAllElectionDetailsVO1() {
		return mandalAllElectionDetailsVO1;
	}

	public void setMandalAllElectionDetailsVO1(
			List<MandalAllElectionDetailsVO> mandalAllElectionDetailsVO1) {
		this.mandalAllElectionDetailsVO1 = mandalAllElectionDetailsVO1;
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
