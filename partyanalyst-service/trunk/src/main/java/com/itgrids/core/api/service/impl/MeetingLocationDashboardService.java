package com.itgrids.core.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.core.api.service.IMeetingLocationDashboardService;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDocumentDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.PartyMeetingDataVO;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class MeetingLocationDashboardService implements IMeetingLocationDashboardService{

	private static Logger LOG = Logger.getLogger(MeetingLocationDashboardService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private IPartyMeetingStatusDAO partyMeetingStatusDAO;
	private IPartyMeetingDAO partyMeetingDAO;
	private IPartyMeetingInviteeDAO partyMeetingInviteeDAO;
	private IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO;
	private IPartyMeetingDocumentDAO partyMeetingDocumentDAO;
	
	
	
	public IPartyMeetingDocumentDAO getPartyMeetingDocumentDAO() {
		return partyMeetingDocumentDAO;
	}

	public void setPartyMeetingDocumentDAO(
			IPartyMeetingDocumentDAO partyMeetingDocumentDAO) {
		this.partyMeetingDocumentDAO = partyMeetingDocumentDAO;
	}

	public IPartyMeetingAttendanceDAO getPartyMeetingAttendanceDAO() {
		return partyMeetingAttendanceDAO;
	}

	public void setPartyMeetingAttendanceDAO(
			IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO) {
		this.partyMeetingAttendanceDAO = partyMeetingAttendanceDAO;
	}

	public IPartyMeetingInviteeDAO getPartyMeetingInviteeDAO() {
		return partyMeetingInviteeDAO;
	}

	public void setPartyMeetingInviteeDAO(
			IPartyMeetingInviteeDAO partyMeetingInviteeDAO) {
		this.partyMeetingInviteeDAO = partyMeetingInviteeDAO;
	}

	public IPartyMeetingDAO getPartyMeetingDAO() {
		return partyMeetingDAO;
	}

	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}

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
			List<Object[]> list = partyMeetingStatusDAO.getLocationWiseMeetingsDeatils(locationValues,locationTypeId,startDate,endDate,null);
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
					Long locationId=commonMethodsUtilService.getLongValueForObject(objects[2]);
					String locationName= commonMethodsUtilService.getStringValueForObject(objects[3]);
					if(commonMethodsUtilService.getLongValueForObject(objects[2]) == 5l || commonMethodsUtilService.getLongValueForObject(objects[2]) ==4l
							|| commonMethodsUtilService.getLongValueForObject(objects[2]) == 6l){
						locationId=4l;
						locationName="MANDAL/DIVISION/TOWN";
					}else if(commonMethodsUtilService.getLongValueForObject(objects[2]) == 7l || commonMethodsUtilService.getLongValueForObject(objects[2]) == 8l){
						locationId=7l;
						locationName="VILLAGE/WARD";
					}
					LocationVotersVO levelVO =levelMap.get(locationId);
					LocationVotersVO totalLvlVO = totalLevelMap.get(locationId);
					if(totalLvlVO == null){
						totalLvlVO = new LocationVotersVO();
						totalLvlVO.setCasteGroupId(locationId);
						totalLvlVO.setCastgroup(locationName);
						totalLevelMap.put(totalLvlVO.getCasteGroupId(), totalLvlVO);
					}
					
					if(levelVO == null){
						levelVO =   new LocationVotersVO();
						levelVO.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));//partyMeetingMainTypeId
						levelVO.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[1]));//partyMeetingMainType
						levelVO.setCasteGroupId(locationId);//partyMeetingLevelId
						levelVO.setCastgroup(locationName);//partyMeetingLevel
						levelMap.put(locationId, levelVO);
					}
					
					String status = commonMethodsUtilService.getStringValueForObject(objects[4]);
					if(status != null && status.equalsIgnoreCase("Y")){
						levelVO.setMaleCadres(totalLvlVO.getMaleCadres()+commonMethodsUtilService.getLongValueForObject(objects[5])); // done count
						totalLvlVO.setMaleCadres(totalLvlVO.getMaleCadres()+commonMethodsUtilService.getLongValueForObject(objects[5]));
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
	
	public PartyMeetingDataVO getLocationWiseCommitteeMeetings(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr,Long partyMeetingMainTypeId){
		PartyMeetingDataVO returnVO = new PartyMeetingDataVO();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			List<Object[]> list = partyMeetingStatusDAO.getLocationWiseMeetingsDeatils(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId);
			Map<Long,Map<Long,PartyMeetingDataVO>> meetingMap = new HashMap<Long,Map<Long,PartyMeetingDataVO>>();
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] objects : list) {
					Map<Long,PartyMeetingDataVO> levelMap = meetingMap.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
					if(levelMap == null){
						levelMap = new HashMap<Long,PartyMeetingDataVO>();
						returnVO.setPartyMeetingId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						returnVO.setPartyMeetingName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						meetingMap.put(commonMethodsUtilService.getLongValueForObject(objects[0]), levelMap);
					}
					Long locationId=commonMethodsUtilService.getLongValueForObject(objects[2]);
					String locationName= commonMethodsUtilService.getStringValueForObject(objects[3]);
					if(commonMethodsUtilService.getLongValueForObject(objects[2]) == 4l || commonMethodsUtilService.getLongValueForObject(objects[2]) ==5l
							|| commonMethodsUtilService.getLongValueForObject(objects[2]) == 6l){
						locationId=4l;
						locationName="MANDAL/DIVISION/TOWN";
					}else if(commonMethodsUtilService.getLongValueForObject(objects[2]) == 7l || commonMethodsUtilService.getLongValueForObject(objects[2]) == 8l){
						locationId=7l;
						locationName="VILLAGE/WARD";
					}
					PartyMeetingDataVO levelVO =levelMap.get(locationId);
					
					if(levelVO == null){
						levelVO =   new PartyMeetingDataVO();
						levelVO.setPartyMeetingId(commonMethodsUtilService.getLongValueForObject(objects[0]));//partyMeetingMainTypeId
						levelVO.setPartyMeetingName(commonMethodsUtilService.getStringValueForObject(objects[1]));//partyMeetingMainType
						levelVO.setId(locationId);//partyMeetingLevelId
						levelVO.setName(locationName);//partyMeetingLevel
						levelMap.put(locationId, levelVO);
					}
					
					String status = commonMethodsUtilService.getStringValueForObject(objects[4]);
					if(status != null && status.equalsIgnoreCase("Y")){
						levelVO.setConductedMeetings(levelVO.getConductedMeetings()+commonMethodsUtilService.getLongValueForObject(objects[5])); // YES count
					}else if(status != null && status.equalsIgnoreCase("N")){
						levelVO.setNotConductedMeetings(levelVO.getNotConductedMeetings()+commonMethodsUtilService.getLongValueForObject(objects[5])); // NO count
					}else if(status != null && status.equalsIgnoreCase("M")){
						levelVO.setMaybeeMeetings(levelVO.getMaybeeMeetings()+commonMethodsUtilService.getLongValueForObject(objects[5])); // May be count
					}else if(status != null && status.equalsIgnoreCase("N")){
						levelVO.setNotUpdatedCount(levelVO.getNotUpdatedCount()+commonMethodsUtilService.getLongValueForObject(objects[5])); // Not Updated count
					}
					levelVO.setTotalMeetings(levelVO.getTotalMeetings()+commonMethodsUtilService.getLongValueForObject(objects[5]));//total
				}
			}
			
			if(commonMethodsUtilService.isMapValid(meetingMap)){
				Map<Long,PartyMeetingDataVO> levelMap=meetingMap.get(partyMeetingMainTypeId);
					if(commonMethodsUtilService.isMapValid(levelMap)){
						for (Entry<Long, PartyMeetingDataVO> entry :levelMap.entrySet()){
							returnVO.getLevelList().add(entry.getValue());
						}
					}
				}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised at getLocationWiseMeetingsCount", e);
		}
		return returnVO;
	}
	
	public PartyMeetingDataVO getLocationWiseStateMeetings(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr,Long partyMeetingMainTypeId){
		PartyMeetingDataVO returnVO = new PartyMeetingDataVO();
		
		try{
			List<PartyMeetingDataVO> partyMeetingTypes = new ArrayList<PartyMeetingDataVO>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			List<Object[]> totalList = partyMeetingDAO.getLocationWiseStateMeetings(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId);
			List<Object[]> invitees = partyMeetingInviteeDAO.getLocationWiseStateMeetingInvitees(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId);
			List<Object[]> attendees = partyMeetingAttendanceDAO.getLocationWiseStateMeetingAttendees(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId);
			List<Object[]> images = partyMeetingDocumentDAO.getLocationWiseStateMeetingImages(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId);
			Map<Long,Map<Long,PartyMeetingDataVO>> meetingTypeMap = new HashMap<Long,Map<Long,PartyMeetingDataVO>>();
			Map<Long,PartyMeetingDataVO> totalCntMap = new HashMap<Long,PartyMeetingDataVO>();
			if(commonMethodsUtilService.isListOrSetValid(totalList)){
				for (Object[] param : totalList) {
					PartyMeetingDataVO totalMeetings = totalCntMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
					if(totalMeetings == null){
						totalMeetings = new PartyMeetingDataVO();
						totalMeetings.setTotalMeetings(commonMethodsUtilService.getLongValueForObject(param[4]));
						totalCntMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), totalMeetings);
					}
				}
			}
			
			setMeetingDetails(meetingTypeMap,invitees,"invitees");
			setMeetingDetails(meetingTypeMap,invitees,"attendees");
			setMeetingDetails(meetingTypeMap,invitees,"images");
			
			if(commonMethodsUtilService.isMapValid(meetingTypeMap)){
				for (Entry<Long, Map<Long, PartyMeetingDataVO>> entry :meetingTypeMap.entrySet()) {
					Map<Long,PartyMeetingDataVO> cadresmap = entry.getValue();
					PartyMeetingDataVO  meetingTypeVO = totalCntMap.get(entry.getKey());
					if(commonMethodsUtilService.isMapValid(cadresmap)){
						for (Entry<Long, PartyMeetingDataVO> candidates :cadresmap.entrySet()) {
							PartyMeetingDataVO vo =candidates.getValue();
							meetingTypeVO.setPartyMeetingId(vo.getPartyMeetingId());
							meetingTypeVO.setPartyMeetingName(vo.getPartyMeetingName());
							meetingTypeVO.setId(vo.getId());
							meetingTypeVO.setName(vo.getName());
							Set<Long> inviteeIds = vo.getInviteeIds();
							if(commonMethodsUtilService.isListOrSetValid(inviteeIds))
								meetingTypeVO.setInvitedCount(meetingTypeVO.getInvitedCount()+inviteeIds.size());
							Set<Long> attendedIds = vo.getInviteeIds();
								if(commonMethodsUtilService.isListOrSetValid(attendedIds)){
									meetingTypeVO.setAttendedCount(meetingTypeVO.getAttendedCount()+attendedIds.size());
									for (Long long1 : attendedIds) {
										if(inviteeIds.contains(long1)){
											meetingTypeVO.setInviteeAttendedCount(meetingTypeVO.getInviteeAttendedCount()+1);
										}
									}
									meetingTypeVO.setImagesCnt(meetingTypeVO.getImagesCnt()+vo.getImagesCnt());
								}
						}
					}
				}
			}
			returnVO.getLevelList().addAll(totalCntMap.values());
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised at getLocationWiseStateMeetings", e);
		}
		return returnVO;
	}
	
	public void setMeetingDetails(Map<Long,Map<Long,PartyMeetingDataVO>> meetingTypeMap,List<Object[]> invitees,String type){
		try{
			if(commonMethodsUtilService.isListOrSetValid(invitees)){
				for (Object[] param : invitees) {
					Map<Long,PartyMeetingDataVO> inviteesMap = meetingTypeMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
					if(inviteesMap == null){
						inviteesMap = new HashMap<Long,PartyMeetingDataVO>();
						meetingTypeMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), inviteesMap);
					}
					
					PartyMeetingDataVO  partyMeetingVO = inviteesMap.get(commonMethodsUtilService.getLongValueForObject(param[5]));
					if(partyMeetingVO == null){
						partyMeetingVO = new PartyMeetingDataVO();
						partyMeetingVO.setPartyMeetingId(commonMethodsUtilService.getLongValueForObject(param[0]));
						partyMeetingVO.setPartyMeetingName(commonMethodsUtilService.getStringValueForObject(param[1]));
						partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
						partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
						inviteesMap.put(commonMethodsUtilService.getLongValueForObject(param[5]),partyMeetingVO);
					}
					if(type != null && type.equalsIgnoreCase("images")){
						partyMeetingVO.setImagesCnt(partyMeetingVO.getImagesCnt()+1);
					}else{
						partyMeetingVO.getInviteeIds().add(commonMethodsUtilService.getLongValueForObject(param[4]));
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised at setMeetingDetails", e);
		}
	}
}
