package com.itgrids.partyanalyst.dto;

public class VillageBoothInfoVO {

	private Long villageBoothElectionId;
	private String villageName;
	private String partNo;
	private String villagesCovered;
	private String constituencyName;
	
	public Long getVillageBoothElectionId() {
		return villageBoothElectionId;
	}
	
	public void setVillageBoothElectionId(Long villageBoothElectionId) {
		this.villageBoothElectionId = villageBoothElectionId;
	}
	
	public String getVillageName() {
		return villageName;
	}
	
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	
	public String getPartNo() {
		return partNo;
	}
	
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	
	public String getVillagesCovered() {
		return villagesCovered;
	}
	
	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}
	
	
}
