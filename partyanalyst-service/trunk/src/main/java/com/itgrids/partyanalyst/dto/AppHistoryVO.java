package com.itgrids.partyanalyst.dto;

public class AppHistoryVO {
	private Long id;
	private String purpose;
	private String createdOn;
	private String preferredDate;
	private String ConfirmedDate;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getPreferredDate() {
		return preferredDate;
	}
	public void setPreferredDate(String preferredDate) {
		this.preferredDate = preferredDate;
	}
	public String getConfirmedDate() {
		return ConfirmedDate;
	}
	public void setConfirmedDate(String confirmedDate) {
		ConfirmedDate = confirmedDate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
