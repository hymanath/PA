package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.List;

public class UserEventVO extends ResultStatus implements Comparable<UserEventVO>{
 
	private static final long serialVersionUID = 1L;
	
	private Long userEventsId;
	private Long userID;
	private String title;
	private String description;
	private String locationType;
	private Long locationId;
	private Date startDate;
	private Date endDate;
	private List <SelectOptionVO> organizers;
	private List<EventActionPlanVO> actionPlans;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getUserEventsId() {
		return userEventsId;
	}
	public void setUserEventsId(Long userEventsId) {
		this.userEventsId = userEventsId;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<SelectOptionVO> getOrganizers() {
		return organizers;
	}
	public void setOrganizers(List<SelectOptionVO> organizers) {
		this.organizers = organizers;
	}
	public List<EventActionPlanVO> getActionPlans() {
		return actionPlans;
	}
	public void setActionPlans(List<EventActionPlanVO> actionPlans) {
		this.actionPlans = actionPlans;
	}
	public int compareTo(UserEventVO obj) {
		int result = obj.getStartDate().compareTo(this.getStartDate());
		return result;
	}
	
	
	
	
	
}
