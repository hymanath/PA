package com.itgrids.core.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    Date fromDate=null;
			Date toDate=null;
			
			if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> locationIds = new ArrayList<Long>();
			if(locationId != null && locationId.longValue() > 0L){
				locationIds.add(locationId);
			}
			List<Object[]> meetingList =  partyMeetingDAO.getMeetingTypeWiseTotalMeetings(locationLevel,locationIds,fromDate,toDate);
		}catch(Exception e){
			LOG.error("Exception raised at getMeetingTypeWiseTotalMeetings() method of LoactionDetailsDashBoardService", e);
		}
		return null;
	}
	

}
