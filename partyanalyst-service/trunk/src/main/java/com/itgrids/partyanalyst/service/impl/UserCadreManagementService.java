package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.PartyImportantDatesVO;
import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.service.IUserCadreManagementService;
import com.itgrids.partyanalyst.service.IUserCalendarService;

public class UserCadreManagementService implements IUserCadreManagementService {

	private IUserCalendarService userCalendarService;
	private CadreManagementService cadreManagementService; 
	
	public void setUserCalendarService(IUserCalendarService userCalendarService) {
		this.userCalendarService = userCalendarService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public CadreManagementVO getUserData(Long userID, Long partyID) {
		CadreManagementVO cadreManagementVO = new CadreManagementVO();
		try{
			List<UserEventVO> userPlannedEvents =userCalendarService.getUserPlannedEvents(userID);
			cadreManagementVO.setUserEvents(userPlannedEvents);
			List<PartyImportantDatesVO> userImpDatesList = userCalendarService.getUserImpDates(userID, partyID);
			cadreManagementVO.setUserImpDates(userImpDatesList);
		}catch (Exception exceptionEncountered) {
			cadreManagementVO.setExceptionEncountered(exceptionEncountered);
			
		}
		return cadreManagementVO;
	}

}
