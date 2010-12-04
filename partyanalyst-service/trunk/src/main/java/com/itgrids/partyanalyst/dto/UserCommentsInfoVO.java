package com.itgrids.partyanalyst.dto;

public class UserCommentsInfoVO extends ResultStatus{

	private Double commentScore = 0.0d;
	private String comment;
	private String commentCategory;
	private Long commentCategoryId;
	
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
		
	
}
