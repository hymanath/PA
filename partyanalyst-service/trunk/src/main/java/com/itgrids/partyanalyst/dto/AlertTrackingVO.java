package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

public class AlertTrackingVO {
	
	private Long alertId;
	private Long alertStatusId;
	private Long alertCommentId;
	private Long alertUserTypeId;
	private Long userId;
	private Long alertTrackingActionId;
	private String date;
	private String designation;
	private List<AlertTrackingVO> attachementsList = new ArrayList<AlertTrackingVO>(0);
	private List<AlertTrackingVO> dueDateList = new ArrayList<AlertTrackingVO>(0);
	private List<AlertTrackingVO> priorityList = new ArrayList<AlertTrackingVO>(0);
	private List<AlertTrackingVO> statusList = new ArrayList<AlertTrackingVO>(0);
	private List<AlertTrackingVO> commentList = new ArrayList<AlertTrackingVO>(0);
	private List<AlertTrackingVO> timeList = new ArrayList<AlertTrackingVO>(0);
	private List<String> strList = new ArrayList<String>(0);
	private String userName;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getStrList() {
		return strList;
	}
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
	public List<AlertTrackingVO> getTimeList() {
		return timeList;
	}
	public void setTimeList(List<AlertTrackingVO> timeList) {
		this.timeList = timeList;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
	public List<AlertTrackingVO> getAttachementsList() {
		return attachementsList;
	}
	public void setAttachementsList(List<AlertTrackingVO> attachementsList) {
		this.attachementsList = attachementsList;
	}
	public List<AlertTrackingVO> getDueDateList() {
		return dueDateList;
	}
	public void setDueDateList(List<AlertTrackingVO> dueDateList) {
		this.dueDateList = dueDateList;
	}
	public List<AlertTrackingVO> getPriorityList() {
		return priorityList;
	}
	public void setPriorityList(List<AlertTrackingVO> priorityList) {
		this.priorityList = priorityList;
	}
	public List<AlertTrackingVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<AlertTrackingVO> statusList) {
		this.statusList = statusList;
	}
	public List<AlertTrackingVO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<AlertTrackingVO> commentList) {
		this.commentList = commentList;
	}
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	public Long getAlertStatusId() {
		return alertStatusId;
	}
	public void setAlertStatusId(Long alertStatusId) {
		this.alertStatusId = alertStatusId;
	}
	public Long getAlertCommentId() {
		return alertCommentId;
	}
	public void setAlertCommentId(Long alertCommentId) {
		this.alertCommentId = alertCommentId;
	}
	public Long getAlertUserTypeId() {
		return alertUserTypeId;
	}
	public void setAlertUserTypeId(Long alertUserTypeId) {
		this.alertUserTypeId = alertUserTypeId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAlertTrackingActionId() {
		return alertTrackingActionId;
	}
	public void setAlertTrackingActionId(Long alertTrackingActionId) {
		this.alertTrackingActionId = alertTrackingActionId;
	}

}
