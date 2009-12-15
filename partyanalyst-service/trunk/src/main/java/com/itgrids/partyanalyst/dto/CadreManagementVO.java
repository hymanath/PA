package com.itgrids.partyanalyst.dto;

import java.util.List;

public class CadreManagementVO extends ResultStatus{
	private List<UserEventVO>  UserEvents;

	public List<UserEventVO> getUserEvents() {
		return UserEvents;
	}

	public void setUserEvents(List<UserEventVO> userEvents) {
		UserEvents = userEvents;
	}
	
	
}
