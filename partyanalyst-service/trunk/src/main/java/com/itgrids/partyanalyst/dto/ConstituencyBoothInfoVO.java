package com.itgrids.partyanalyst.dto;

public class ConstituencyBoothInfoVO {

	private Long boothConstiElecId;
	private String partNo;
	private String constituencyName;
	private String villagesCovered;
	
	public ConstituencyBoothInfoVO(){
		
	}
	
	public ConstituencyBoothInfoVO(Long boothConstiElecId, String partNo,
			String constituencyName, String villagesCovered) {
		this.boothConstiElecId = boothConstiElecId;
		this.partNo = partNo;
		this.constituencyName = constituencyName;
		this.villagesCovered = villagesCovered;
	}

	public Long getBoothConstiElecId() {
		return boothConstiElecId;
	}
	
	public void setBoothConstiElecId(Long boothConstiElecId) {
		this.boothConstiElecId = boothConstiElecId;
	}
	
	public String getPartNo() {
		return partNo;
	}
	
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	
	public String getConstituencyName() {
		return constituencyName;
	}
	
	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getVillagesCovered() {
		return villagesCovered;
	}

	public void setVillagesCovered(String villagesCovered) {
		this.villagesCovered = villagesCovered;
	}
	
	
	
}
