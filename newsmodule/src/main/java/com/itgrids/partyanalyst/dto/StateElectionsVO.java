/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on October 06, 2009
 */
package com.itgrids.partyanalyst.dto;


public class StateElectionsVO {

	private Long electionId;
	private Long electionTypeId;
	private String electionType;
	private String electionSubtype;
	private String year;
	//private List<PartyWiseResultVO> partyResultsVO;
	//private List<String> staticParties;
	//private List<PartyResultsInRegionVO> partyResultsInRegionVOLst;
	//private List<PartyInfoVO> partyAndAVGSeatsWon;
	
	/*public List<String> getStaticParties() {
		return staticParties;
	}
	public void setStaticParties(List<String> staticParties) {
		this.staticParties = staticParties;
	}
	public List<PartyResultsInRegionVO> getPartyResultsInRegionVOLst() {
		return partyResultsInRegionVOLst;
	}
	public void setPartyResultsInRegionVOLst(
			List<PartyResultsInRegionVO> partyResultsInRegionVOLst) {
		this.partyResultsInRegionVOLst = partyResultsInRegionVOLst;
	}*/
	//getters and setters
	public Long getElectionId() {
		return electionId;
	}
	/*public List<PartyInfoVO> getPartyAndAVGSeatsWon() {
		return partyAndAVGSeatsWon;
	}
	public void setPartyAndAVGSeatsWon(List<PartyInfoVO> partyAndAVGSeatsWon) {
		this.partyAndAVGSeatsWon = partyAndAVGSeatsWon;
	}*/
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
	
	/*public List<PartyWiseResultVO> getPartyResultsVO() {
		return partyResultsVO;
	}
	public void setPartyResultsVO(List<PartyWiseResultVO> partyResultsVO) {
		this.partyResultsVO = partyResultsVO;
	}*/
	public String getElectionSubtype() {
		return electionSubtype;
	}
	public void setElectionSubtype(String electionSubtype) {
		this.electionSubtype = electionSubtype;
	}
	
}
