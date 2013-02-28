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
	private Long ageRangeId;
	private Long totalVotersInARange;
	private Double malePercentage;
	private Double femalePercentage;
	private String reportLevel;
	private String publicationDate;
	private Long constituencyId;
	
	public String getReportLevel() {
		return reportLevel;
	}
	public void setReportLevel(String reportLevel) {
		this.reportLevel = reportLevel;
	}
	public Long getTotalVotersInARange() {
		return totalVotersInARange;
	}
	public void setTotalVotersInARange(Long totalVotersInARange) {
		this.totalVotersInARange = totalVotersInARange;
	}
	public Double getMalePercentage() {
		return malePercentage;
	}
	public void setMalePercentage(Double malePercentage) {
		this.malePercentage = malePercentage;
	}
	public Double getFemalePercentage() {
		return femalePercentage;
	}
	public void setFemalePercentage(Double femalePercentage) {
		this.femalePercentage = femalePercentage;
	}
	public Long getAgeRangeId() {
		return ageRangeId;
	}
	public void setAgeRangeId(Long ageRangeId) {
		this.ageRangeId = ageRangeId;
	}
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
	public String getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	
}
