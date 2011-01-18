package com.itgrids.partyanalyst.dto;

import java.util.List;


public class ProblemDetailsVO {

	private Long problemID;
	private String definition;
	private String description;
	private String identifiedDate;
	private String location;	
	private String source;
	private String year;
	private String existingFrom;
	private String locationType;
	private String isApproved;
	private Long approvedProblemsCount;
	private Long rejectedProblemsCount;
	private Long notConsideredProblemsCount;
	private Long totalPostedProblemsCount;
	private Long postedProblemsCountByOtherUsers;
	private Long postedProblemsCountByLoggedInUsers;
	private Long problemHistoryId;
	
	public Long getApprovedProblemsCount() {
		return approvedProblemsCount;
	}
	public void setApprovedProblemsCount(Long approvedProblemsCount) {
		this.approvedProblemsCount = approvedProblemsCount;
	}
	public Long getRejectedProblemsCount() {
		return rejectedProblemsCount;
	}
	public void setRejectedProblemsCount(Long rejectedProblemsCount) {
		this.rejectedProblemsCount = rejectedProblemsCount;
	}
	public Long getNotConsideredProblemsCount() {
		return notConsideredProblemsCount;
	}
	public void setNotConsideredProblemsCount(Long notConsideredProblemsCount) {
		this.notConsideredProblemsCount = notConsideredProblemsCount;
	}
	public Long getTotalPostedProblemsCount() {
		return totalPostedProblemsCount;
	}
	public void setTotalPostedProblemsCount(Long totalPostedProblemsCount) {
		this.totalPostedProblemsCount = totalPostedProblemsCount;
	}
	public Long getPostedProblemsCountByOtherUsers() {
		return postedProblemsCountByOtherUsers;
	}
	public void setPostedProblemsCountByOtherUsers(
			Long postedProblemsCountByOtherUsers) {
		this.postedProblemsCountByOtherUsers = postedProblemsCountByOtherUsers;
	}
	public Long getPostedProblemsCountByLoggedInUsers() {
		return postedProblemsCountByLoggedInUsers;
	}
	public void setPostedProblemsCountByLoggedInUsers(
			Long postedProblemsCountByLoggedInUsers) {
		this.postedProblemsCountByLoggedInUsers = postedProblemsCountByLoggedInUsers;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getExistingFrom() {
		return existingFrom;
	}
	public void setExistingFrom(String existingFrom) {
		this.existingFrom = existingFrom;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Long getProblemID() {
		return problemID;
	}
	public void setProblemID(Long problemID) {
		this.problemID = problemID;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIdentifiedDate() {
		return identifiedDate;
	}
	public void setIdentifiedDate(String identifiedDate) {
		this.identifiedDate = identifiedDate;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setProblemHistoryId(Long problemHistoryId) {
		this.problemHistoryId = problemHistoryId;
	}
	public Long getProblemHistoryId() {
		return problemHistoryId;
	}
	
}
