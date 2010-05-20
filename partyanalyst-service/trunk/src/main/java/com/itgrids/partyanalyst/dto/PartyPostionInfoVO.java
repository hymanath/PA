package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class PartyPostionInfoVO implements Serializable, Comparable<PartyPostionInfoVO>{
	//private String constituencyName;
	private String partyName;
	private String candidateName;
	private Integer rank;
	private String votePercentage;
	/*public String getConstituencyName() {
		return constituencyName;
	}
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}*/
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getVotePercentage() {
		return votePercentage;
	}
	public void setVotePercentage(String votePercentage) {
		this.votePercentage = votePercentage;
	}
	
	public int compareTo(PartyPostionInfoVO obj) {
		return this.rank.compareTo(obj.getRank());
	}
	
	

}
