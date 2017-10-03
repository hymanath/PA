package com.itgrids.core.api.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.core.api.service.ILoactionDetailsDashBoardService;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
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
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	/*
	 * Swadhin K Lenka
	 * @see com.itgrids.core.api.service.ILoactionDetailsDashBoardService#getMeetingTypeWiseTotalMeetings(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public List<PartyMeetingsVO> getMeetingTypeWiseTotalMeetings(Long locationLevel, Long locationId, String fromDateStr,String toDateStr){
		try{
			
		}catch(Exception e){
			LOG.error("Exception raised at getMeetingTypeWiseTotalMeetings() method of LoactionDetailsDashBoardService", e);
		}
		return null;
	}
	

}
