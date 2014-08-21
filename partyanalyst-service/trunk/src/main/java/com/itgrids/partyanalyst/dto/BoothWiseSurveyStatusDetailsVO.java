package com.itgrids.partyanalyst.dto;

public class BoothWiseSurveyStatusDetailsVO {
	
	private Long boothId;
	private String partNo;
	private String dcCompleted = "N";
	private String dvCompleted = "N";
	private String qcCompleted = "N";
	private String wmDcCompleted = "N";
	private String wmDvCompleted = "N";
	private Long totalVoters;
	
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
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
	public String getDcCompleted() {
		return dcCompleted;
	}
	public void setDcCompleted(String dcCompleted) {
		this.dcCompleted = dcCompleted;
	}
	public String getDvCompleted() {
		return dvCompleted;
	}
	public void setDvCompleted(String dvCompleted) {
		this.dvCompleted = dvCompleted;
	}
	public String getQcCompleted() {
		return qcCompleted;
	}
	public void setQcCompleted(String qcCompleted) {
		this.qcCompleted = qcCompleted;
	}
	public String getWmDcCompleted() {
		return wmDcCompleted;
	}
	public void setWmDcCompleted(String wmDcCompleted) {
		this.wmDcCompleted = wmDcCompleted;
	}
	public String getWmDvCompleted() {
		return wmDvCompleted;
	}
	public void setWmDvCompleted(String wmDvCompleted) {
		this.wmDvCompleted = wmDvCompleted;
	}

}
