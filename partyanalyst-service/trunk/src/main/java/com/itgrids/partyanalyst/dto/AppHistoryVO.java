package com.itgrids.partyanalyst.dto;

public class AppHistoryVO {
	private Long id;
	private String purpose;
	private Long createdOn;
	private Long preferredDate;
	private Long ConfirmedDate;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Long getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}
	public Long getPreferredDate() {
		return preferredDate;
	}
	public void setPreferredDate(Long preferredDate) {
		this.preferredDate = preferredDate;
	}
	public Long getConfirmedDate() {
		return ConfirmedDate;
	}
	public void setConfirmedDate(Long confirmedDate) {
		ConfirmedDate = confirmedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
