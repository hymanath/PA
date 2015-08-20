package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dto.PartyMeetingDetailsVO;
import com.itgrids.partyanalyst.service.IPartyMeetingDashboardService;

public class PartyMeetingDashboardService implements IPartyMeetingDashboardService{

	private static Logger LOG = Logger.getLogger(PartyMeetingDashboardService.class);
			
	public List<PartyMeetingDetailsVO> getPartyMeetingDetailsForDashboard(Long partyMeetingLevelId,Date fromDate,Date toDate,Long locationLevelId,Long locationValue)
	{
		List<PartyMeetingDetailsVO> result = new ArrayList<PartyMeetingDetailsVO>(0);
		try{
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in PartyMeetingDashboardService() Method - ",e);
		}
		return result;
	}

}
