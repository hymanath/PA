/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on April 07,2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class AlliancePartyDistrictResultsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3413293320777147766L;
	
	private String year;
	private String state;
	private String electionType;
	private String allianceGroupName;
	
	private List<DistrictWisePartyPositionsVO> partiesInAlliance;
	
	//getters and setters
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
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
	public String getAllianceGroupName() {
		return allianceGroupName;
	}
	public void setAllianceGroupName(String allianceGroupName) {
		this.allianceGroupName = allianceGroupName;
	}
	public List<DistrictWisePartyPositionsVO> getPartiesInAlliance() {
		return partiesInAlliance;
	}
	public void setPartiesInAlliance(
			List<DistrictWisePartyPositionsVO> partiesInAlliance) {
		this.partiesInAlliance = partiesInAlliance;
	}
	

}
