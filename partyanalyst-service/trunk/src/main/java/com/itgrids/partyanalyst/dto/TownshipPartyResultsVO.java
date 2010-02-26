package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class TownshipPartyResultsVO implements Serializable {
	private Long partyID;
	private String partyName;
	private Long candidateID;
	private String candidateName;
	private String partyVotesInfo;
	private Long votesEarned;
	private Long townshipID;
	private String townshipName;
	
	public Long getPartyID() {
		return partyID;
	}
	public void setPartyID(Long partyID) {
		this.partyID = partyID;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPartyVotesInfo() {
		return partyVotesInfo;
	}
	public void setPartyVotesInfo(String partyVotesInfo) {
		this.partyVotesInfo = partyVotesInfo;
	}
	public Long getCandidateID() {
		return candidateID;
	}
	public void setCandidateID(Long candidateID) {
		this.candidateID = candidateID;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Long getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(Long votesEarned) {
		this.votesEarned = votesEarned;
	}
	public Long getTownshipID() {
		return townshipID;
	}
	public void setTownshipID(Long townshipID) {
		this.townshipID = townshipID;
	}
	public String getTownshipName() {
		return townshipName;
	}
	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}
}
