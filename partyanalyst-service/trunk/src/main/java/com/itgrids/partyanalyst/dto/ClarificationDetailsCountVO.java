package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClarificationDetailsCountVO implements Serializable {
	private Long actionTypeId;
	private String typeName;
	private Long actionTypeStatusId;
	private String status;
	private Long alertCategoryId;
	private String category;
	private Long count = 0L;
	
	private List<ClarificationDetailsCountVO> actionTypeList = null;
	private List<ClarificationDetailsCountVO> statusTypeList = null;
	private List<ClarificationDetailsCountVO> categoryTypeList = null;
	public Long getActionTypeId() {
		return actionTypeId;
	}
	public void setActionTypeId(Long actionTypeId) {
		this.actionTypeId = actionTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Long getActionTypeStatusId() {
		return actionTypeStatusId;
	}
	public void setActionTypeStatusId(Long actionTypeStatusId) {
		this.actionTypeStatusId = actionTypeStatusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getAlertCategoryId() {
		return alertCategoryId;
	}
	public void setAlertCategoryId(Long alertCategoryId) {
		this.alertCategoryId = alertCategoryId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	public List<ClarificationDetailsCountVO> getActionTypeList() {
		if(actionTypeList == null){
			actionTypeList = new ArrayList<ClarificationDetailsCountVO>();
		}
		return actionTypeList;
	}
	
	public List<ClarificationDetailsCountVO> getStatusTypeList() {
		if(statusTypeList == null){
			statusTypeList = new ArrayList<ClarificationDetailsCountVO>();
		}
		return statusTypeList;
	}
	
	public List<ClarificationDetailsCountVO> getCategoryTypeList() {
		if(categoryTypeList == null){
			categoryTypeList = new ArrayList<ClarificationDetailsCountVO>();
		}
		return categoryTypeList;
	}
	
}
