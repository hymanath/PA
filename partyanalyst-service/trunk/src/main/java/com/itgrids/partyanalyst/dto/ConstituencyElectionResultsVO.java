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

public class ConstituencyElectionResultsVO extends ResultStatus{

	private Long electionId;
	private String electionType;
	private String electionYear;
	private Date electionDate;
	private String constituencyName;
	private Long constituencyId;
	private Long totalVoters;
	private Long validVotes;
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
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}
	
}
