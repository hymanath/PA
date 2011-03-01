package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class UserFeedbackVO extends ResultStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long commentId;
	private Long commentType;
	private Long taskName;
	private String kindOfComment;
	private String task;
	private String comment;
	private Long userId;
	private String userType;
	private String userName;
	private String responseCategory;
	private Date posteddate;
	private String status;
	
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public Long getCommentType() {
		return commentType;
	}
	public void setCommentType(Long feedBackTask) {
		this.commentType = feedBackTask;
	}
	public Long getTaskName() {
		return taskName;
	}
	public void setTaskName(Long taskName) {
		this.taskName = taskName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getKindOfComment() {
		return kindOfComment;
	}
	public void setKindOfComment(String kindOfComment) {
		this.kindOfComment = kindOfComment;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getResponseCategory() {
		return responseCategory;
	}
	public void setResponseCategory(String responseCategory) {
		this.responseCategory = responseCategory;
	}
	public Date getPosteddate() {
		return posteddate;
	}
	public void setPosteddate(Date posteddate) {
		this.posteddate = posteddate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserType() {
		return userType;
	}
	
		
}