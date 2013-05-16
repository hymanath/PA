package com.itgrids.partyanalyst.dto;

import java.util.List;

public class TeshilPartyInfoVO {
	
	private String partyName;
	private Long participatedSeats;
	private Long totalValidVotes;
	private Long votesGained;
	private Long totalVotesWonByParty;
	private Long seatsWonByParty;
	private Float percentageOfVotesWonByParty;
	private Float othersPercent;
	private String muncipalityName;
	private Long muncipalityId;
	private Double totalVoters;
	private Float totalPolledVotes;
	private Long totalSeats;
	private Long totalWards;	
	private int totalMuncipalities;
	private List<TeshilPartyInfoVO> muncipalityVO;
	private String latestMuncipalElectionYear;
	private ResultStatus resultStatus;
	private String chartName;
	private Long partyId;
	private String totalVotersInConstituency;
	private Long stateId;
	private Long electionTypeId;
	private Long partyWonSeats;
	private Long partySecndPos;
	private Long partyThirdPos;
	private Long partyNthPos;
	private String Year;
	private String totConstiVotesPercent;
	private String partiPartiVotesPercent;
	private Long totalConstiValidVotes;
	private Long partyParticipatedValidVotes;
	private String electionType;
	
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public String getTotalVotersInConstituency() {
		return totalVotersInConstituency;
	}
	public void setTotalVotersInConstituency(String totalVotersInConstituency) {
		this.totalVotersInConstituency = totalVotersInConstituency;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public Long getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(Long totalSeats) {
		this.totalSeats = totalSeats;
	}
	public String getLatestMuncipalElectionYear() {
		return latestMuncipalElectionYear;
	}
	public void setLatestMuncipalElectionYear(String latestMuncipalElectionYear) {
		this.latestMuncipalElectionYear = latestMuncipalElectionYear;
	}
	public List<TeshilPartyInfoVO> getMuncipalityVO() {
		return muncipalityVO;
	}
	public void setMuncipalityVO(List<TeshilPartyInfoVO> muncipalityVO) {
		this.muncipalityVO = muncipalityVO;
	}
	public Float getPercentageOfVotesWonByParty() {
		return percentageOfVotesWonByParty;
	}
	public String getMuncipalityName() {
		return muncipalityName;
	}
	public void setMuncipalityName(String muncipalityName) {
		this.muncipalityName = muncipalityName;
	}
	public Long getMuncipalityId() {
		return muncipalityId;
	}
	public void setMuncipalityId(Long muncipalityId) {
		this.muncipalityId = muncipalityId;
	}
	public Float getTotalPolledVotes() {
		return totalPolledVotes;
	}
	public void setTotalPolledVotes(Float totalPolledVotes) {
		this.totalPolledVotes = totalPolledVotes;
	}
	public Double getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Double totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Long getTotalWards() {
		return totalWards;
	}
	public int getTotalMuncipalities() {
		return totalMuncipalities;
	}
	public void setTotalMuncipalities(int totalMuncipalities) {
		this.totalMuncipalities = totalMuncipalities;
	}
	public void setTotalWards(Long totalWards) {
		this.totalWards = totalWards;
	}
	public void setPercentageOfVotesWonByParty(Float percentageOfVotesWonByParty) {
		this.percentageOfVotesWonByParty = percentageOfVotesWonByParty;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Long getParticipatedSeats() {
		return participatedSeats;
	}
	public void setParticipatedSeats(Long participatedSeats) {
		this.participatedSeats = participatedSeats;
	}
	public Long getTotalValidVotes() {
		return totalValidVotes;
	}
	public Long getSeatsWonByParty() {
		return seatsWonByParty;
	}
	public void setSeatsWonByParty(Long seatsWonByParty) {
		this.seatsWonByParty = seatsWonByParty;
	}
	public void setTotalValidVotes(Long totalValidVotes) {
		this.totalValidVotes = totalValidVotes;
	}
	public Long getTotalVotesWonByParty() {
		return totalVotesWonByParty;
	}
	public void setTotalVotesWonByParty(Long totalVotesWonByParty) {
		this.totalVotesWonByParty = totalVotesWonByParty;
	}
	public Float getOthersPercent() {
		return othersPercent;
	}
	public void setOthersPercent(Float othersPercent) {
		this.othersPercent = othersPercent;
	}
	public Long getPartyWonSeats() {
		return partyWonSeats;
	}
	public void setPartyWonSeats(Long partyWonSeats) {
		this.partyWonSeats = partyWonSeats;
	}
	public Long getPartySecndPos() {
		return partySecndPos;
	}
	public void setPartySecndPos(Long partySecndPos) {
		this.partySecndPos = partySecndPos;
	}
	public Long getPartyThirdPos() {
		return partyThirdPos;
	}
	public void setPartyThirdPos(Long partyThirdPos) {
		this.partyThirdPos = partyThirdPos;
	}
	public Long getPartyNthPos() {
		return partyNthPos;
	}
	public void setPartyNthPos(Long partyNthPos) {
		this.partyNthPos = partyNthPos;
	}
	public Long getVotesGained() {
		return votesGained;
	}
	public void setVotesGained(Long votesGained) {
		this.votesGained = votesGained;
	}
	public Long getTotalConstiValidVotes() {
		return totalConstiValidVotes;
	}
	public void setTotalConstiValidVotes(Long totalConstiValidVotes) {
		this.totalConstiValidVotes = totalConstiValidVotes;
	}
	public Long getPartyParticipatedValidVotes() {
		return partyParticipatedValidVotes;
	}
	public void setPartyParticipatedValidVotes(Long partyParticipatedValidVotes) {
		this.partyParticipatedValidVotes = partyParticipatedValidVotes;
	}
	public String getTotConstiVotesPercent() {
		return totConstiVotesPercent;
	}
	public void setTotConstiVotesPercent(String totConstiVotesPercent) {
		this.totConstiVotesPercent = totConstiVotesPercent;
	}
	public String getPartiPartiVotesPercent() {
		return partiPartiVotesPercent;
	}
	public void setPartiPartiVotesPercent(String partiPartiVotesPercent) {
		this.partiPartiVotesPercent = partiPartiVotesPercent;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	
}
