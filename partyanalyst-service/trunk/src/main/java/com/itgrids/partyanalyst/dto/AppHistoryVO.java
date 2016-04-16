package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class AppHistoryVO {
	private Long id;
	private String purpose;
	private String createdOn;
	private String preferredDate;
	private String ConfirmedDate;
	private String status;
	private String uniqueCode;
	private Long statusId;
	private String comment;
	private String User;
	
	
	private List<AppHistoryVO> commentlist = new ArrayList<AppHistoryVO>();
	
	
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getUniqueCode() {
		return uniqueCode;
	}
	public void setUniqueCode(String uniqueCode) {
		this.uniqueCode = uniqueCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getPreferredDate() {
		return preferredDate;
	}
	public void setPreferredDate(String preferredDate) {
		this.preferredDate = preferredDate;
	}
	public String getConfirmedDate() {
		return ConfirmedDate;
	}
	public void setConfirmedDate(String confirmedDate) {
		ConfirmedDate = confirmedDate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<AppHistoryVO> getCommentlist() {
		return commentlist;
	}
	public void setCommentlist(List<AppHistoryVO> commentlist) {
		this.commentlist = commentlist;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	
	
}
