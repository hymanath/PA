package com.itgrids.partyanalyst.dto;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ElectionResultVO {

	private String electionType;
	private String electionYear;
	private Long votesEarned;
	private String percentage;
	private String partyName;
	private String partyShortName;
	private Long noOfSeatsWon;
	private List<AlliancePartyResultsVO> partiesAlliances;
	
	public Long getNoOfSeatsWon() {
		return noOfSeatsWon;
	}

	public String getPartyShortName() {
		return partyShortName;
	}

	public void setPartyShortName(String partyShortName) {
		this.partyShortName = partyShortName;
	}

	public void setNoOfSeatsWon(Long noOfSeatsWon) {
		this.noOfSeatsWon = noOfSeatsWon;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
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
	
	public Long getVotesEarned() {
		return votesEarned;
	}
	
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	
	public String getPercentage() {
		return percentage;
	}
	
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public List<AlliancePartyResultsVO> getPartiesAlliances() {
		return partiesAlliances;
	}

	public void setPartiesAlliances(List<AlliancePartyResultsVO> partiesAlliances) {
		this.partiesAlliances = partiesAlliances;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof ElectionResultVO))
			return false;
		ElectionResultVO voObj = (ElectionResultVO) obj;
		return new EqualsBuilder().append(electionType, voObj.getElectionType()).
		append(electionYear, voObj.getElectionYear()).isEquals();
	}
	
	@Override
	public int hashCode(){
		return new HashCodeBuilder(17, 37).append(electionType).append(electionYear).toHashCode();
	}
	
}
