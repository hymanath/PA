package com.itgrids.dto;

import java.util.List;

public class InnovationSocietyDtlsVO {

	private Long startupsCount;
	private Long schoolsCount;
	private Long incubatorsCount;
	private Long collegesCount;
	private Long mentorsCount;
	private String batchName;
	private String duration;
	private Long days;
	private Long companiesRegistered;
	private Long jobsCreated;
	
	private List<InnovationSocietyDtlsVO> subList;
	
	public Long getStartupsCount() {
		return startupsCount;
	}
	public void setStartupsCount(Long startupsCount) {
		this.startupsCount = startupsCount;
	}
	public Long getSchoolsCount() {
		return schoolsCount;
	}
	public void setSchoolsCount(Long schoolsCount) {
		this.schoolsCount = schoolsCount;
	}
	public Long getIncubatorsCount() {
		return incubatorsCount;
	}
	public void setIncubatorsCount(Long incubatorsCount) {
		this.incubatorsCount = incubatorsCount;
	}
	public Long getCollegesCount() {
		return collegesCount;
	}
	public void setCollegesCount(Long collegesCount) {
		this.collegesCount = collegesCount;
	}
	public Long getMentorsCount() {
		return mentorsCount;
	}
	public void setMentorsCount(Long mentorsCount) {
		this.mentorsCount = mentorsCount;
	}
	public List<InnovationSocietyDtlsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<InnovationSocietyDtlsVO> subList) {
		this.subList = subList;
	}
	public String getBatchName() {
		return batchName;
	}
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Long getDays() {
		return days;
	}
	public void setDays(Long days) {
		this.days = days;
	}
	public Long getCompaniesRegistered() {
		return companiesRegistered;
	}
	public void setCompaniesRegistered(Long companiesRegistered) {
		this.companiesRegistered = companiesRegistered;
	}
	public Long getJobsCreated() {
		return jobsCreated;
	}
	public void setJobsCreated(Long jobsCreated) {
		this.jobsCreated = jobsCreated;
	}
    
	
	
}
