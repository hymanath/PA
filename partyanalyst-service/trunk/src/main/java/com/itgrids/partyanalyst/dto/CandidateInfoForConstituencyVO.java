package com.itgrids.partyanalyst.dto;

public class CandidateInfoForConstituencyVO {
	
	private Long constituencyId;
	private String constituencyName;
	private Long candidateId;
	private String CandidateName;
	private String Party;
	
	
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public Long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
	public String getCandidateName() {
		return CandidateName;
	}
	public void setCandidateName(String candidateName) {
		CandidateName = candidateName;
	}
	public String getParty() {
		return Party;
	}
	public void setParty(String party) {
		Party = party;
	}
}
