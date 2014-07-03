package com.itgrids.partyanalyst.dto;

public class UserBoothDetailsVO {
	
	private Long boothId;
	private String partNo;
	
	private String panchayatName;
	private String localBodyName;
	
	private boolean userHas;
	
	
	public boolean isUserHas() {
		return userHas;
	}
	public void setUserHas(boolean userHas) {
		this.userHas = userHas;
	}
	public String getPanchayatName() {
		return panchayatName;
	}
	public void setPanchayatName(String panchayatName) {
		this.panchayatName = panchayatName;
	}
	public String getLocalBodyName() {
		return localBodyName;
	}
	public void setLocalBodyName(String localBodyName) {
		this.localBodyName = localBodyName;
	}
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}

}
