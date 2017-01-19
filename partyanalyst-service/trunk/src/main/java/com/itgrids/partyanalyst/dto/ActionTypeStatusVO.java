package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ActionTypeStatusVO implements Serializable {
	private Long actionTypeId;
	private String typeName;
	private Long actionTypeStatusId;
	private String status;
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
}
