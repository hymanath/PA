/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 15,2010.
 */

package com.itgrids.partyanalyst.dto;

import java.util.List;

@SuppressWarnings("unused")
public class PartyResultVO {

	private Long partyId;
	private String partyName;
	private String candidateName;
	private Long candidateId;
	private Long constituencyId;
	private String constituencyName;
	private Long rank;
	private Long votesEarned;
	private Long validVotes;
	private String votesPercent;
	private Long electors;
	private CandidateOppositionVO oppositionCandidates;
	private List<ElectionResultVO> electionWiseResults;
	private List<PartyResultVO> partyResultVo;
	private Long seatsWonCountToSort;
	private List<SelectOptionVO> electionList;
	private List<SelectOptionVO> partiesList;
	private List<SelectOptionVO> votesRange;
	private String range;
		
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public List<SelectOptionVO> getVotesRange() {
		return votesRange;
	}
	public void setVotesRange(List<SelectOptionVO> votesRange) {
		this.votesRange = votesRange;
	}
	public List<SelectOptionVO> getElectionList() {
		return electionList;
	}
	public void setElectionList(List<SelectOptionVO> electionList) {
		this.electionList = electionList;
	}
	public List<SelectOptionVO> getPartiesList() {
		return partiesList;
	}
	public void setPartiesList(List<SelectOptionVO> partiesList) {
		this.partiesList = partiesList;
	}
	public List<PartyResultVO> getPartyResultVo() {
		return partyResultVo;
	}
	public void setPartyResultVo(List<PartyResultVO> partyResultVo) {
		this.partyResultVo = partyResultVo;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getRank() {
		return rank;
	}
	public void setRank(Long rank) {
		this.rank = rank;
	}
	public Long getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	public Long getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}
	public String getVotesPercent() {
		return votesPercent;
	}
	public void setVotesPercent(String votesPercent) {
		this.votesPercent = votesPercent;
	}
	public Long getElectors() {
		return electors;
	}
	public void setElectors(Long electors) {
		this.electors = electors;
	}
	public CandidateOppositionVO getOppositionCandidates() {
		return oppositionCandidates;
	}
	public void setOppositionCandidates(CandidateOppositionVO oppositionCandidates) {
		this.oppositionCandidates = oppositionCandidates;
	}
	public List<ElectionResultVO> getElectionWiseResults() {
		return electionWiseResults;
	}
	public void setElectionWiseResults(List<ElectionResultVO> electionWiseResults) {
		this.electionWiseResults = electionWiseResults;
	}

	public Long getSeatsWonCountToSort() {
		return seatsWonCountToSort;
	}
	public void setSeatsWonCountToSort(Long seatsWonCountToSort) {
		this.seatsWonCountToSort = seatsWonCountToSort;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof PartyResultVO))
			return false;
		PartyResultVO voObj = (PartyResultVO) obj;
		return this.partyId.equals(voObj.getPartyId());
	}
	
	@Override
	public int hashCode(){
		return this.partyId.hashCode();
	}
	
}
