package com.itgrids.partyanalyst.excel;

public class CandidateElectionResult {

	private String candidateName;
	private String candidatePrty;
	private Double votesEarned;
	private String votesPercentage;
	private String sex;
	
	private Double assets;
	private Double liabilities;
	private String education;
	private String criminalCharges;
	
	public CandidateElectionResult(){
		
	}
	
	public CandidateElectionResult(String candidateName, String candidatePrty,
			Double votesEarned) {
		this.candidateName = candidateName;
		this.candidatePrty = candidatePrty;
		this.votesEarned = votesEarned;
	}	
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getCandidatePrty() {
		return candidatePrty;
	}
	public void setCandidatePrty(String candidatePrty) {
		this.candidatePrty = candidatePrty;
	}
	public Double getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Double votesEarned) {
		this.votesEarned = votesEarned;
	}

	public String getVotesPercentage() {
		return votesPercentage;
	}

	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Double getAssets() {
		return assets;
	}

	public void setAssets(Double assets) {
		this.assets = assets;
	}

	public Double getLiabilities() {
		return liabilities;
	}

	public void setLiabilities(Double liabilities) {
		this.liabilities = liabilities;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getCriminalCharges() {
		return criminalCharges;
	}

	public void setCriminalCharges(String criminalCharges) {
		this.criminalCharges = criminalCharges;
	}
}
