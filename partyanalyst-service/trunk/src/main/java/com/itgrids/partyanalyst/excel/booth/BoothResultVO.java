package com.itgrids.partyanalyst.excel.booth;

public class BoothResultVO {
	
	private Long boothId;
	private String partNo;
	private String location;
	private String villagesCovered;
	private int votesEarned;
	private int totalVoters;
	private String percentage;
	private String mandal;
	
	private Long oppPartyId;
	private String oppParty;
	private int oppPartyVotesEarned;
	private String oppPartyPercentage;
	
	public BoothResultVO(){
		
	}
	
	public BoothResultVO(String partNo, String location,
			String villagesCovered, int votesEarned, int totalVoters,
			String percentage,String mandal) {
		this.partNo = partNo;
		this.location = location;
		this.villagesCovered = villagesCovered;
		this.votesEarned = votesEarned;
		this.totalVoters = totalVoters;
		this.percentage = percentage;
		this.mandal = mandal;
	}
	
	
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
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
	public int getVotesEarned() {
		return votesEarned;
	}
	public void setVotesEarned(int votesEarned) {
		this.votesEarned = votesEarned;
	}
	public int getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(int totalVoters) {
		this.totalVoters = totalVoters;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public Long getOppPartyId() {
		return oppPartyId;
	}

	public void setOppPartyId(Long oppPartyId) {
		this.oppPartyId = oppPartyId;
	}

	public String getOppParty() {
		return oppParty;
	}

	public void setOppParty(String oppParty) {
		this.oppParty = oppParty;
	}

	public int getOppPartyVotesEarned() {
		return oppPartyVotesEarned;
	}

	public void setOppPartyVotesEarned(int oppPartyVotesEarned) {
		this.oppPartyVotesEarned = oppPartyVotesEarned;
	}

	public String getOppPartyPercentage() {
		return oppPartyPercentage;
	}

	public void setOppPartyPercentage(String oppPartyPercentage) {
		this.oppPartyPercentage = oppPartyPercentage;
	}

	public Long getBoothId() {
		return boothId;
	}

	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}

	
}
