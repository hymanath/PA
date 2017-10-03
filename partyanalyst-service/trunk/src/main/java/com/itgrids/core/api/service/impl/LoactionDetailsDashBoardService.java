package com.itgrids.core.api.service.impl;

import org.apache.log4j.Logger;

import com.itgrids.core.api.service.ILoactionDetailsDashBoardService;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class LoactionDetailsDashBoardService implements ILoactionDetailsDashBoardService {
	private final static Logger LOG =  Logger.getLogger(LoactionDetailsDashBoardService.class);
	private IPartyMeetingDAO partyMeetingDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	
	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		commonMethodsUtilService = commonMethodsUtilService;
	}
	
	

}
