/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on Jun 29, 2010
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ElectionDataVO extends ResultStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long electionId;
	private String electionType;
	private String electionYear;
	private List<SelectOptionVO> partiesHeading;
	private List<ConstituencyMandalVO> constituencyMandalInfo;
	private List<AlliancePartiesInElection> allianceParties;
	
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
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
	public List<AlliancePartiesInElection> getAllianceParties() {
		return allianceParties;
	}
	public void setAllianceParties(List<AlliancePartiesInElection> allianceParties) {
		this.allianceParties = allianceParties;
	}
	
	public List<SelectOptionVO> getPartiesHeading() {
		return partiesHeading;
	}
	public void setPartiesHeading(List<SelectOptionVO> partiesHeading) {
		this.partiesHeading = partiesHeading;
	}
	public List<ConstituencyMandalVO> getConstituencyMandalInfo() {
		return constituencyMandalInfo;
	}
	public void setConstituencyMandalInfo(
			List<ConstituencyMandalVO> constituencyMandalInfo) {
		this.constituencyMandalInfo = constituencyMandalInfo;
	}
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof ElectionDataVO))
			return false;
		ElectionDataVO voObj = (ElectionDataVO) obj;
		return this.electionId.equals(voObj.getElectionId());
	}
	
	@Override
	public int hashCode(){
		return this.electionId.hashCode();
	}

}
