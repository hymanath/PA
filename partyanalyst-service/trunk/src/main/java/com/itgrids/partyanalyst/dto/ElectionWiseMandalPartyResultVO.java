package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

public class ElectionWiseMandalPartyResultVO  implements Serializable {

	private Long electionYear;
	private String electionType;
	private List<PartyGenderWiseVotesVO> partyVotes;
	private String genderBoothURL;
	private List<BoothTotalVotesVO> boothTotalVotes;
	
	public Long getElectionYear() {
		return electionYear;
	}
	public void setElectionYear(Long electionYear) {
		this.electionYear = electionYear;
	}
	public String getElectionType() {
		return electionType;
	}
	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}
	public List<PartyGenderWiseVotesVO> getPartyVotes() {
		return partyVotes;
	}
	public void setPartyVotes(List<PartyGenderWiseVotesVO> partyVotes) {
		this.partyVotes = partyVotes;
	}
	public String getGenderBoothURL() {
		return genderBoothURL;
	}
	public void setGenderBoothURL(String genderBoothURL) {
		this.genderBoothURL = genderBoothURL;
	}
	public List<BoothTotalVotesVO> getBoothTotalVotes() {
		return boothTotalVotes;
	}
	public void setBoothTotalVotes(List<BoothTotalVotesVO> boothTotalVotes) {
		this.boothTotalVotes = boothTotalVotes;
	}
}
