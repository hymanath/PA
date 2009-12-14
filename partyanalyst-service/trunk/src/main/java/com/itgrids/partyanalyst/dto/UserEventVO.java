package com.itgrids.partyanalyst.dto;

import java.util.Date;
import java.util.Set;

public class UserEventVO extends ResultStatus {
 
	private static final long serialVersionUID = 1L;
	
	private Long userEventsId;
	private Long userID;
	private String description;
	private String locationType;
	private Long locationId;
	private Date startDate;
	private Date endDate;
	private Set <CadreInfo> organizers;
	private Set<EventActionPlanVO> actionPlans;
	
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
	public Set<CadreInfo> getOrganizers() {
		return organizers;
	}
	public void setOrganizers(Set<CadreInfo> organizers) {
		this.organizers = organizers;
	}
	public Set<EventActionPlanVO> getActionPlans() {
		return actionPlans;
	}
	public void setActionPlans(Set<EventActionPlanVO> actionPlans) {
		this.actionPlans = actionPlans;
	}
	
	
	
	
	
}
