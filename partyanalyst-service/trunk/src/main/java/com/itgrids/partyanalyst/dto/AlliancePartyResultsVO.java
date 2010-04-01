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

public class AlliancePartyResultsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3823508819511237972L;
	
	private String year;
	private String state;
	private String electionType;
	private String allianceGroupName;
	
	private List<PartyPositionsVO> partiesInAlliance;

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

	public List<PartyPositionsVO> getPartiesInAlliance() {
		return partiesInAlliance;
	}

	public void setPartiesInAlliance(List<PartyPositionsVO> partiesInAlliance) {
		this.partiesInAlliance = partiesInAlliance;
	}

}
