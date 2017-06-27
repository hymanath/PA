package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AmsAppLoginVO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String userName;
	private String status;
	private String password;
	private String isOtp;
	private Long alertId;
	private String task;
	private Long subTaskId;
	private Long parentDeptId;
	private Long departmentId;
	private Long levelId;
	private Long levelValue;
	private Long designationId;
	private Long statusId;
	private String comment;
	private Long proposalCategoryId;
	private String proposalAmount;
	private Long rejoinderActionId;
	private List<String> images = new ArrayList<String>(0);
	
	
	public AmsAppLoginVO(){}
	public AmsAppLoginVO(Long userId, String userName){
		this.userId = userId;
		this.userName=userName;
	}
	public AmsAppLoginVO(Long userId, String userName,String status){
		this.userId = userId;
		this.userName=userName;
		this.status = status;
	}
	public AmsAppLoginVO(String status){
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIsOtp() {
		return isOtp;
	}
	public void setIsOtp(String isOtp) {
		this.isOtp = isOtp;
	}
	public Long getAlertId() {
		return alertId;
	}
	public void setAlertId(Long alertId) {
		this.alertId = alertId;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public Long getSubTaskId() {
		return subTaskId;
	}
	public void setSubTaskId(Long subTaskId) {
		this.subTaskId = subTaskId;
	}
	public Long getParentDeptId() {
		return parentDeptId;
	}
	public void setParentDeptId(Long parentDeptId) {
		this.parentDeptId = parentDeptId;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public Long getLevelValue() {
		return levelValue;
	}
	public void setLevelValue(Long levelValue) {
		this.levelValue = levelValue;
	}
	public Long getDesignationId() {
		return designationId;
	}
	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getProposalCategoryId() {
		return proposalCategoryId;
	}
	public void setProposalCategoryId(Long proposalCategoryId) {
		this.proposalCategoryId = proposalCategoryId;
	}
	public String getProposalAmount() {
		return proposalAmount;
	}
	public void setProposalAmount(String proposalAmount) {
		this.proposalAmount = proposalAmount;
	}
	public Long getRejoinderActionId() {
		return rejoinderActionId;
	}
	public void setRejoinderActionId(Long rejoinderActionId) {
		this.rejoinderActionId = rejoinderActionId;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
}
