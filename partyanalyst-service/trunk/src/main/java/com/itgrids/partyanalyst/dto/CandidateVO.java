package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CandidateVO {
	
	private Long id;
	private String candidateName;
	private String image;
	private String year;
	private String party;
	private List<CandidateElectionVO> candidateElectionVOs;
	
	public CandidateVO(){
		
	}

	public CandidateVO(Long id, String candidateName,String image, String year,
			List<CandidateElectionVO> candidateElectionVOs, String party) {
		this.id = id;
		this.candidateName = candidateName;
		this.year = year;
		this.candidateElectionVOs = candidateElectionVOs;
		this.party = party;
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<CandidateElectionVO> getCandidateElectionVOs() {
		return candidateElectionVOs;
	}

	public void setCandidateElectionVOs(
			List<CandidateElectionVO> candidateElectionVOs) {
		this.candidateElectionVOs = candidateElectionVOs;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
