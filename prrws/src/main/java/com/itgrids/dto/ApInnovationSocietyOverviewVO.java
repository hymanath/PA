package com.itgrids.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApInnovationSocietyOverviewVO {

	private Long Startups;
	private Long Schools;
	private Long Colleges;
	private Long Incubators;
	private Long Mentors;
	
	public Long getStartups() {
		return Startups;
	}
	public void setStartups(Long startups) {
		Startups = startups;
	}
	public Long getSchools() {
		return Schools;
	}
	public void setSchools(Long schools) {
		Schools = schools;
	}
	public Long getColleges() {
		return Colleges;
	}
	public void setColleges(Long colleges) {
		Colleges = colleges;
	}
	public Long getIncubators() {
		return Incubators;
	}
	public void setIncubators(Long incubators) {
		Incubators = incubators;
	}
	public Long getMentors() {
		return Mentors;
	}
	public void setMentors(Long mentors) {
		Mentors = mentors;
	}
	
}
