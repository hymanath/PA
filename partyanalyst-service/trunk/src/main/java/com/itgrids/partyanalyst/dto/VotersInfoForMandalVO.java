package com.itgrids.partyanalyst.dto;

public class VotersInfoForMandalVO {

	private String mandalId;
	private String mandalName;
	private String totalMaleVoters;
	private String totalFemaleVoters;
	private String totalVoters;
	public String getMandalId() {
		return mandalId;
	}
	public void setMandalId(String mandalId) {
		this.mandalId = mandalId;
	}
	public String getMandalName() {
		return mandalName;
	}
	public void setMandalName(String mandalName) {
		this.mandalName = mandalName;
	}
	public String getTotalMaleVoters() {
		return totalMaleVoters;
	}
	public void setTotalMaleVoters(String totalMaleVoters) {
		this.totalMaleVoters = totalMaleVoters;
	}
	public String getTotalFemaleVoters() {
		return totalFemaleVoters;
	}
	public void setTotalFemaleVoters(String totalFemaleVoters) {
		this.totalFemaleVoters = totalFemaleVoters;
	}
	public String getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(String totalVoters) {
		this.totalVoters = totalVoters;
	}
}
