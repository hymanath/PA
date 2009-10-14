/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;

public class ConstituencyElectionResultsVO {

	private Long electionId;
	private String electionType;
	private String electionYear;
	private Date electionDate;
	private List<PartyResultsVO> partyResultsVO;
	private CandidateWonVO candidateResultsVO;
	private List<CandidateOppositionVO> candidateOppositionList;
	
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
	public String getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(String electionYear) {
		this.electionYear = electionYear;
	}
	public Date getElectionDate() {
		return electionDate;
	}
	public void setElectionDate(Date electionDate) {
		this.electionDate = electionDate;
	}
	public List<PartyResultsVO> getPartyResultsVO() {
		return partyResultsVO;
	}
	public void setPartyResultsVO(List<PartyResultsVO> partyResultsVO) {
		this.partyResultsVO = partyResultsVO;
	}
	public CandidateWonVO getCandidateResultsVO() {
		return candidateResultsVO;
	}
	public void setCandidateResultsVO(CandidateWonVO candidateResultsVO) {
		this.candidateResultsVO = candidateResultsVO;
	}
	public List<CandidateOppositionVO> getCandidateOppositionList() {
		return candidateOppositionList;
	}
	public void setCandidateOppositionList(
			List<CandidateOppositionVO> candidateOppositionList) {
		this.candidateOppositionList = candidateOppositionList;
	}
	
}
