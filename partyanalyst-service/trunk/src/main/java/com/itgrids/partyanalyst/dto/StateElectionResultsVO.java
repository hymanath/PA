/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 17, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class StateElectionResultsVO {

	private Long electionId;
	private Long electionTypeId;
	private String electionType;
	private String electionYear;
	List<PartyResultsVO> partyResultsVO;
	
	//getters and setters
	
	public Long getElectionId() {
		return electionId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
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
	public List<PartyResultsVO> getPartyResultsVO() {
		return partyResultsVO;
	}
	public void setPartyResultsVO(List<PartyResultsVO> partyResultsVO) {
		this.partyResultsVO = partyResultsVO;
	}
	
}
