package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PartyPostionInfoVO implements Serializable{
	//private String constituencyName;
	private String partyName;
	private String candidateName;
	private Integer rank;
	private BigDecimal votePercentage;
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
	public BigDecimal getVotePercentage() {
		return votePercentage;
	}
	public void setVotePercentage(BigDecimal votePercentage) {
		this.votePercentage = votePercentage;
	}
	
	

}
