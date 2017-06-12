package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AmsTrackingVO implements Serializable {
	private Long alertId;
	private Long alertStatusId;
	private Long alertCommentId;
	private Long alertUserTypeId;
	private Long userId;
	private Long alertTrackingActionId;
	private String alertTrackingActionType;
	private String date;
	private String designation;
	private List<AmsTrackingVO> attachementsList = new ArrayList<AmsTrackingVO>(0);
	private List<AmsTrackingVO> dueDateList = new ArrayList<AmsTrackingVO>(0);
	private List<AmsTrackingVO> priorityList = new ArrayList<AmsTrackingVO>(0);
	private List<AmsTrackingVO> statusList = new ArrayList<AmsTrackingVO>(0);
	private List<AmsTrackingVO> commentList = new ArrayList<AmsTrackingVO>(0);
	private List<AmsTrackingVO> timeList = new ArrayList<AmsTrackingVO>(0);
	private List<String> strList = new ArrayList<String>(0);
	private String userName;
	private String status;
	private String comment;
	private Long count;
	private Long totalCount=0L;
	private String title;
	private String mobileNO;
	private String deptName;
	private String updatedUserName;
	private String alertTypeStr;
	private String color;
	private String location;
	private String document;
	private String trackingDate;
	private String trackingTime;
	private String dueDate;
	private String severty;
	private String actionType;
	private String position;
	private Long alertFeedbackStatusId;
	private String alertFeedbackStatus;
	private Long alertCallerId;
	private String alertCallerName;
	private String proposalStatus;
	private String amount;
	private String category;
	private Long categoryId;
	private String approvedAmount;
	private String rejinderStatus;
	public AmsTrackingVO(){}
	public AmsTrackingVO(Long alertId,String comment,String date){
		this.alertId=alertId;
		this.comment = comment;
		this.date = date;
	}
	
	public AmsTrackingVO(Long alertId,String comment,String date,String userName,String designation,String department,String location,Long userId){
		this.alertId=alertId;
		this.comment = comment;
		this.date = date;
		this.userName=userName;
		this.designation = designation;
		this.deptName = department;
		this.location = location;
		this.userId = userId;
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
	public String getAlertTrackingActionType() {
		return alertTrackingActionType;
	}
	public void setAlertTrackingActionType(String alertTrackingActionType) {
		this.alertTrackingActionType = alertTrackingActionType;
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
	public List<AmsTrackingVO> getAttachementsList() {
		return attachementsList;
	}
	public void setAttachementsList(List<AmsTrackingVO> attachementsList) {
		this.attachementsList = attachementsList;
	}
	public List<AmsTrackingVO> getDueDateList() {
		return dueDateList;
	}
	public void setDueDateList(List<AmsTrackingVO> dueDateList) {
		this.dueDateList = dueDateList;
	}
	public List<AmsTrackingVO> getPriorityList() {
		return priorityList;
	}
	public void setPriorityList(List<AmsTrackingVO> priorityList) {
		this.priorityList = priorityList;
	}
	public List<AmsTrackingVO> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<AmsTrackingVO> statusList) {
		this.statusList = statusList;
	}
	public List<AmsTrackingVO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<AmsTrackingVO> commentList) {
		this.commentList = commentList;
	}
	public List<AmsTrackingVO> getTimeList() {
		return timeList;
	}
	public void setTimeList(List<AmsTrackingVO> timeList) {
		this.timeList = timeList;
	}
	public List<String> getStrList() {
		return strList;
	}
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMobileNO() {
		return mobileNO;
	}
	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getUpdatedUserName() {
		return updatedUserName;
	}
	public void setUpdatedUserName(String updatedUserName) {
		this.updatedUserName = updatedUserName;
	}
	public String getAlertTypeStr() {
		return alertTypeStr;
	}
	public void setAlertTypeStr(String alertTypeStr) {
		this.alertTypeStr = alertTypeStr;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getTrackingDate() {
		return trackingDate;
	}
	public void setTrackingDate(String trackingDate) {
		this.trackingDate = trackingDate;
	}
	public String getTrackingTime() {
		return trackingTime;
	}
	public void setTrackingTime(String trackingTime) {
		this.trackingTime = trackingTime;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getSeverty() {
		return severty;
	}
	public void setSeverty(String severty) {
		this.severty = severty;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Long getAlertFeedbackStatusId() {
		return alertFeedbackStatusId;
	}
	public void setAlertFeedbackStatusId(Long alertFeedbackStatusId) {
		this.alertFeedbackStatusId = alertFeedbackStatusId;
	}
	public String getAlertFeedbackStatus() {
		return alertFeedbackStatus;
	}
	public void setAlertFeedbackStatus(String alertFeedbackStatus) {
		this.alertFeedbackStatus = alertFeedbackStatus;
	}
	public Long getAlertCallerId() {
		return alertCallerId;
	}
	public void setAlertCallerId(Long alertCallerId) {
		this.alertCallerId = alertCallerId;
	}
	public String getAlertCallerName() {
		return alertCallerName;
	}
	public void setAlertCallerName(String alertCallerName) {
		this.alertCallerName = alertCallerName;
	}
	public String getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(String proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(String approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public String getRejinderStatus() {
		return rejinderStatus;
	}
	public void setRejinderStatus(String rejinderStatus) {
		this.rejinderStatus = rejinderStatus;
	}
	
}
