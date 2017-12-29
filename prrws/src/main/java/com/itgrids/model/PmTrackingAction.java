package com.itgrids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pm_tracking_action")
public class PmTrackingAction {
	//pm_tracking_action_id
	//action_name
	
	private Long pmTrackingActionId;
	private String actionName;
	
	@Id
	@Column(name="pm_tracking_action_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getPmTrackingActionId() {
		return pmTrackingActionId;
	}
	public void setPmTrackingActionId(Long pmTrackingActionId) {
		this.pmTrackingActionId = pmTrackingActionId;
	}
	@Column(name="action_name")
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
}
