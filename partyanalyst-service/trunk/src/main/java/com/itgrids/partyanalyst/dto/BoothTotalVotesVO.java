package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class BoothTotalVotesVO implements Serializable {
	private Long boothID;
	private String villagesCovered;
	private Long totalVotes;
	
	public Long getBoothID() {
		return boothID;
	}
	public void setBoothID(Long boothID) {
		this.boothID = boothID;
	}
	public String getVillagesCovered() {
		return villagesCovered;
	}
	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}
	public Long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}
}
