package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ElectionResultVO {

	private Long electionId;
	private String electionType;
	private String electionYear;
	private Long votesEarned;
	private String percentage;
	private BigDecimal percent;
	private String partyName;
	private String partyShortName;
	private Long noOfSeatsWon;
	private String hasAlliance;
	private String votesRange;
	private List<AlliancePartyResultsVO> partiesAlliances;
	private String electionYearAndType;
	private Boolean alliancRes;
	private List<PartyElectionResultVO> result;
	
	public List<PartyElectionResultVO> getResult() {
		return result;
	}

	public void setResult(List<PartyElectionResultVO> result) {
		this.result = result;
	}

	public String getVotesRange() {
		return votesRange;
	}

	public void setVotesRange(String votesRange) {
		this.votesRange = votesRange;
	}

	public Long getElectionId() {
		return electionId;
	}

	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}

	public String getHasAlliance() {
		return hasAlliance;
	}

	public void setHasAlliance(String hasAlliance) {
		this.hasAlliance = hasAlliance;
	}

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

	public void setElectionYearAndType(String electionYearAndType) {
		this.electionYearAndType = electionYearAndType;
	}

	public String getElectionYearAndType() {
		return electionYearAndType;
	}

	public Boolean getAlliancRes() {
		return alliancRes;
	}

	public void setAlliancRes(Boolean alliancRes) {
		this.alliancRes = alliancRes;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}
	
}
