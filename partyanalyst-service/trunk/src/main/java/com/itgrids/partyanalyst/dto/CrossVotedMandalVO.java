package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CrossVotedMandalVO {

	private Long mandalId;
	private String mandalName;
	private Long totalVoters;
	private Long polledVotes;
	private Long acValidVotesInMandal;
	private Long acEarnedVotesInMandal;
	private Long pcEarnedVotesInMandal;
	private Long pcValidVotesInMandal;
	private String acPercentageInMandal;
	private String pcPercentageInMandal;
	private Long earnedVotesDiffernce;
	private String percentageDifferenceInMandal;
	private String percentageImpactOnConstituency;
	private List<CrossVotedBoothVO> crossVotedBooths;
	
	public CrossVotedMandalVO(){
		
	}

	public CrossVotedMandalVO(Long mandalId, String mandalName,
			Long totalVoters, Long polledVotes, Long acValidVotesInMandal,
			Long acEarnedVotesInMandal, Long pcEarnedVotesInMandal,
			Long pcValidVotesInMandal, String acPercentageInMandal,
			String pcPercentageInMandal, Long earnedVotesDiffernce,
			String percentageDifferenceInMandal,
			String percentageImpactOnConstituency,
			List<CrossVotedBoothVO> crossVotedBooths) {
		this.mandalId = mandalId;
		this.mandalName = mandalName;
		this.totalVoters = totalVoters;
		this.polledVotes = polledVotes;
		this.acValidVotesInMandal = acValidVotesInMandal;
		this.acEarnedVotesInMandal = acEarnedVotesInMandal;
		this.pcEarnedVotesInMandal = pcEarnedVotesInMandal;
		this.pcValidVotesInMandal = pcValidVotesInMandal;
		this.acPercentageInMandal = acPercentageInMandal;
		this.pcPercentageInMandal = pcPercentageInMandal;
		this.earnedVotesDiffernce = earnedVotesDiffernce;
		this.percentageDifferenceInMandal = percentageDifferenceInMandal;
		this.percentageImpactOnConstituency = percentageImpactOnConstituency;
		this.crossVotedBooths = crossVotedBooths;
	}

	public Long getMandalId() {
		return mandalId;
	}

	public void setMandalId(Long mandalId) {
		this.mandalId = mandalId;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}

	public Long getTotalVoters() {
		return totalVoters;
	}

	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}

	public Long getPolledVotes() {
		return polledVotes;
	}

	public void setPolledVotes(Long polledVotes) {
		this.polledVotes = polledVotes;
	}

	public Long getAcValidVotesInMandal() {
		return acValidVotesInMandal;
	}

	public void setAcValidVotesInMandal(Long acValidVotesInMandal) {
		this.acValidVotesInMandal = acValidVotesInMandal;
	}

	public Long getAcEarnedVotesInMandal() {
		return acEarnedVotesInMandal;
	}

	public void setAcEarnedVotesInMandal(Long acEarnedVotesInMandal) {
		this.acEarnedVotesInMandal = acEarnedVotesInMandal;
	}

	public Long getPcEarnedVotesInMandal() {
		return pcEarnedVotesInMandal;
	}

	public void setPcEarnedVotesInMandal(Long pcEarnedVotesInMandal) {
		this.pcEarnedVotesInMandal = pcEarnedVotesInMandal;
	}

	public Long getPcValidVotesInMandal() {
		return pcValidVotesInMandal;
	}

	public void setPcValidVotesInMandal(Long pcValidVotesInMandal) {
		this.pcValidVotesInMandal = pcValidVotesInMandal;
	}

	public String getAcPercentageInMandal() {
		return acPercentageInMandal;
	}

	public void setAcPercentageInMandal(String acPercentageInMandal) {
		this.acPercentageInMandal = acPercentageInMandal;
	}

	public String getPcPercentageInMandal() {
		return pcPercentageInMandal;
	}

	public void setPcPercentageInMandal(String pcPercentageInMandal) {
		this.pcPercentageInMandal = pcPercentageInMandal;
	}

	public Long getEarnedVotesDiffernce() {
		return earnedVotesDiffernce;
	}

	public void setEarnedVotesDiffernce(Long earnedVotesDiffernce) {
		this.earnedVotesDiffernce = earnedVotesDiffernce;
	}

	public String getPercentageDifferenceInMandal() {
		return percentageDifferenceInMandal;
	}

	public void setPercentageDifferenceInMandal(String percentageDifferenceInMandal) {
		this.percentageDifferenceInMandal = percentageDifferenceInMandal;
	}

	public String getPercentageImpactOnConstituency() {
		return percentageImpactOnConstituency;
	}

	public void setPercentageImpactOnConstituency(
			String percentageImpactOnConstituency) {
		this.percentageImpactOnConstituency = percentageImpactOnConstituency;
	}

	public List<CrossVotedBoothVO> getCrossVotedBooths() {
		return crossVotedBooths;
	}

	public void setCrossVotedBooths(List<CrossVotedBoothVO> crossVotedBooths) {
		this.crossVotedBooths = crossVotedBooths;
	}

	
		
}
