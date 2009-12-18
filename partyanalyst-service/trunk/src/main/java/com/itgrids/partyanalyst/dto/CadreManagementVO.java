package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CadreManagementVO extends ResultStatus{
	private List<UserEventVO>  userEvents;
	private List<ImportantDatesVO> userImpDates;
	
	

	public List<UserEventVO> getUserEvents() {
		return userEvents;
	}

	public void setUserEvents(List<UserEventVO> userEvents) {
		this.userEvents = userEvents;
	}

	public List<ImportantDatesVO> getUserImpDates() {
		return userImpDates;
	}

	public void setUserImpDates(List<ImportantDatesVO> userImpDates) {
		this.userImpDates = userImpDates;
	}
	
	
}
