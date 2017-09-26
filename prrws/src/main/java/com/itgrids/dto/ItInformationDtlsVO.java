package com.itgrids.dto;

public class ItInformationDtlsVO {

	private String nameOfLead;
	private String status;
	private String sector;
	private String lineOfActivity;
	private String districtName;
	private Long committedInvestmentCount;
	private Long committedEmploymentCount;
	
	public String getNameOfLead() {
		return nameOfLead;
	}
	public void setNameOfLead(String nameOfLead) {
		this.nameOfLead = nameOfLead;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getLineOfActivity() {
		return lineOfActivity;
	}
	public void setLineOfActivity(String lineOfActivity) {
		this.lineOfActivity = lineOfActivity;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Long getCommittedInvestmentCount() {
		return committedInvestmentCount;
	}
	public void setCommittedInvestmentCount(Long committedInvestmentCount) {
		this.committedInvestmentCount = committedInvestmentCount;
	}
	public Long getCommittedEmploymentCount() {
		return committedEmploymentCount;
	}
	public void setCommittedEmploymentCount(Long committedEmploymentCount) {
		this.committedEmploymentCount = committedEmploymentCount;
	}
	
	
}
