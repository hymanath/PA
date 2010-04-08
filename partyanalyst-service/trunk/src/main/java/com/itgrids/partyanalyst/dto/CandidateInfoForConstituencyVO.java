package com.itgrids.partyanalyst.dto;

public class CandidateInfoForConstituencyVO {
	
	private Long constituencyId;
	private String constituencyName;
	private Long candidateId;
	private String candidateName;
	private Long partyId;
	private String party;
	private String partyFlag;
	private String constituencyType;
	private String deformDate;	
	private String latestElecYear;
	
	public String getLatestElecYear() {
		return latestElecYear;
	}
	public void setLatestElecYear(String latestElecYear) {
		this.latestElecYear = latestElecYear;
	}
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
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getPartyFlag() {
		return partyFlag;
	}
	public void setPartyFlag(String partyFlag) {
		this.partyFlag = partyFlag;
	}
	public String getConstituencyType() {
		return constituencyType;
	}
	public void setConstituencyType(String constituencyType) {
		this.constituencyType = constituencyType;
	}
	public String getDeformDate() {
		return deformDate;
	}
	public void setDeformDate(String deformDate) {
		this.deformDate = deformDate;
	}

}
