/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on March 31,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ElectionResultsReportVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1224213071802672407L;
	
	private String state;
	private String electionType;
	private String electionYear;
	private String statewiseElectionResultsChartName;
	private String stateLevelLineChartWithoutAllianc;
	private String statewiseResultsLineChartName;
	private String districtWiseElecResultsChartName;
	private String partyResultsDistrictLevelChartWithoutAllianc;
	private ResultStatus resultStatus;
	
	private List<SelectOptionVO> partiDistList;
	private List<SelectOptionVO> partiPartiesList;
	private ElectionResultsVO electionBasicResultsVO;
	private ElectionResultsInAllDistrictsVO electionResultsInDistricts;
	
	
    //getters and setters
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public String getElectionYear() {
		return electionYear;
	}

	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public ElectionResultsVO getElectionBasicResultsVO() {
		return electionBasicResultsVO;
	}

	public void setElectionBasicResultsVO(ElectionResultsVO electionBasicResultsVO) {
		this.electionBasicResultsVO = electionBasicResultsVO;
	}

	public String getStatewiseElectionResultsChartName() {
		return statewiseElectionResultsChartName;
	}

	public void setStatewiseElectionResultsChartName(
			String statewiseElectionResultsChartName) {
		this.statewiseElectionResultsChartName = statewiseElectionResultsChartName;
	}

	public ElectionResultsInAllDistrictsVO getElectionResultsInDistricts() {
		return electionResultsInDistricts;
	}

	public void setElectionResultsInDistricts(
			ElectionResultsInAllDistrictsVO electionResultsInDistricts) {
		this.electionResultsInDistricts = electionResultsInDistricts;
	}

	public String getDistrictWiseElecResultsChartName() {
		return districtWiseElecResultsChartName;
	}

	public void setDistrictWiseElecResultsChartName(
			String districtWiseElecResultsChartName) {
		this.districtWiseElecResultsChartName = districtWiseElecResultsChartName;
	}

	public String getStatewiseResultsLineChartName() {
		return statewiseResultsLineChartName;
	}

	public void setStatewiseResultsLineChartName(
			String statewiseResultsLineChartName) {
		this.statewiseResultsLineChartName = statewiseResultsLineChartName;
	}

	public String getStateLevelLineChartWithoutAllianc() {
		return stateLevelLineChartWithoutAllianc;
	}

	public void setStateLevelLineChartWithoutAllianc(
			String stateLevelLineChartWithoutAllianc) {
		this.stateLevelLineChartWithoutAllianc = stateLevelLineChartWithoutAllianc;
	}

	public String getPartyResultsDistrictLevelChartWithoutAllianc() {
		return partyResultsDistrictLevelChartWithoutAllianc;
	}

	public void setPartyResultsDistrictLevelChartWithoutAllianc(
			String partyResultsDistrictLevelChartWithoutAllianc) {
		this.partyResultsDistrictLevelChartWithoutAllianc = partyResultsDistrictLevelChartWithoutAllianc;
	}

	public List<SelectOptionVO> getPartiDistList() {
		return partiDistList;
	}

	public void setPartiDistList(List<SelectOptionVO> partiDistList) {
		this.partiDistList = partiDistList;
	}

	public List<SelectOptionVO> getPartiPartiesList() {
		return partiPartiesList;
	}

	public void setPartiPartiesList(List<SelectOptionVO> partiPartiesList) {
		this.partiPartiesList = partiPartiesList;
	}
	
	
	

}
