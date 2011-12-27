package com.itgrids.partyanalyst.dto;

public class PartyPageVO {

	private Long stateId;
	private Long partyId;
	private Long electionId;
	private Long electionTypeId;
	
	public Long getStateId() {
		return stateId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public Long getElectionId() {
		return electionId;
	}
	public Long getElectionTypeId() {
		return electionTypeId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public void setElectionId(Long electionId) {
		this.electionId = electionId;
	}
	public void setElectionTypeId(Long electionTypeId) {
		this.electionTypeId = electionTypeId;
	}
}
