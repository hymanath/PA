package com.itgrids.partyanalyst.dto;

public class CrossVotedBoothVO {

	private String partNO;
	private String villagesCovered;
	private String acPercentage;
	private String pcPercentage;
	private Long acValidVotes;
	private Long pcValidVotes;
	private String percentageDifference;
	
	public CrossVotedBoothVO(){
		
	}
	
	public CrossVotedBoothVO(String partNO, String villagesCovered,
			String acPercentage, String pcPercentage, Long acValidVotes,
			Long pcValidVotes, String percentageDifference) {
		this.partNO = partNO;
		this.villagesCovered = villagesCovered;
		this.acPercentage = acPercentage;
		this.pcPercentage = pcPercentage;
		this.acValidVotes = acValidVotes;
		this.pcValidVotes = pcValidVotes;
		this.percentageDifference = percentageDifference;
	}

	public String getPartNO() {
		return partNO;
	}
	public void setPartNO(String partNO) {
		this.partNO = partNO;
	}
	public String getVillagesCovered() {
		return villagesCovered;
	}
	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}
	public String getAcPercentage() {
		return acPercentage;
	}
	public void setAcPercentage(String acPercentage) {
		this.acPercentage = acPercentage;
	}
	public String getPcPercentage() {
		return pcPercentage;
	}
	public void setPcPercentage(String pcPercentage) {
		this.pcPercentage = pcPercentage;
	}
	public Long getAcValidVotes() {
		return acValidVotes;
	}
	public void setAcValidVotes(Long acValidVotes) {
		this.acValidVotes = acValidVotes;
	}
	public Long getPcValidVotes() {
		return pcValidVotes;
	}
	public void setPcValidVotes(Long pcValidVotes) {
		this.pcValidVotes = pcValidVotes;
	}

	public String getPercentageDifference() {
		return percentageDifference;
	}

	public void setPercentageDifference(String percentageDifference) {
		this.percentageDifference = percentageDifference;
	}
	
}
