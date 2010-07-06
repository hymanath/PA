package com.itgrids.partyanalyst.dto;

import java.util.List;

public class TeshilPartyInfoVO {
	
	private String partyName;
	private Long participatedSeats;
	private Long totalValidVotes;
	private Long totalVotesWonByParty;
	private Long seatsWonByParty;
	private Float percentageOfVotesWonByParty;
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
}
