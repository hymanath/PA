package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class BoothTotalVotesVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7624183188745715882L;
	private Long boothID;
	private Long partNo;
	private String location;
	private String villagesCovered;
	private Long maleVotes;
	private Long femaleVotes;
	private Long totalVotes;
	private Long validVotes;
	
	public Long getBoothID() {
		return boothID;
	}
	public void setBoothID(Long boothID) {
		this.boothID = boothID;
	}
	public Long getPartNo() {
		return partNo;
	}
	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getVillagesCovered() {
		return villagesCovered;
	}
	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}
	public Long getMaleVotes() {
		return maleVotes;
	}
	public void setMaleVotes(Long maleVotes) {
		this.maleVotes = maleVotes;
	}
	public Long getFemaleVotes() {
		return femaleVotes;
	}
	public void setFemaleVotes(Long femaleVotes) {
		this.femaleVotes = femaleVotes;
	}
	public Long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(Long totalVotes) {
		this.totalVotes = totalVotes;
	}
	public Long getValidVotes() {
		return validVotes;
	}
	public void setValidVotes(Long validVotes) {
		this.validVotes = validVotes;
	}
	
}
