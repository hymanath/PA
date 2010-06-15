package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class BiElectionResultsMainVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<BiElectionResultsVO> biElectionResultsMainVO;
	
	private String mandalWiseResultsChart;
	private String assemblyResultsChartForPresentYear;
	private String assemblyResultsChartForPreviousYear;
	private ConstituencyVO constituencyVO;
	
	public List<BiElectionResultsVO> getBiElectionResultsMainVO() {
		return biElectionResultsMainVO;
	}
	public void setBiElectionResultsMainVO(
			List<BiElectionResultsVO> biElectionResultsMainVO) {
		this.biElectionResultsMainVO = biElectionResultsMainVO;
	}
	public ConstituencyVO getConstituencyVO() {
		return constituencyVO;
	}
	public void setConstituencyVO(ConstituencyVO constituencyVO) {
		this.constituencyVO = constituencyVO;
	}
	public String getMandalWiseResultsChart() {
		return mandalWiseResultsChart;
	}
	public void setMandalWiseResultsChart(String mandalWiseResultsChart) {
		this.mandalWiseResultsChart = mandalWiseResultsChart;
	}
	public String getAssemblyResultsChartForPresentYear() {
		return assemblyResultsChartForPresentYear;
	}
	public void setAssemblyResultsChartForPresentYear(
			String assemblyResultsChartForPresentYear) {
		this.assemblyResultsChartForPresentYear = assemblyResultsChartForPresentYear;
	}
	public String getAssemblyResultsChartForPreviousYear() {
		return assemblyResultsChartForPreviousYear;
	}
	public void setAssemblyResultsChartForPreviousYear(
			String assemblyResultsChartForPreviousYear) {
		this.assemblyResultsChartForPreviousYear = assemblyResultsChartForPreviousYear;
	}
	
	
	

}
