package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.Date;

public class DashboardCommentVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dashBoardCommentId;
	private Long dashboardComponentId;
	private  String comment;
	private Long userId;
	private Date insertedTime;
	private String isDeleted;
	
	public Long getDashBoardCommentId() {
		return dashBoardCommentId;
	}
	public void setDashBoardCommentId(Long dashBoardCommentId) {
		this.dashBoardCommentId = dashBoardCommentId;
	}
	public Long getDashboardComponentId() {
		return dashboardComponentId;
	}
	public void setDashboardComponentId(Long dashboardComponentId) {
		this.dashboardComponentId = dashboardComponentId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	

}
