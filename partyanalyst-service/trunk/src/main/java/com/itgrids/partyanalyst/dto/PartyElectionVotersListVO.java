package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List; 

public class PartyElectionVotersListVO implements Serializable {
	private String partyName;
	private List<String> partyElectionVotersList1;
	
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public List<String> getPartyElectionVotersList1() {
		return partyElectionVotersList1;
	}
	public void setPartyElectionVotersList1(List<String> partyElectionVotersList1) {
		this.partyElectionVotersList1 = partyElectionVotersList1;
	}
	
}