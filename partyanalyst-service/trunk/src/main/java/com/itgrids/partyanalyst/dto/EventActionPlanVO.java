package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;

public class EventActionPlanVO extends ResultStatus implements Comparable<EventActionPlanVO>{

	
	private static final long serialVersionUID = 1L;
	
	private Long eventActionPlanId;
	private String action;
	private Long userEventsId;
	private Date targetDate;
	private List<SelectOptionVO> actionPlanOrganizers;
	private Long id;
	private Long eventId;
	private Long locationValue;
	
	private Long inviteeCount =0l;
	private Long attendeeCount =0l;
	private Long totalAttendeescount = 0l;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public Long getLocationValue() {
		return locationValue;
	}
	public void setLocationValue(Long locationValue) {
		this.locationValue = locationValue;
	}
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
	
	public int compareTo(EventActionPlanVO obj) {
		int result = this.getTargetDate().compareTo(obj.getTargetDate());
		return result;
	}
	public Long getInviteeCount() {
		return inviteeCount;
	}
	public void setInviteeCount(Long inviteeCount) {
		this.inviteeCount = inviteeCount;
	}
	public Long getAttendeeCount() {
		return attendeeCount;
	}
	public void setAttendeeCount(Long attendeeCount) {
		this.attendeeCount = attendeeCount;
	}
	public Long getTotalAttendeescount() {
		return totalAttendeescount;
	}
	public void setTotalAttendeescount(Long totalAttendeescount) {
		this.totalAttendeescount = totalAttendeescount;
	}
	
}
