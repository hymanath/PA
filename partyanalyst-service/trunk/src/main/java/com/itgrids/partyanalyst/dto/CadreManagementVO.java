package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CadreManagementVO extends ResultStatus{
	private List<UserEventVO>  UserEvents;
	List<ImportantDatesVO> userImpDates;
	
	public List<UserEventVO> getUserEvents() {
		return UserEvents;
	}

	public void setUserEvents(List<UserEventVO> userEvents) {
		UserEvents = userEvents;
	}

	public List<ImportantDatesVO> getUserImpDates() {
		return userImpDates;
	}

	public void setUserImpDates(List<ImportantDatesVO> userImpDates) {
		this.userImpDates = userImpDates;
	}
	
	
}
