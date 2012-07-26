package com.itgrids.partyanalyst.dto;

import java.util.List;

public class UserCommentsInfoVO extends ResultStatus{

	private Double commentScore = 0.0d;
	private String comment;
	private String commentCategory;
	private Long commentCategoryId;
	private List<CandidateCommentsVO> candidateComments;
	private Long totalResultsCount;
	private String firstName;
	private String lastName;
	private Long userId;
	private String date;
	private String profileImg;
	
	public Long getTotalResultsCount() {
		return totalResultsCount;
	}

	public void setTotalResultsCount(Long totalResultsCount) {
		this.totalResultsCount = totalResultsCount;
	}

	public List<CandidateCommentsVO> getCandidateComments() {
		return candidateComments;
	}

	public void setCandidateComments(List<CandidateCommentsVO> candidateComments) {
		this.candidateComments = candidateComments;
	}

	

	public Double getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(Double commentScore) {
		this.commentScore = commentScore;
	}

	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Long getCommentCategoryId() {
		return commentCategoryId;
	}
	
	public void setCommentCategoryId(Long commentCategoryId) {
		this.commentCategoryId = commentCategoryId;
	}

	public String getCommentCategory() {
		return commentCategory;
	}

	public void setCommentCategory(String commentCategory) {
		this.commentCategory = commentCategory;
	}

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
		
	
}
