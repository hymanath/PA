package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ConstituencyWinnerInfoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String constituencyName;
	private String constituencyId;
	private String candidateName; // winner in the constituency
	private String candidateId;
	private String partyName;
	
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	
	
}
