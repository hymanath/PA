/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 12, 2009
 */
package com.itgrids.partyanalyst.service;

import java.util.Calendar;
import java.util.List;

import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.ImportantDatesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserEventVO;

/**
 * 
 * @author Narender Akula
 *
 */
public interface IUserCalendarService {
	
	public List<ImportantDatesVO> getUserImpDates(RegistrationVO user, Calendar calendar);
	public List<UserEventVO> getUserPlannedEvents(Long userID, Calendar calendar);
	public UserEventVO saveUserPlannedEvents(UserEventVO userPlannedEvents);
	public void deleteUserPlannedEvents(Long userEventID);
	public void userSubscribePartyImpDates(Long userID, String partySubscribeImpDates);
	public List<SelectOptionVO> getCadresByRegionType(Long userID, String regionType, Long regionID);
	public List<ImportantDatesVO> saveUserImpDate(ImportantDatesVO importantDatesVO);
	public UserEventVO getUserPlannedEvent(Long eventID);
	public List<ImportantDatesVO> getUserImpDate(Long impDateID, String dateType);
	public CadreManagementVO getUserImpDateAndEvent(RegistrationVO user, Calendar calendar);
	public void deleteUserImpDate(Long impDateID);
	
}
