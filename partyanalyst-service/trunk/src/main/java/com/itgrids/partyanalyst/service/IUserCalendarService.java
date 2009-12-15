/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 12, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PartyImportantDatesVO;
import com.itgrids.partyanalyst.dto.UserEventVO;

/**
 * 
 * @author Narender Akula
 *
 */
public interface IUserCalendarService {
	
	public List<PartyImportantDatesVO> getUserImpDates(Long userID, Long partyId);
	public List<UserEventVO> getUserPlannedEvents(Long userID);
	public UserEventVO saveUserPlannedEvents(UserEventVO userPlannedEvents);
	public void deleteUserPlannedEvents(Long userEventID);
	
	
}
