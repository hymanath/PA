package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CadreManagementVO extends ResultStatus{
	private List<UserEventVO>  UserEvents;
	List<PartyImportantDatesVO> userImpDates;
	
	public List<UserEventVO> getUserEvents() {
		return UserEvents;
	}

	public void setUserEvents(List<UserEventVO> userEvents) {
		UserEvents = userEvents;
	}

	public List<PartyImportantDatesVO> getUserImpDates() {
		return userImpDates;
	}

	public void setUserImpDates(List<PartyImportantDatesVO> userImpDates) {
		this.userImpDates = userImpDates;
	}
	
	
}
