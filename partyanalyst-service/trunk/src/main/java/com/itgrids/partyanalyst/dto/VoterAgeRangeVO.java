package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class VoterAgeRangeVO implements Serializable {

	private String ageRange;
	private Long maleVoters;
	private Long femaleVoters;
	private Long totalVoters;
	private String percentage;
	private Long reportLevelId;
	private Long reportLevelValue;
	private Long publicationDateId;
	
	public Long getReportLevelId() {
		return reportLevelId;
	}
	public void setReportLevelId(Long reportLevelId) {
		this.reportLevelId = reportLevelId;
	}
	public Long getReportLevelValue() {
		return reportLevelValue;
	}
	public void setReportLevelValue(Long reportLevelValue) {
		this.reportLevelValue = reportLevelValue;
	}
	public Long getPublicationDateId() {
		return publicationDateId;
	}
	public void setPublicationDateId(Long publicationDateId) {
		this.publicationDateId = publicationDateId;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public Long getMaleVoters() {
		return maleVoters;
	}
	public void setMaleVoters(Long maleVoters) {
		this.maleVoters = maleVoters;
	}
	public Long getFemaleVoters() {
		return femaleVoters;
	}
	public void setFemaleVoters(Long femaleVoters) {
		this.femaleVoters = femaleVoters;
	}
	public Long getTotalVoters() {
		return totalVoters;
	}
	public void setTotalVoters(Long totalVoters) {
		this.totalVoters = totalVoters;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
}
