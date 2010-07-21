package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class BiElectionResultsMainVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<BiElectionResultsVO> biElectionResultsMainVO;
	
	private String constituencyType;
	private String mandalWiseResultsChart;
	private String enlargedMandalWiseResultsChart;
	private String assemblyResultsChartForPresentYear;
	private String assemblyResultsChartForPreviousYear;
	private ConstituencyVO constituencyVO;
	private List<String> electionResultsChart;
	private List<String> allPartiesElectionResultsChart;
	private List<ElectionTypeChartVO> chartsListForElectionTypes;
	private List<ElectionTypeChartVO> chartsListForElectionTypesAllParties;
	private List<ElectionDataVO> urbanRuralConstiResults;
	private ElectionWiseMandalPartyResultListVO allPartiesElecInfo;
	
	public List<String> getAllPartiesElectionResultsChart() {
		return allPartiesElectionResultsChart;
	}
	public void setAllPartiesElectionResultsChart(
			List<String> allPartiesElectionResultsChart) {
		this.allPartiesElectionResultsChart = allPartiesElectionResultsChart;
	}
	public List<String> getElectionResultsChart() {
		return electionResultsChart;
	}
	public void setElectionResultsChart(List<String> electionResultsChart) {
		this.electionResultsChart = electionResultsChart;
	}
	public String getEnlargedMandalWiseResultsChart() {
		return enlargedMandalWiseResultsChart;
	}
	public void setEnlargedMandalWiseResultsChart(
			String enlargedMandalWiseResultsChart) {
		this.enlargedMandalWiseResultsChart = enlargedMandalWiseResultsChart;
	}
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
	public void setChartsListForElectionTypes(
			List<ElectionTypeChartVO> chartsListForElectionTypes) {
		this.chartsListForElectionTypes = chartsListForElectionTypes;
	}
	public List<ElectionTypeChartVO> getChartsListForElectionTypes() {
		return chartsListForElectionTypes;
	}
	public void setChartsListForElectionTypesAllParties(
			List<ElectionTypeChartVO> chartsListForElectionTypesAllParties) {
		this.chartsListForElectionTypesAllParties = chartsListForElectionTypesAllParties;
	}
	public List<ElectionTypeChartVO> getChartsListForElectionTypesAllParties() {
		return chartsListForElectionTypesAllParties;
	}
	public void setUrbanRuralConstiResults(List<ElectionDataVO> urbanRuralConstiResults) {
		this.urbanRuralConstiResults = urbanRuralConstiResults;
	}
	public List<ElectionDataVO> getUrbanRuralConstiResults() {
		return urbanRuralConstiResults;
	}
	public ElectionWiseMandalPartyResultListVO getAllPartiesElecInfo() {
		return allPartiesElecInfo;
	}
	public void setAllPartiesElecInfo(
			ElectionWiseMandalPartyResultListVO allPartiesElecInfo) {
		this.allPartiesElecInfo = allPartiesElecInfo;
	}
	public String getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}
	
	
	

}
