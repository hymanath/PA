package com.itgrids.partyanalyst.service;

import java.util.List;

/**
 * 
 * @author Narender Akula
 *
 */
public interface IUserCalendarService {
	public Object getUserImpDates(Long userID);
	public Object getUserPlannedEvents(Long userID);
	public void saveUserPlannedEvents(Object userPlannedEvents);
	public void deleteUserPlannedEvents(Object userPlannedEvents);
}
