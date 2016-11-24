package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

import com.itgrids.partyanalyst.model.BaseModel;

public class ActionableVO extends BaseModel implements Serializable{

	private Long id;
	private String name;
	private String desc;
	private Long regionScopeId;
	private Long regionScopeValue;	
	private String sourceTypeId;//manual,printMedia,electronicMedia
	
	private Long statusId;	
	private Long alertCategory;
	private Long alertType;
	private Long userId;
	
	private String type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Long getRegionScopeId() {
		return regionScopeId;
	}
	public void setRegionScopeId(Long regionScopeId) {
		this.regionScopeId = regionScopeId;
	}
	public Long getRegionScopeValue() {
		return regionScopeValue;
	}
	public void setRegionScopeValue(Long regionScopeValue) {
		this.regionScopeValue = regionScopeValue;
	}
	public String getSourceTypeId() {
		return sourceTypeId;
	}
	public void setSourceTypeId(String sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getAlertCategory() {
		return alertCategory;
	}
	public void setAlertCategory(Long alertCategory) {
		this.alertCategory = alertCategory;
	}
	public Long getAlertType() {
		return alertType;
	}
	public void setAlertType(Long alertType) {
		this.alertType = alertType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
}
