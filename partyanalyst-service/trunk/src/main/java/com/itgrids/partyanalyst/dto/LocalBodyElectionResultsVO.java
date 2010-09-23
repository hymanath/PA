/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 21, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class LocalBodyElectionResultsVO extends TeshilPartyInfoVO implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long localBodyId;
	private Long localBodyElectionId;
	private Long localBodyElectionTypeId;	
	private String localBodyElectionType;
	private String localBodyElectionYear;
	private String localBodyRegion;
	
	private Long stateId;
	private Long tehsilId;
	private Long districtId;
	private String state;
	private String tehsil;
	private String district;
	
	private String highLevelChart;
	private List<SelectOptionVO> otherElectionYears;
	
	public List<SelectOptionVO> getOtherElectionYears() {
		return otherElectionYears;
	}
	public void setOtherElectionYears(List<SelectOptionVO> otherElectionYears) {
		this.otherElectionYears = otherElectionYears;
	}
	private ResultStatus resultStatus;
	
	//Getters and Setters
	public Long getLocalBodyElectionId() {
		return localBodyElectionId;
	}
	public void setLocalBodyElectionId(Long localBodyElectionId) {
		this.localBodyElectionId = localBodyElectionId;
	}
	public String getLocalBodyElectionType() {
		return localBodyElectionType;
	}
	public void setLocalBodyElectionType(String localBodyElectionType) {
		this.localBodyElectionType = localBodyElectionType;
	}
	public String getLocalBodyElectionYear() {
		return localBodyElectionYear;
	}
	public void setLocalBodyElectionYear(String localBodyElectionYear) {
		this.localBodyElectionYear = localBodyElectionYear;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getLocalBodyRegion() {
		return localBodyRegion;
	}
	public void setLocalBodyRegion(String localBodyRegion) {
		this.localBodyRegion = localBodyRegion;
	}
	public Long getLocalBodyId() {
		return localBodyId;
	}
	public void setLocalBodyId(Long localBodyId) {
		this.localBodyId = localBodyId;
	}
	public Long getLocalBodyElectionTypeId() {
		return localBodyElectionTypeId;
	}
	public void setLocalBodyElectionTypeId(Long localBodyElectionTypeId) {
		this.localBodyElectionTypeId = localBodyElectionTypeId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTehsil() {
		return tehsil;
	}
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getHighLevelChart() {
		return highLevelChart;
	}
	public void setHighLevelChart(String highLevelChart) {
		this.highLevelChart = highLevelChart;
	}


}
