package com.itgrids.partyanalyst.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.CadreManagementVO;
import com.itgrids.partyanalyst.dto.ImportantDatesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
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

	public CadreManagementVO getUserData(RegistrationVO user) {
		log.debug("UserCadreManagementService.getUserData()::::started");
		CadreManagementVO cadreManagementVO = new CadreManagementVO();
		UserCadresInfoVO userCadreInfo = new UserCadresInfoVO();
		
		if(user.getParentUserId() == null)
			userCadreInfo.setIsParent(true);
		else
			userCadreInfo.setIsParent(false);
		userCadreInfo.setUserID(user.getRegistrationID());
		userCadreInfo.setUserAccessType(user.getAccessType());
		userCadreInfo.setUserAccessValue(user.getAccessValue());
		
		Long userID = user.getRegistrationID();
		Long partyID = user.getParty();
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			List<UserEventVO> userPlannedEvents =userCalendarService.getUserPlannedEvents(userID,calendar);
			cadreManagementVO.setUserEvents(userPlannedEvents);
			List<ImportantDatesVO> userImpDatesList = userCalendarService.getUserImpDates(user,calendar);
			cadreManagementVO.setUserImpDates(userImpDatesList);
			Map<String,Long> cadresByCadreLevel = cadreManagementService.getCadreLevelCadresCount(userCadreInfo);
			cadreManagementVO.setCadresByCadreLevel(cadresByCadreLevel);
		}catch (Exception exceptionEncountered) {
			cadreManagementVO.setExceptionEncountered(exceptionEncountered);
			
		}
		//log.debug("UserCadreManagementService.getUserData()::::cadreManagementVO.getUserImpDates().size()::"+cadreManagementVO.getUserImpDates().size());
		return cadreManagementVO;
	}
	
	public CadreManagementVO getUserTodaysData(RegistrationVO user) {
		log.debug("UserCadreManagementService.getUserData()::::started");
		CadreManagementVO cadreManagementVO = new CadreManagementVO();
		UserCadresInfoVO userCadreInfo = new UserCadresInfoVO();
		
		if(user.getParentUserId() == null)
			userCadreInfo.setIsParent(true);
		else
			userCadreInfo.setIsParent(false);
		userCadreInfo.setUserID(user.getRegistrationID());
		userCadreInfo.setUserAccessType(user.getAccessType());
		userCadreInfo.setUserAccessValue(user.getAccessValue());
		
		Long userID = user.getRegistrationID();
		try{
			List<UserEventVO> userPlannedEvents =userCalendarService.getTodaysUserPlannedEvents(userID);
			cadreManagementVO.setUserEvents(userPlannedEvents);
			List<ImportantDatesVO> userImpDatesList = userCalendarService.getUserTodaysImportantEvents(user);
			cadreManagementVO.setUserImpDates(userImpDatesList);
			Map<String,Long> cadresByCadreLevel = cadreManagementService.getCadreLevelCadresCount(userCadreInfo);
			cadreManagementVO.setCadresByCadreLevel(cadresByCadreLevel);
		}catch (Exception exceptionEncountered) {
			cadreManagementVO.setExceptionEncountered(exceptionEncountered);
			
		}
		//log.debug("UserCadreManagementService.getUserData()::::cadreManagementVO.getUserImpDates().size()::"+cadreManagementVO.getUserImpDates().size());
		return cadreManagementVO;
	}

}
