package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CrossVotedMandalVO {

	private String mandalName;
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

	public CrossVotedMandalVO(String mandalName, Long acValidVotesInMandal,
			Long acEarnedVotesInMandal, Long pcEarnedVotesInMandal,
			Long pcValidVotesInMandal, String acPercentageInMandal,
			String pcPercentageInMandal, String percentageDifferenceInMandal,
			List<CrossVotedBoothVO> crossVotedBooths, String percentageImpactOnConstituency, Long earnedVotesDiffernce) {
		this.mandalName = mandalName;
		this.acValidVotesInMandal = acValidVotesInMandal;
		this.acEarnedVotesInMandal = acEarnedVotesInMandal;
		this.pcEarnedVotesInMandal = pcEarnedVotesInMandal;
		this.pcValidVotesInMandal = pcValidVotesInMandal;
		this.acPercentageInMandal = acPercentageInMandal;
		this.pcPercentageInMandal = pcPercentageInMandal;
		this.percentageDifferenceInMandal = percentageDifferenceInMandal;
		this.crossVotedBooths = crossVotedBooths;
		this.percentageImpactOnConstituency = percentageImpactOnConstituency;
		this.earnedVotesDiffernce = earnedVotesDiffernce;
	}

	public String getPercentageImpactOnConstituency() {
		return percentageImpactOnConstituency;
	}

	public void setPercentageImpactOnConstituency(
			String percentageImpactOnConstituency) {
		this.percentageImpactOnConstituency = percentageImpactOnConstituency;
	}

	public Long getEarnedVotesDiffernce() {
		return earnedVotesDiffernce;
	}

	public void setEarnedVotesDiffernce(Long earnedVotesDiffernce) {
		this.earnedVotesDiffernce = earnedVotesDiffernce;
	}

	public String getMandalName() {
		return mandalName;
	}

	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
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

	public String getPercentageDifferenceInMandal() {
		return percentageDifferenceInMandal;
	}

	public void setPercentageDifferenceInMandal(String percentageDifferenceInMandal) {
		this.percentageDifferenceInMandal = percentageDifferenceInMandal;
	}

	public List<CrossVotedBoothVO> getCrossVotedBooths() {
		return crossVotedBooths;
	}

	public void setCrossVotedBooths(List<CrossVotedBoothVO> crossVotedBooths) {
		this.crossVotedBooths = crossVotedBooths;
	}
		
}
