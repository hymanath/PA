package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class CastTotalVotersVO implements Serializable {

	private String casteName;
	private Long totalVoters;
	private Double voterPercentage;
	
	public String getCasteName() {
		return casteName;
	}
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public Double getVoterPercentage() {
		return voterPercentage;
	}
	public void setVoterPercentage(Double voterPercentage) {
		this.voterPercentage = voterPercentage;
	}
	
}
