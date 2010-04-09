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

public class ElectionResultsInAllDistrictsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7763496680819224195L;
	
	private String electionType;
	private String electionYear;
	
	private List<AlliancePartyDistrictResultsVO> alliancePartiesList;
	private List<DistrictWisePartyPositionsVO> allPartiesResults;
	
	//getters and setters
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
	public List<AlliancePartyDistrictResultsVO> getAlliancePartiesList() {
		return alliancePartiesList;
	}
	public void setAlliancePartiesList(
			List<AlliancePartyDistrictResultsVO> alliancePartiesList) {
		this.alliancePartiesList = alliancePartiesList;
	}
	public List<DistrictWisePartyPositionsVO> getAllPartiesResults() {
		return allPartiesResults;
	}
	public void setAllPartiesResults(
			List<DistrictWisePartyPositionsVO> allPartiesResults) {
		this.allPartiesResults = allPartiesResults;
	}

}
