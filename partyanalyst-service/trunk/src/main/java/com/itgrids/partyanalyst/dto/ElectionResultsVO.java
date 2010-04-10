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

public class ElectionResultsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8123738507641834172L;
	
	private String electionType;
	private String electionYear;
	
	private List<AlliancePartyResultsVO> alliancePartiesList;
	private List<PartyPositionsVO> allPartiesResults;
	private List<PartyPositionsVO> allPartiesResultsWithoutGroupingOfAllianc;
	
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
	public List<AlliancePartyResultsVO> getAlliancePartiesList() {
		return alliancePartiesList;
	}
	public void setAlliancePartiesList(
			List<AlliancePartyResultsVO> alliancePartiesList) {
		this.alliancePartiesList = alliancePartiesList;
	}
	public List<PartyPositionsVO> getAllPartiesResults() {
		return allPartiesResults;
	}
	public void setAllPartiesResults(List<PartyPositionsVO> allPartiesResults) {
		this.allPartiesResults = allPartiesResults;
	}
	public List<PartyPositionsVO> getAllPartiesResultsWithoutGroupingOfAllianc() {
		return allPartiesResultsWithoutGroupingOfAllianc;
	}
	public void setAllPartiesResultsWithoutGroupingOfAllianc(
			List<PartyPositionsVO> allPartiesResultsWithoutGroupingOfAllianc) {
		this.allPartiesResultsWithoutGroupingOfAllianc = allPartiesResultsWithoutGroupingOfAllianc;
	}
}
