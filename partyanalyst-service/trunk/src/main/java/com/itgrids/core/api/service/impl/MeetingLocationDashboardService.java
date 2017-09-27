package com.itgrids.core.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.itgrids.core.api.service.IMeetingLocationDashboardService;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class MeetingLocationDashboardService implements IMeetingLocationDashboardService{

	private static Logger LOG = Logger.getLogger(MeetingLocationDashboardService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private IPartyMeetingStatusDAO partyMeetingStatusDAO;
	
	
	public IPartyMeetingStatusDAO getPartyMeetingStatusDAO() {
		return partyMeetingStatusDAO;
	}

	public void setPartyMeetingStatusDAO(
			IPartyMeetingStatusDAO partyMeetingStatusDAO) {
		this.partyMeetingStatusDAO = partyMeetingStatusDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	
	public List<LocationVotersVO> getLocationWiseMeetingsCount(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr){
		List<LocationVotersVO> voList = new ArrayList<LocationVotersVO>(0);
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			Map<Long,LocationVotersVO> totalLevelMap = new HashMap<Long,LocationVotersVO>();
			List<Object[]> list = partyMeetingStatusDAO.getLocationWiseMeetingsDeatils(locationValues,locationTypeId,startDate,endDate);
			Map<Long,Map<Long,LocationVotersVO>> meetingMap = new HashMap<Long,Map<Long,LocationVotersVO>>();
			if(commonMethodsUtilService.isListOrSetValid(list)){
				LocationVotersVO totalVO =   new LocationVotersVO();
				totalVO.setAgeRangeId(0l);
				totalVO.setAgeRange("Total Meetings");
				voList.add(0, totalVO);
				for (Object[] objects : list) {
					Map<Long,LocationVotersVO> levelMap = meetingMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(levelMap == null){
						levelMap = new HashMap<Long,LocationVotersVO>();
						LocationVotersVO meetingVO =   new LocationVotersVO();
						meetingVO.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						meetingVO.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[1]));
						voList.add(meetingVO);
						
						meetingMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), levelMap);
					}
					LocationVotersVO levelVO =levelMap.get(commonMethodsUtilService.getLongValueForObject(objects[2]));
					LocationVotersVO totalLvlVO = totalLevelMap.get(commonMethodsUtilService.getLongValueForObject(objects[2]));
					if(totalLvlVO == null){
						totalLvlVO = new LocationVotersVO();
						totalLvlVO.setCasteGroupId(commonMethodsUtilService.getLongValueForObject(objects[2]));
						totalLvlVO.setCastgroup(commonMethodsUtilService.getStringValueForObject(objects[3]));
						totalLevelMap.put(totalLvlVO.getCasteGroupId(), totalLvlVO);
					}
					
					if(levelVO == null){
						levelVO =   new LocationVotersVO();
						levelVO.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));//partyMeetingMainTypeId
						levelVO.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[1]));//partyMeetingMainType
						levelVO.setCasteGroupId(commonMethodsUtilService.getLongValueForObject(objects[2]));//partyMeetingLevelId
						levelVO.setCastgroup(commonMethodsUtilService.getStringValueForObject(objects[3]));//partyMeetingLevel
						levelMap.put(commonMethodsUtilService.getLongValueForObject(objects[2]), levelVO);
					}
					
					String status = commonMethodsUtilService.getStringValueForObject(objects[4]);
					if(status != null && status.equalsIgnoreCase("Y")){
						levelVO.setMaleCadres(commonMethodsUtilService.getLongValueForObject(objects[5]));
						totalLvlVO.setMaleCadres(commonMethodsUtilService.getLongValueForObject(objects[5]));
					}
					totalLvlVO.setTotalCadres(totalLvlVO.getTotalCadres()+commonMethodsUtilService.getLongValueForObject(objects[5]));
					levelVO.setTotalCadres(levelVO.getTotalCadres()+commonMethodsUtilService.getLongValueForObject(objects[5]));
					voList.get(0).setTotalCadres(voList.get(0).getTotalCadres()+commonMethodsUtilService.getLongValueForObject(objects[5]));
				}
			}
			
			if(commonMethodsUtilService.isMapValid(meetingMap) && commonMethodsUtilService.isListOrSetValid(voList)){
				for (LocationVotersVO meetingVO : voList) {
					Map<Long,LocationVotersVO> levelMap=meetingMap.get(meetingVO.getAgeRangeId());
					if(commonMethodsUtilService.isMapValid(levelMap)){
						//meetingVO.setLocationVotersVOList((List<LocationVotersVO>) levelMap.values());
						for(Entry<Long,LocationVotersVO> levelVO :levelMap.entrySet()){
							meetingVO.setTotalCadres(meetingVO.getTotalCadres()+levelVO.getValue().getTotalCadres());
							
							meetingVO.getLocationVotersVOList().add(levelVO.getValue());
						}
					}
				}
			}
			if(commonMethodsUtilService.isMapValid(totalLevelMap)){
				voList.get(0).getLocationVotersVOList().addAll(totalLevelMap.values());
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised at getLocationWiseMeetingsCount", e);
		}
		return voList;
	}
}
