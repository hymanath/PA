package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ProblemSearchVO extends ResultStatus implements Serializable{

	private static final long serialVersionUID = 8969262022600852824L;
	
	private Boolean ScopeAll;
	private Boolean statusAll;
	private Boolean typeAll;
	private Boolean usersAll;
	private Long scopeId;
	private Long locationValue;
	private Long statusId;
	private Long typeId;
	private Long userId;
	public Boolean getScopeAll() {
		return ScopeAll;
	}
	public void setScopeAll(Boolean scopeAll) {
		ScopeAll = scopeAll;
	}
	public Boolean getStatusAll() {
		return statusAll;
	}
	public void setStatusAll(Boolean statusAll) {
		this.statusAll = statusAll;
	}
	public Boolean getTypeAll() {
		return typeAll;
	}
	public void setTypeAll(Boolean typeAll) {
		this.typeAll = typeAll;
	}
	public Boolean getUsersAll() {
		return usersAll;
	}
	public void setUsersAll(Boolean usersAll) {
		this.usersAll = usersAll;
	}
	public Long getScopeId() {
		return scopeId;
	}
	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

}
