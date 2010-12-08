package com.itgrids.partyanalyst.dto;

import java.math.BigDecimal;

public class VotersInfoForMandalVO {
	
	private String mandalId;
	private String mandalName;
	private String totalMaleVoters;
	private String totalFemaleVoters;
	private String totalVoters;
	private String isPartial;
	private String percent;
	private BigDecimal totVoters;
	private Boolean isMandal = false;
	
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
	public String getIsPartial() {
		return isPartial;
	}
	public void setIsPartial(String isPartial) {
		this.isPartial = isPartial;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public BigDecimal getTotVoters() {
		return totVoters;
	}
	public void setTotVoters(BigDecimal totVoters) {
		this.totVoters = totVoters;
	}
	public Boolean getIsMandal() {
		return isMandal;
	}
	public void setIsMandal(Boolean isMandal) {
		this.isMandal = isMandal;
	}
}
