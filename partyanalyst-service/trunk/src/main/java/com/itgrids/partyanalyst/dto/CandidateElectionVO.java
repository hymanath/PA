package com.itgrids.partyanalyst.dto;

public class CandidateElectionVO {
	
	private String constituencyName;
	private String position;
	private String scope;
	
	public CandidateElectionVO(){
		
	}
	
	public CandidateElectionVO(String constituencyName, String position,
			String scope) {
		
		this.constituencyName = constituencyName;
		this.position = position;
		this.scope = scope;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
}
