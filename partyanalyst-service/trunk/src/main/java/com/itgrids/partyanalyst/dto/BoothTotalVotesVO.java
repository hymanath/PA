package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class BoothTotalVotesVO implements Serializable {
	
	private Long boothID;
	private Long partNo;
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
	@Override
	public boolean equals(Object obj){
		BoothTotalVotesVO voObj = (BoothTotalVotesVO) obj;
		return this.boothID.equals(voObj.getBoothID());
	}
	
	@Override
	public int hashCode(){
		return this.boothID.hashCode();
	}
}
