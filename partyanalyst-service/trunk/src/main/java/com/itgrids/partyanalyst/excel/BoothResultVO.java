package com.itgrids.partyanalyst.excel;

public class BoothResultVO {
	
	private Long partNo;
	private String location;
	private String villagesCovered;
	private int votesEarned;
	private int totalVoters;
	private String percentage;
	private String mandal;
	
	public BoothResultVO(){
		
	}
	
	public BoothResultVO(Long partNo, String location,
			String villagesCovered, int votesEarned, int totalVoters,
			String percentage, String mandal) {
		this.partNo = partNo;
		this.location = location;
		this.villagesCovered = villagesCovered;
		this.votesEarned = votesEarned;
		this.totalVoters = totalVoters;
		this.percentage = percentage;
		this.mandal = mandal;
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

}
