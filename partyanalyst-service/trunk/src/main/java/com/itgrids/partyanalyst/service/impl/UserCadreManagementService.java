package com.itgrids.partyanalyst.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.ImportantDatesVO;
import com.itgrids.partyanalyst.dto.UserEventVO;
import com.itgrids.partyanalyst.service.IUserCadreManagementService;
import com.itgrids.partyanalyst.service.IUserCalendarService;

public class UserCadreManagementService implements IUserCadreManagementService {

	private IUserCalendarService userCalendarService;
	private CadreManagementService cadreManagementService; 
	private final static Logger log = Logger.getLogger(UserCadreManagementService.class);
	
	public void setUserCalendarService(IUserCalendarService userCalendarService) {
		this.userCalendarService = userCalendarService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public CadreManagementVO getUserData(Long userID, Long partyID) {
		log.debug("UserCadreManagementService.getUserData()::::started");
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
		log.debug("UserCadreManagementService.getUserData()::::cadreManagementVO.getUserImpDates().size()::"+cadreManagementVO.getUserImpDates().size());
		return cadreManagementVO;
	}

}
