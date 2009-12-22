package com.itgrids.partyanalyst.dto;

import java.util.List;
import java.util.Map;

public class CadreManagementVO extends ResultStatus{
	private List<UserEventVO>  userEvents;
	private List<ImportantDatesVO> userImpDates;
	private Map<String, Long> cadresByCadreLevel;
	
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

	public Map<String, Long> getCadresByCadreLevel() {
		return cadresByCadreLevel;
	}

	public void setCadresByCadreLevel(Map<String, Long> cadresByCadreLevel) {
		this.cadresByCadreLevel = cadresByCadreLevel;
	}
	
	
}
