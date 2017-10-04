package com.itgrids.core.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.itgrids.core.api.service.ILoactionDetailsDashBoardService;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dto.PartyMeetingsVO;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class LoactionDetailsDashBoardService implements ILoactionDetailsDashBoardService {
	
	private final static Logger LOG =  Logger.getLogger(LoactionDetailsDashBoardService.class);
	
	private CommonMethodsUtilService commonMethodsUtilService;
	
	private SetterAndGetterUtilService setterAndGetterUtilService;
	
	private IPartyMeetingDAO partyMeetingDAO;
	
	private IPartyMeetingStatusDAO partyMeetingStatusDAO;
	
	private IPartyMeetingInviteeDAO  partyMeetingInviteeDAO;  
	
	private IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO;
	
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}
	
	public void setPartyMeetingStatusDAO(
			IPartyMeetingStatusDAO partyMeetingStatusDAO) {
		this.partyMeetingStatusDAO = partyMeetingStatusDAO;
	}
	
	/*
	 * Swadhin K Lenka
	 * Date: 03/10/2017
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
			List<PartyMeetingsVO> finalResult = new ArrayList<PartyMeetingsVO>();
			PartyMeetingsVO partyMeetingsVO = null;
			if(meetingList != null && meetingList.size() > 0){
				for(Object[] param : meetingList){
					partyMeetingsVO = new PartyMeetingsVO();
					partyMeetingsVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					partyMeetingsVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					partyMeetingsVO.setMeetingCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					finalResult.add(partyMeetingsVO);
				}
			}
			return finalResult;
		}catch(Exception e){
			LOG.error("Exception raised at getMeetingTypeWiseTotalMeetings() method of LoactionDetailsDashBoardService", e);
		}
		return null;
	}
	
	/*
	 * Swadhin K Lenka
	 * Date: 04/10/2017
	 * @see com.itgrids.core.api.service.ILoactionDetailsDashBoardService#getMeetingLevelWiseTotalMeetings(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public List<PartyMeetingsVO> getMeetingLevelWiseTotalMeetings(Long locationLevel, Long locationId, String fromDateStr,String toDateStr){
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
			List<Object[]> meetingList =  partyMeetingDAO.getMeetingLevelWiseTotalMeetings(locationLevel,locationIds,fromDate,toDate);
			List<PartyMeetingsVO> finalResult = new ArrayList<PartyMeetingsVO>();
			PartyMeetingsVO partyMeetingsVO = null;
			if(meetingList != null && meetingList.size() > 0){
				for(Object[] param : meetingList){
					partyMeetingsVO = new PartyMeetingsVO();
					partyMeetingsVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					partyMeetingsVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					partyMeetingsVO.setMeetingCount(commonMethodsUtilService.getLongValueForObject(param[2]));
					finalResult.add(partyMeetingsVO);
				}
			}
			return finalResult;
		}catch(Exception e){
			LOG.error("Exception raised at getMeetingLevelWiseTotalMeetings() method of LoactionDetailsDashBoardService", e);
		}
		return null;
	}
	/*
	 * 1	aaa		y	10
	 * 1	aaa		n	20
	 * 1	aaa		nu	30
	 * 1	aaa		m	40
	 * 
	 * 2	aaa		y	10
	 * 2	aaa		n	20
	 * 2	aaa		nu	30
	 * 2	aaa		m	40
	 * 
	 */
	/*
	 * Swadhin K Lenka
	 * Date: 04/10/2017
	 * @see com.itgrids.core.api.service.ILoactionDetailsDashBoardService#getCommitteeMeetingStatistics(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public List<PartyMeetingsVO> getCommitteeMeetingStatistics(Long locationLevel, Long locationId, String fromDateStr,String toDateStr){
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
			List<Object[]> meetingList =  partyMeetingStatusDAO.getCommitteeMeetingStatistics(locationLevel,locationIds,fromDate,toDate);
			
			//create a map for levelId And map of status and count map
			Map<Long,HashMap<String,Long>> levelIdAndStatusAndCountMap = new HashMap<Long,HashMap<String,Long>>();
			HashMap<String,Long> statusAndCountMap = null;
			if(meetingList != null && meetingList.size() > 0){
				for(Object[] param : meetingList){
					statusAndCountMap = levelIdAndStatusAndCountMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(statusAndCountMap == null){
						statusAndCountMap = new HashMap<String,Long>();
						levelIdAndStatusAndCountMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), statusAndCountMap);
					}
					statusAndCountMap.put(commonMethodsUtilService.getStringValueForObject(param[2]), commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}
			
			List<PartyMeetingsVO> finalResult = new ArrayList<PartyMeetingsVO>();
			PartyMeetingsVO partyMeetingsVO1 = new PartyMeetingsVO();
			//create a template for ui
			partyMeetingsVO1.setId(3L);
			partyMeetingsVO1.setName("CONSTITUENCY");
			finalResult.add(partyMeetingsVO1);
			PartyMeetingsVO partyMeetingsVO2 = new PartyMeetingsVO();
			partyMeetingsVO2.setId(4L);
			partyMeetingsVO2.setName("MANDAL/TOWN/DIV");
			finalResult.add(partyMeetingsVO2);
			PartyMeetingsVO partyMeetingsVO3 = new PartyMeetingsVO();
			partyMeetingsVO3.setId(7L);
			partyMeetingsVO3.setName("VILLAGE/WARD");
			finalResult.add(partyMeetingsVO3);
			if(levelIdAndStatusAndCountMap != null && levelIdAndStatusAndCountMap.size() > 0){
				buildFinalResult(finalResult,levelIdAndStatusAndCountMap);
			}
			if(finalResult != null && finalResult.size() > 0){
				for(PartyMeetingsVO param : finalResult){
					param.setTotalCount(param.getConductedCount()+param.getNotConductedCount()+param.getMayBeCount()+param.getNotUpdatedCount());
				}
			}
			return finalResult;
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteeMeetingStatistics() method of LoactionDetailsDashBoardService", e);
		}
		return null;
	}
	public void buildFinalResult(List<PartyMeetingsVO> finalResult,Map<Long,HashMap<String,Long>> levelIdAndStatusAndCountMap){
		try{
			PartyMeetingsVO partyMeetingsVO = null;
			for(Entry<Long,HashMap<String,Long>> outerParam : levelIdAndStatusAndCountMap.entrySet()){
				if(outerParam.getKey().longValue() == 3L){
					partyMeetingsVO = (PartyMeetingsVO) setterAndGetterUtilService.getMatchedVOfromList(finalResult, "id", "3");
					pushCount(partyMeetingsVO,outerParam.getValue());
				}else if(outerParam.getKey().longValue() == 4L || outerParam.getKey().longValue() == 5L || outerParam.getKey().longValue() == 6L){
					partyMeetingsVO = (PartyMeetingsVO) setterAndGetterUtilService.getMatchedVOfromList(finalResult, "id", "4");
					pushCount(partyMeetingsVO,outerParam.getValue());
				}else if(outerParam.getKey().longValue() == 7L || outerParam.getKey().longValue() == 8L){
					partyMeetingsVO = (PartyMeetingsVO) setterAndGetterUtilService.getMatchedVOfromList(finalResult, "id", "7");
					pushCount(partyMeetingsVO,outerParam.getValue());
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at buildFinalResult() method of LoactionDetailsDashBoardService", e);
		}
	}
	public void pushCount(PartyMeetingsVO partyMeetingsVO,HashMap<String,Long> statusAndCountMap){
		try{
			if(statusAndCountMap != null && statusAndCountMap.size() > 0){
				for(Entry<String,Long> param : statusAndCountMap.entrySet()){
					if(param.getKey().equalsIgnoreCase("Y")){
						partyMeetingsVO.setConductedCount(partyMeetingsVO.getConductedCount()+param.getValue());
					}else if(param.getKey().equalsIgnoreCase("N")){
						partyMeetingsVO.setNotConductedCount(partyMeetingsVO.getNotConductedCount()+param.getValue());
					}else if(param.getKey().equalsIgnoreCase("M")){
						partyMeetingsVO.setMayBeCount(partyMeetingsVO.getMayBeCount()+param.getValue());
					}else if(param.getKey().equalsIgnoreCase("NU")){
						partyMeetingsVO.setNotUpdatedCount(partyMeetingsVO.getNotUpdatedCount()+param.getValue());
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at pushCount() method of LoactionDetailsDashBoardService", e);
		}
	}
	
	/*
	 * Swadhin K Lenka
	 * Date: 04/10/2017
	 * @see com.itgrids.core.api.service.ILoactionDetailsDashBoardService#getSpecialMeetingStatistics(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public List<PartyMeetingsVO> getSpecialMeetingStatistics(Long locationLevel, Long locationId, String fromDateStr,String toDateStr){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    Date fromDate=null;
			Date toDate=null;
			
			if(fromDateStr != null && fromDateStr.trim().length()>0 && toDateStr!= null && toDateStr.trim().length()>0){
				fromDate = sdf.parse(fromDateStr);
				toDate = sdf.parse(toDateStr);
			}
			List<Long> locationIds = new ArrayList<Long>();
			
			List<Object[]> inviteeList = partyMeetingInviteeDAO.getInviteeList(locationLevel,locationIds,fromDate,toDate);
			List<Object[]> attendedList = partyMeetingAttendanceDAO.getAttendedList(locationLevel,locationIds,fromDate,toDate);
			
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteeMeetingStatistics() method of LoactionDetailsDashBoardService", e);
		}
		return null;
	}
	
}
