package com.itgrids.partyanalyst.service.impl;

import java.util.List;
import java.util.Map;

import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.ImportantDatesVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
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

	public CadreManagementVO getUserData(UserCadresInfoVO userCadresInfoVO) {
		Long userID = userCadresInfoVO.getUserID();
		Long partyID = userCadresInfoVO.getPartyID();
		CadreManagementVO cadreManagementVO = new CadreManagementVO();
		try{
			List<UserEventVO> userPlannedEvents =userCalendarService.getUserPlannedEvents(userID);
			cadreManagementVO.setUserEvents(userPlannedEvents);
			List<ImportantDatesVO> userImpDatesList = userCalendarService.getUserImpDates(userID, partyID);
			cadreManagementVO.setUserImpDates(userImpDatesList);
			Map<String,Long> cadresByCadreLevel = cadreManagementService.getCadreLevelCadresCount(userID);
			cadreManagementVO.setCadresByCadreLevel(cadresByCadreLevel);
		}catch (Exception exceptionEncountered) {
			cadreManagementVO.setExceptionEncountered(exceptionEncountered);
			
		}
		return cadreManagementVO;
	}

}
