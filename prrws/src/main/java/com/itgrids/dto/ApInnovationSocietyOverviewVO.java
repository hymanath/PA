package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApInnovationSocietyOverviewVO {

	private Long Startups = 0L;
	private Long Schools;
	private Long Colleges = 0L;
	private Long Incubators = 0L;
	private Long Mentors;
	private Long bootCampCount = 0L;
	private Long eventsCount = 0L;
	private Long activitiesCount = 0L;
	private String incubatorName;
	private String location;
	private String name;
	private Long id;
	private Long count = 0L;
	private String year;
	private String webSite;
	private String startUps;
	private String fromDate;
	private String toDtae;
	private Long totalPartiCount = 0L;
	private Long compReg = 0L;
	private String permentJobs ;
	private String temporaryJobs;
	private String internJobs;
	private String totalJobs;
	private String remarks;
	private String state;
	private String sector;
	private String companyName;
	private String innovation;
	private String studentName;
	private String college;
	private String course;
	private String branch;
	private String eventName;
	private String eventType;
	private String venue;
	private String totalParticipaints;
	private String conductedBy;
	
	
	private List<ApInnovationSocietyOverviewVO> subList = new ArrayList<ApInnovationSocietyOverviewVO>(0);
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
	public Long getBootCampCount() {
		return bootCampCount;
	}
	public void setBootCampCount(Long bootCampCount) {
		this.bootCampCount = bootCampCount;
	}
	public Long getEventsCount() {
		return eventsCount;
	}
	public void setEventsCount(Long eventsCount) {
		this.eventsCount = eventsCount;
	}
	public Long getActivitiesCount() {
		return activitiesCount;
	}
	public void setActivitiesCount(Long activitiesCount) {
		this.activitiesCount = activitiesCount;
	}
	public String getIncubatorName() {
		return incubatorName;
	}
	public void setIncubatorName(String incubatorName) {
		this.incubatorName = incubatorName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<ApInnovationSocietyOverviewVO> getSubList() {
		return subList;
	}
	public void setSubList(List<ApInnovationSocietyOverviewVO> subList) {
		this.subList = subList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getStartUps() {
		return startUps;
	}
	public void setStartUps(String startUps) {
		this.startUps = startUps;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDtae() {
		return toDtae;
	}
	public void setToDtae(String toDtae) {
		this.toDtae = toDtae;
	}
	public Long getTotalPartiCount() {
		return totalPartiCount;
	}
	public void setTotalPartiCount(Long totalPartiCount) {
		this.totalPartiCount = totalPartiCount;
	}
	public Long getCompReg() {
		return compReg;
	}
	public void setCompReg(Long compReg) {
		this.compReg = compReg;
	}
	public String getPermentJobs() {
		return permentJobs;
	}
	public void setPermentJobs(String permentJobs) {
		this.permentJobs = permentJobs;
	}
	public String getTemporaryJobs() {
		return temporaryJobs;
	}
	public void setTemporaryJobs(String temporaryJobs) {
		this.temporaryJobs = temporaryJobs;
	}
	public String getInternJobs() {
		return internJobs;
	}
	public void setInternJobs(String internJobs) {
		this.internJobs = internJobs;
	}
	public String getTotalJobs() {
		return totalJobs;
	}
	public void setTotalJobs(String totalJobs) {
		this.totalJobs = totalJobs;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getInnovation() {
		return innovation;
	}
	public void setInnovation(String innovation) {
		this.innovation = innovation;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getTotalParticipaints() {
		return totalParticipaints;
	}
	public void setTotalParticipaints(String totalParticipaints) {
		this.totalParticipaints = totalParticipaints;
	}
	public String getConductedBy() {
		return conductedBy;
	}
	public void setConductedBy(String conductedBy) {
		this.conductedBy = conductedBy;
	}
	
}
