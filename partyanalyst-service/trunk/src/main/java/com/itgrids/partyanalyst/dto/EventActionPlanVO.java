package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;

public class EventActionPlanVO extends ResultStatus {

	
	private static final long serialVersionUID = 1L;
	
	private Long eventActionPlanId;
	private String action;
	private Long userEventsId;
	private Date targetDate;
	private List<SelectOptionVO> actionPlanOrganizers;
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
	public List<SelectOptionVO> getActionPlanOrganizers() {
		return actionPlanOrganizers;
	}
	public void setActionPlanOrganizers(List<SelectOptionVO> actionPlanOrganizers) {
		this.actionPlanOrganizers = actionPlanOrganizers;
	}
	
	
}
