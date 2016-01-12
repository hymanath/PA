package com.itgrids.partyanalyst.dto;

import java.io.Serializable;

public class ActivityReqAttributesVO implements Serializable{
	private Long		activityScopeId;
	private Long		reqAttributeId;
	
	public Long getActivityScopeId() {
		return activityScopeId;
	}
	public void setActivityScopeId(Long activityScopeId) {
		this.activityScopeId = activityScopeId;
	}
	public Long getReqAttributeId() {
		return reqAttributeId;
	}
	public void setReqAttributeId(Long reqAttributeId) {
		this.reqAttributeId = reqAttributeId;
	}
	
	
}
