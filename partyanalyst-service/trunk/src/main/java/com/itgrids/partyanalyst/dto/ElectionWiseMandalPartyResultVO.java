package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Set;

public class ElectionWiseMandalPartyResultVO  implements Serializable {

	private Long electionYear;
	private String electionType;
	private Set<PartyGenderWiseVotesVO> partyVotes;
	private String genderBoothURL;
	private BoothTypeDetailsVO boothTypeDetailsVO;
	
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
	public Set<PartyGenderWiseVotesVO> getPartyVotes() {
		return partyVotes;
	}
	public void setPartyVotes(Set<PartyGenderWiseVotesVO> partyVotes) {
		this.partyVotes = partyVotes;
	}
	public String getGenderBoothURL() {
		return genderBoothURL;
	}
	public void setGenderBoothURL(String genderBoothURL) {
		this.genderBoothURL = genderBoothURL;
	}
	public BoothTypeDetailsVO getBoothTypeDetailsVO() {
		return boothTypeDetailsVO;
	}
	public void setBoothTypeDetailsVO(BoothTypeDetailsVO boothTypeDetailsVO) {
		this.boothTypeDetailsVO = boothTypeDetailsVO;
	}
	
	@Override
	public boolean equals(Object obj){
		ElectionWiseMandalPartyResultVO voObj = (ElectionWiseMandalPartyResultVO) obj;
		return this.electionYear.equals(voObj.getElectionYear()) && this.electionType.equals(voObj.getElectionType());
	}
	
	@Override
	public int hashCode(){
		return this.electionYear.hashCode() + this.electionType.hashCode();
	}
}
