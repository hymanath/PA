package com.itgrids.partyanalyst.dto;

import java.util.Date;
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
	private EmailDetailsVO emailDetailsVO;
	private Date postedDate;
	private String userImageURL;
	private Long userId;
	private CompleteProblemDetailsVO averageRating;
	private String rating;
	private Long postedProblemsCountByConnectedUsers;
	private String firstName;
	private String lastName;
	
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public EmailDetailsVO getEmailDetailsVO() {
		return emailDetailsVO;
	}
	public void setEmailDetailsVO(EmailDetailsVO emailDetailsVO) {
		this.emailDetailsVO = emailDetailsVO;
	}
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
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public String getUserImageURL() {
		return userImageURL;
	}
	public void setUserImageURL(String userImageURL) {
		this.userImageURL = userImageURL;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public CompleteProblemDetailsVO getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(CompleteProblemDetailsVO averageRating) {
		this.averageRating = averageRating;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Long getPostedProblemsCountByConnectedUsers() {
		return postedProblemsCountByConnectedUsers;
	}
	public void setPostedProblemsCountByConnectedUsers(
			Long postedProblemsCountByConnectedUsers) {
		this.postedProblemsCountByConnectedUsers = postedProblemsCountByConnectedUsers;
	}
	
	
}
