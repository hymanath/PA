package com.itgrids.core.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}

	public void setPartyMeetingInviteeDAO(
			IPartyMeetingInviteeDAO partyMeetingInviteeDAO) {
		this.partyMeetingInviteeDAO = partyMeetingInviteeDAO;
	}

	public void setPartyMeetingAttendanceDAO(
			IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO) {
		this.partyMeetingAttendanceDAO = partyMeetingAttendanceDAO;
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
			//create a map for meetingTypeId and meetingId and cadreIdSet for invitee
			Map<Long,Map<Long,Set<Long>>> meetingTypeIdAndMeetingIdAndCadreIdSetMapForInvitee = new HashMap<Long,Map<Long,Set<Long>>>();
			if(inviteeList != null && inviteeList.size() > 0){
				createMapForObjectArr(meetingTypeIdAndMeetingIdAndCadreIdSetMapForInvitee,inviteeList);
			}
			
			List<Object[]> attendedList = partyMeetingAttendanceDAO.getAttendedList(locationLevel,locationIds,fromDate,toDate);
			//create a map for meetingTypeId and meetingId and cadreIdSet for attended
			Map<Long,Map<Long,Set<Long>>> meetingTypeIdAndMeetingIdAndCadreIdSetMapForAttended = new HashMap<Long,Map<Long,Set<Long>>>();
			if(attendedList != null && attendedList.size() > 0){
				createMapForObjectArr(meetingTypeIdAndMeetingIdAndCadreIdSetMapForAttended,attendedList);
			}
			
			//now create a map for invitee attended from above two map
			
			//create a map for meetingTypeId and meetingId and cadreIdSet for invitee attended
			Map<Long,Map<Long,Set<Long>>> meetingTypeIdAndMeetingIdAndCadreIdSetMapForInviteeAttended = new HashMap<Long,Map<Long,Set<Long>>>();
			
			createMapForInviteeAttended(meetingTypeIdAndMeetingIdAndCadreIdSetMapForInviteeAttended,meetingTypeIdAndMeetingIdAndCadreIdSetMapForInvitee,meetingTypeIdAndMeetingIdAndCadreIdSetMapForAttended);
			
			//create a template for ui
			Map<Long,String> meetingTypeIdAndNameMap = new HashMap<Long,String>();
			if(inviteeList != null && inviteeList.size() > 0){
				for(Object[] param : inviteeList){
					meetingTypeIdAndNameMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getStringValueForObject(param[1]));
				}
			}
			
			List<PartyMeetingsVO> finalResult = new ArrayList<PartyMeetingsVO>();
			PartyMeetingsVO partyMeetingsVO = null;
			
			if(meetingTypeIdAndNameMap != null && meetingTypeIdAndNameMap.size() > 0){
				for(Entry<Long,String> param : meetingTypeIdAndNameMap.entrySet()){
					partyMeetingsVO = new PartyMeetingsVO();
					partyMeetingsVO.setId(param.getKey());
					partyMeetingsVO.setName(param.getValue());
					finalResult.add(partyMeetingsVO);
				}
			}
			
			//push no of meetings and invitees
			if(finalResult != null && finalResult.size() > 0){
				for(PartyMeetingsVO param : finalResult){
					pushNoOfMeetingsAndInvitees( param,meetingTypeIdAndMeetingIdAndCadreIdSetMapForInvitee);
				}
			}
			//push no of invitee attended
			if(finalResult != null && finalResult.size() > 0){
				for(PartyMeetingsVO param : finalResult){
					pushInviteeAttended(param,meetingTypeIdAndMeetingIdAndCadreIdSetMapForInviteeAttended);
				}
			}
			//push no of absent count
			if(finalResult != null && finalResult.size() > 0){
				for(PartyMeetingsVO param : finalResult){
					param.setAbsentCount(param.getInvitedCount() - param.getInviteeAttendedCount());
				}
			}
			return finalResult;
		}catch(Exception e){
			LOG.error("Exception raised at getCommitteeMeetingStatistics() method of LoactionDetailsDashBoardService", e);
		}
		return null;
	}
	public void pushInviteeAttended(PartyMeetingsVO param,Map<Long,Map<Long,Set<Long>>> meetingTypeIdAndMeetingIdAndCadreIdSetMapForInviteeAttended){
		try{
			int totalMember = 0;
			if(meetingTypeIdAndMeetingIdAndCadreIdSetMapForInviteeAttended.get(param.getId()) != null){
				for(Entry<Long,Set<Long>> innerParam : meetingTypeIdAndMeetingIdAndCadreIdSetMapForInviteeAttended.get(param.getId()).entrySet()){
					totalMember = totalMember + innerParam.getValue().size();
				}
			}
			param.setInviteeAttendedCount(Long.valueOf(totalMember));
		}catch(Exception e){
			LOG.error("Exception raised at pushInviteeAttended() method of LoactionDetailsDashBoardService", e);
		}
	}
	public void pushNoOfMeetingsAndInvitees(PartyMeetingsVO param, Map<Long,Map<Long,Set<Long>>> meetingTypeIdAndMeetingIdAndCadreIdSetMapForInvitee){
		try{
			Long count = 0L;
			int totalMember = 0;
			if(meetingTypeIdAndMeetingIdAndCadreIdSetMapForInvitee.get(param.getId()) != null){
				for(Entry<Long,Set<Long>> innerParam : meetingTypeIdAndMeetingIdAndCadreIdSetMapForInvitee.get(param.getId()).entrySet()){
					count = count + 1;
					totalMember = totalMember + innerParam.getValue().size();
				}
			}
			param.setTotalCount(count);
			param.setInvitedCount(Long.valueOf(totalMember));
		}catch(Exception e){
			LOG.error("Exception raised at pushNoOfMeetingsAndInvitees() method of LoactionDetailsDashBoardService", e);
		}
	}
	public void createMapForObjectArr(Map<Long,Map<Long,Set<Long>>> meetingTypeIdAndMeetingIdAndCadreIdSetMap,List<Object[]> inviteeList){
		try{
			Map<Long,Set<Long>> meetingIdAndCadreIdSetMap = null;
			Set<Long> cadreIdSet = null;
			for(Object[] param : inviteeList){
				meetingIdAndCadreIdSetMap = meetingTypeIdAndMeetingIdAndCadreIdSetMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				if(meetingIdAndCadreIdSetMap == null){
					cadreIdSet = new HashSet<Long>();
					cadreIdSet.add(commonMethodsUtilService.getLongValueForObject(param[3]));
					meetingIdAndCadreIdSetMap = new HashMap<Long,Set<Long>>();
					meetingIdAndCadreIdSetMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), cadreIdSet);
					meetingTypeIdAndMeetingIdAndCadreIdSetMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), meetingIdAndCadreIdSetMap);
				}else{
					cadreIdSet = meetingIdAndCadreIdSetMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
					if(cadreIdSet == null){
						cadreIdSet = new HashSet<Long>();
						meetingIdAndCadreIdSetMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), cadreIdSet);
					}
					cadreIdSet.add(commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception raised at createMapForObjectArr() method of LoactionDetailsDashBoardService", e);
		}
	}
	public void createMapForInviteeAttended(Map<Long,Map<Long,Set<Long>>> meetingTypeIdAndMeetingIdAndCadreIdSetMapForInviteeAttended,Map<Long,Map<Long,Set<Long>>> meetingTypeIdAndMeetingIdAndCadreIdSetMapForInvitee,Map<Long,Map<Long,Set<Long>>> meetingTypeIdAndMeetingIdAndCadreIdSetMapForAttended){
		try{
			Long meetingTypeId = 0L;
			Long meetingId = 0L;
			Set<Long> invitedCadreIdSet = null;
			Set<Long> attendedCadreIdSet = null;
			if(meetingTypeIdAndMeetingIdAndCadreIdSetMapForInvitee != null && meetingTypeIdAndMeetingIdAndCadreIdSetMapForInvitee.size() > 0){
				for(Entry<Long,Map<Long,Set<Long>>> outerParam : meetingTypeIdAndMeetingIdAndCadreIdSetMapForInvitee.entrySet()){
					meetingTypeId = outerParam.getKey();
					for(Entry<Long,Set<Long>> innerParam : outerParam.getValue().entrySet()){
						meetingId = innerParam.getKey();
						invitedCadreIdSet = innerParam.getValue();
						if(meetingTypeIdAndMeetingIdAndCadreIdSetMapForAttended.get(outerParam.getKey()) != null && meetingTypeIdAndMeetingIdAndCadreIdSetMapForAttended.get(outerParam.getKey()).get(innerParam.getKey()) != null){
							attendedCadreIdSet = meetingTypeIdAndMeetingIdAndCadreIdSetMapForAttended.get(outerParam.getKey()).get(innerParam.getKey());
						}
						initializeInviteeAttendedMap(meetingTypeId,meetingId,invitedCadreIdSet,attendedCadreIdSet,meetingTypeIdAndMeetingIdAndCadreIdSetMapForInviteeAttended);
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at createMapForInviteeAttended() method of LoactionDetailsDashBoardService", e);
		}
	}
	public void initializeInviteeAttendedMap(Long meetingTypeId,Long meetingId,Set<Long> invitedCadreIdSet,Set<Long> attendedCadreIdSet,Map<Long,Map<Long,Set<Long>>> meetingTypeIdAndMeetingIdAndCadreIdSetMapForInviteeAttended){
		try{
			Map<Long,Set<Long>> meetingIdAndCadreIdSetMap = null;
			Set<Long> cadreIdSet = null;
			Set<Long> tempCadreIdSet = new HashSet<Long>();
			if(invitedCadreIdSet != null && attendedCadreIdSet != null){
				tempCadreIdSet.addAll(invitedCadreIdSet);
				tempCadreIdSet.retainAll(attendedCadreIdSet);
				if(tempCadreIdSet != null){
					meetingIdAndCadreIdSetMap = meetingTypeIdAndMeetingIdAndCadreIdSetMapForInviteeAttended.get(meetingTypeId);
					if(meetingIdAndCadreIdSetMap == null){
						meetingIdAndCadreIdSetMap = new HashMap<Long,Set<Long>>();
						meetingIdAndCadreIdSetMap.put(meetingId, tempCadreIdSet);
						meetingTypeIdAndMeetingIdAndCadreIdSetMapForInviteeAttended.put(meetingTypeId, meetingIdAndCadreIdSetMap);
					}else{
						cadreIdSet = meetingIdAndCadreIdSetMap.get(meetingId);
						if(cadreIdSet == null){
							meetingIdAndCadreIdSetMap.put(meetingId, tempCadreIdSet);
						}
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception raised at initializeInviteeAttendedMap() method of LoactionDetailsDashBoardService", e);
		}
	}
	/*
	 * Swadhin K Lenka
	 * Date: 04/10/2017
	 * @see com.itgrids.core.api.service.ILoactionDetailsDashBoardService#getBelowLevelMeetingConductedCount(java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
	 */
	public List<PartyMeetingsVO> getBelowLevelMeetingConductedCount(Long locationLevel, Long locationId, String fromDateStr,String toDateStr){
		try{
			
		}catch(Exception e){
			LOG.error("Exception raised at getBelowLevelMeetingConductedCount() method of LoactionDetailsDashBoardService", e);
		}
		return null;
	}
	
}
