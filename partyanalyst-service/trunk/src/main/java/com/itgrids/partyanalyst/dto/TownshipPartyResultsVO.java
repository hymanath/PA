package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class TownshipPartyResultsVO implements Serializable {
	private Long partyID;
	private String partyName;
	private String partyVotesInfo;
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
}
