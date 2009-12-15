package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.UserEventVO;

/**
 * 
 * @author Narender Akula
 *
 */
public interface IUserCalendarService {
	public Object getUserImpDates(Long userID);
	public List<UserEventVO> getUserPlannedEvents(Long userID);
	public UserEventVO saveUserPlannedEvents(UserEventVO userPlannedEvents);
	public void deleteUserPlannedEvents(Long userEventID);
}
