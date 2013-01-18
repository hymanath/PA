package com.itgrids.partyanalyst.dto;

import java.util.List;

public class PartyVotesEarnedVO {

	private Long partyId;
	private String partyName;
	private Long votesEarned;
	private String percentages;
	private String electionYear;
	private String electionType;
	private List<PartyVotesEarnedVO> partyVotesEarnedVOs;
	private PartyVotesEarnedVO votesEarnedVO;
	private List<String> partiesList;
	
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
	
	public Long getVotesEarned() {
		return votesEarned;
	}
	
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}

	public String getPercentages() {
		return percentages;
	}

	public void setPercentages(String percentages) {
		this.percentages = percentages;
	}

	public List<PartyVotesEarnedVO> getPartyVotesEarnedVOs() {
		return partyVotesEarnedVOs;
	}

	public void setPartyVotesEarnedVOs(List<PartyVotesEarnedVO> partyVotesEarnedVOs) {
		this.partyVotesEarnedVOs = partyVotesEarnedVOs;
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

	public PartyVotesEarnedVO getVotesEarnedVO() {
		return votesEarnedVO;
	}

	public void setVotesEarnedVO(PartyVotesEarnedVO votesEarnedVO) {
		this.votesEarnedVO = votesEarnedVO;
	}
	public List<String> getPartiesList() {
		return partiesList;
	}
	public void setPartiesList(List<String> partiesList) {
		this.partiesList = partiesList;
	}
	
	
}
