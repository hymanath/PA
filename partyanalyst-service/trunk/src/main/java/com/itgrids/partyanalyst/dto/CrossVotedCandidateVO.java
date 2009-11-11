package com.itgrids.partyanalyst.dto;

public class CrossVotedCandidateVO {

	private Long candidateId;
	private String candidateName;
	private String rank;
	private String votesPercentage;
	private String image;
	
	public CrossVotedCandidateVO(){
		
	}

	public CrossVotedCandidateVO(Long candidateId, String candidateName,
			String rank, String votesPercentage, String image) {
		this.candidateId = candidateId;
		this.candidateName = candidateName;
		this.rank = rank;
		this.votesPercentage = votesPercentage;
		this.image = image;
	}

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getVotesPercentage() {
		return votesPercentage;
	}

	public void setVotesPercentage(String votesPercentage) {
		this.votesPercentage = votesPercentage;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
