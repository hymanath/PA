package com.itgrids.partyanalyst.dto;

import java.util.List;

public class ConstituencyCadreVO {

	private Long id;
	private String name;
	private Double percentage;
	
	private Long enrollmentYearId;
	private String enrollmentYear;
	private Long toalCadreCount=0l;
	private Long newCaderCount;
	private Long renewalCadreCount;
	private Long maleCount=0l;
	private Long femaleCount=0l;
	private Double malePercentage;
	private Double femalePercentage;
	
	private List<ConstituencyCadreVO>  casteGroupList;
	
	public Long getEnrollmentYearId() {
		return enrollmentYearId;
	}
	public void setEnrollmentYearId(Long enrollmentYearId) {
		this.enrollmentYearId = enrollmentYearId;
	}
	public String getEnrollmentYear() {
		return enrollmentYear;
	}
	public void setEnrollmentYear(String enrollmentYear) {
		this.enrollmentYear = enrollmentYear;
	}
	public Long getToalCadreCount() {
		return toalCadreCount;
	}
	public void setToalCadreCount(Long toalCadreCount) {
		this.toalCadreCount = toalCadreCount;
	}
	public Long getNewCaderCount() {
		return newCaderCount;
	}
	public void setNewCaderCount(Long newCaderCount) {
		this.newCaderCount = newCaderCount;
	}
	public Long getRenewalCadreCount() {
		return renewalCadreCount;
	}
	public void setRenewalCadreCount(Long renewalCadreCount) {
		this.renewalCadreCount = renewalCadreCount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public Long getMaleCount() {
		return maleCount;
	}
	public void setMaleCount(Long maleCount) {
		this.maleCount = maleCount;
	}
	public Long getFemaleCount() {
		return femaleCount;
	}
	public void setFemaleCount(Long femaleCount) {
		this.femaleCount = femaleCount;
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
	public List<ConstituencyCadreVO> getCasteGroupList() {
		return casteGroupList;
	}
	public void setCasteGroupList(List<ConstituencyCadreVO> casteGroupList) {
		this.casteGroupList = casteGroupList;
	}
	
	
	
}
