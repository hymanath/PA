/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 06, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.util.List;

public class StateElectionsVO {

	private Long electionId;
	private Long electionTypeId;
	private String electionType;
	private String year;
	List<PartyWiseResultVO> partyResultsVO;
	
	//getters and setters
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
	
	public List<PartyWiseResultVO> getPartyResultsVO() {
		return partyResultsVO;
	}
	public void setPartyResultsVO(List<PartyWiseResultVO> partyResultsVO) {
		this.partyResultsVO = partyResultsVO;
	}
	
}
