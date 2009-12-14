package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.Set;

public class EventActionPlanVO extends ResultStatus {

	
	private static final long serialVersionUID = 1L;
	
	private Long eventActionPlanId;
	private String action;
	private Long userEventsId;
	private Date targetDate;
	private Set<CadreInfo> actionPlanOrganizers;
	public Long getEventActionPlanId() {
		return eventActionPlanId;
	}
	public void setEventActionPlanId(Long eventActionPlanId) {
		this.eventActionPlanId = eventActionPlanId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Long getUserEventsId() {
		return userEventsId;
	}
	public void setUserEventsId(Long userEventsId) {
		this.userEventsId = userEventsId;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	public Set<CadreInfo> getActionPlanOrganizers() {
		return actionPlanOrganizers;
	}
	public void setActionPlanOrganizers(Set<CadreInfo> actionPlanOrganizers) {
		this.actionPlanOrganizers = actionPlanOrganizers;
	}
	
	
}
