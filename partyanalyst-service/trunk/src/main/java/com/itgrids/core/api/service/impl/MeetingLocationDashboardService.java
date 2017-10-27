package com.itgrids.core.api.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.core.api.service.IMeetingLocationDashboardService;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDocumentDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.dto.PartyMeetingDataVO;
import com.itgrids.partyanalyst.service.impl.CoreDashboardPartyMeetingService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class MeetingLocationDashboardService implements IMeetingLocationDashboardService{

	private static Logger LOG = Logger.getLogger(MeetingLocationDashboardService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private IPartyMeetingStatusDAO partyMeetingStatusDAO;
	private IPartyMeetingDAO partyMeetingDAO;
	private IPartyMeetingInviteeDAO partyMeetingInviteeDAO;
	private IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO;
	private IPartyMeetingDocumentDAO partyMeetingDocumentDAO;
	private IDistrictDAO districtDAO;
	private DateUtilService dateUtilService;
	private CoreDashboardPartyMeetingService coreDashboardPartyMeetingService ;
	
	
	
	public CoreDashboardPartyMeetingService getCoreDashboardPartyMeetingService() {
		return coreDashboardPartyMeetingService;
	}

	public void setCoreDashboardPartyMeetingService(
			CoreDashboardPartyMeetingService coreDashboardPartyMeetingService) {
		this.coreDashboardPartyMeetingService = coreDashboardPartyMeetingService;
	}

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
	
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
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
					}else if(status != null && status.equalsIgnoreCase("NU")){
						levelVO.setNotUpdatedCount(levelVO.getNotUpdatedCount()+commonMethodsUtilService.getLongValueForObject(objects[5])); // Not Updated count
					}
					levelVO.setTotalMeetings(levelVO.getTotalMeetings()+commonMethodsUtilService.getLongValueForObject(objects[5]));//total
				}
			}
			
			if(commonMethodsUtilService.isMapValid(meetingMap)){
				Map<Long,PartyMeetingDataVO> levelMap=meetingMap.get(partyMeetingMainTypeId);
					if(commonMethodsUtilService.isMapValid(levelMap)){
						for (Entry<Long, PartyMeetingDataVO> entry :levelMap.entrySet()){
							PartyMeetingDataVO levelVO= entry.getValue();
							levelVO.setTotalPerc("100");
							levelVO.setYesPerc(calculatePercantage(levelVO.getConductedMeetings(), levelVO.getTotalMeetings()).toString());
							levelVO.setNoPerc(calculatePercantage(levelVO.getNotConductedMeetings(), levelVO.getTotalMeetings()).toString());
							levelVO.setMayBePerc(calculatePercantage(levelVO.getMaybeeMeetings(), levelVO.getTotalMeetings()).toString());
							levelVO.setNotUpdatePerc(calculatePercantage(levelVO.getNotUpdatedCount(), levelVO.getTotalMeetings()).toString());
							returnVO.getLevelList().add(levelVO);
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
			
			List<Object[]> totalList = partyMeetingDAO.getLocationWiseStateMeetings(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,"",null);
			List<Object[]> invitees = partyMeetingInviteeDAO.getLocationWiseStateMeetingInvitees(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,null);
			List<Object[]> attendees = partyMeetingAttendanceDAO.getLocationWiseStateMeetingAttendees(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,null,null,null);
			List<Object[]> images = partyMeetingDocumentDAO.getLocationWiseStateMeetingImages(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,null);
			Map<Long,Map<Long,PartyMeetingDataVO>> meetingTypeMap = new HashMap<Long,Map<Long,PartyMeetingDataVO>>();
			Map<Long,PartyMeetingDataVO> totalCntMap = new HashMap<Long,PartyMeetingDataVO>();
			if(commonMethodsUtilService.isListOrSetValid(totalList)){
				for (Object[] param : totalList) {
					returnVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					returnVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					PartyMeetingDataVO totalMeetings = totalCntMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
					if(totalMeetings == null){
						totalMeetings = new PartyMeetingDataVO();
						totalMeetings.setTotalMeetings(commonMethodsUtilService.getLongValueForObject(param[4]));
						totalCntMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), totalMeetings);
					}
				}
			}
			
			setMeetingDetails(meetingTypeMap,invitees,"invitees");
			setMeetingDetails(meetingTypeMap,attendees,"attendees");
			setMeetingDetails(meetingTypeMap,images,"images");
			
			if(commonMethodsUtilService.isMapValid(meetingTypeMap)){
				for (Entry<Long, Map<Long, PartyMeetingDataVO>> entry :meetingTypeMap.entrySet()) {
					Map<Long,PartyMeetingDataVO> cadresmap = entry.getValue();
					PartyMeetingDataVO  meetingTypeVO = totalCntMap.get(entry.getKey());
					if(commonMethodsUtilService.isMapValid(cadresmap)){
						for (Entry<Long, PartyMeetingDataVO> candidates :cadresmap.entrySet()) {
							PartyMeetingDataVO vo =candidates.getValue();
							meetingTypeVO.setPartyMeetingId(vo.getPartyMeetingId());//partyMeetingMaintypeid
							meetingTypeVO.setPartyMeetingName(vo.getPartyMeetingName());//partyMeetingMainTypeName
							meetingTypeVO.setId(vo.getId());//partymeetingTypeId
							meetingTypeVO.setName(vo.getName());//patyMeetingType
							Set<Long> inviteeIds = vo.getInviteeIds();
							if(commonMethodsUtilService.isListOrSetValid(inviteeIds))
								meetingTypeVO.setInvitedCount(meetingTypeVO.getInvitedCount()+inviteeIds.size());
							Set<Long> attendedIds = vo.getAttendedIds();
								if(commonMethodsUtilService.isListOrSetValid(attendedIds)){
									meetingTypeVO.setAttendedCount(meetingTypeVO.getAttendedCount()+attendedIds.size());
									for (Long tdpCadreId : attendedIds) {
										if(inviteeIds.contains(tdpCadreId)){
											meetingTypeVO.getInviteeAttendedIdsList().add(tdpCadreId);
											//sessionVO.setRecentInviteeAttended(Long.valueOf(String.valueOf(sessionVO.getInviteeAttendedIdsList().size())));
											meetingTypeVO.setInviteeAttendedCount(Long.valueOf(String.valueOf(meetingTypeVO.getInviteeAttendedIdsList().size())));
										}
									}
								}
								meetingTypeVO.setImagesCnt(meetingTypeVO.getImagesCnt()+vo.getImagesCnt());
						}
					}
					meetingTypeVO.setAbsentCount(Math.abs(meetingTypeVO.getInvitedCount().longValue()-meetingTypeVO.getInviteeAttendedCount().longValue()));
					meetingTypeVO.setNonInviteesCount(Math.abs(meetingTypeVO.getAttendedCount().longValue()-meetingTypeVO.getInviteeAttendedCount().longValue()));
				}
			}
			if(commonMethodsUtilService.isMapValid(totalCntMap))
			returnVO.getLevelList().addAll(totalCntMap.values());
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised at getLocationWiseStateMeetings", e);
		}
		return returnVO;
	}
	
	/**
	* @author Hymavathi G 
	* @Description :used to buid vo structure for attendees , invitees, images count of meetings
	*  @since 12-oct-2017
	*  @return :void
	*/
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
						partyMeetingVO.setImagesCnt(partyMeetingVO.getImagesCnt().longValue()+1l);
					}else{
						if(type.equalsIgnoreCase("attendees")){
							partyMeetingVO.getAttendedIds().add(commonMethodsUtilService.getLongValueForObject(param[4]));
						}else if(type.equalsIgnoreCase("invitees")){
							partyMeetingVO.getInviteeIds().add(commonMethodsUtilService.getLongValueForObject(param[4]));
						}
						
					}
					 
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised at setMeetingDetails", e);
		}
	}
	
	/**
	* @author Hymavathi G 
	* @Description :Used to get Location wise Special meeting invited,attended,images,total party meetings count
	*  @since 12-oct-2017
	*  @return :PartyMeetingDataVO
	*/
	public PartyMeetingDataVO getLocationWiseSpecialMeetings(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr,Long partyMeetingMainTypeId){
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
			
			List<Object[]> totalList = partyMeetingDAO.getLocationWiseStateMeetings(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,"",null);
			List<Object[]> invitees = partyMeetingInviteeDAO.getLocationWiseStateMeetingInvitees(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,null);
			List<Object[]> attendees = partyMeetingAttendanceDAO.getLocationWiseStateMeetingAttendees(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,null,null,null);
			List<Object[]> images = partyMeetingDocumentDAO.getLocationWiseStateMeetingImages(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,null);
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
			setMeetingDetails(meetingTypeMap,attendees,"attendees");
			setMeetingDetails(meetingTypeMap,images,"images");
			
			//if(partyMeetingMainTypeId != null && partyMeetingMainTypeId.longValue() == 3l){
				 List<Object[]> recentTimes = partyMeetingDAO.getLocationWiseStateMeetings(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,"recentTime",null);
			
				 if(commonMethodsUtilService.isListOrSetValid(recentTimes)){
					 for (Object[] param : recentTimes) {
						 PartyMeetingDataVO  meetingTypeVO = totalCntMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
						 String recentDate = commonMethodsUtilService.getStringValueForObject(param[4]);
						 if(recentDate != null && !recentDate.isEmpty() ){
								Date date = new SimpleDateFormat("yyyy-MM-dd").parse(recentDate);
								String format = new SimpleDateFormat("MMM dd").format(date);
								meetingTypeVO.setConductedDate(format+","+recentDate.substring(0,4));
						}
						 meetingTypeVO.setMeetingId(commonMethodsUtilService.getLongValueForObject(param[5]));
					}
				 }
			//}
				 
				 calculateLateTime(totalCntMap,attendees,meetingTypeMap);
			if(commonMethodsUtilService.isMapValid(meetingTypeMap)){
				for (Entry<Long, Map<Long, PartyMeetingDataVO>> entry :meetingTypeMap.entrySet()) {
					Map<Long,PartyMeetingDataVO> cadresmap = entry.getValue();
					PartyMeetingDataVO  meetingTypeVO = totalCntMap.get(entry.getKey());
					if(commonMethodsUtilService.isMapValid(cadresmap)){
						for (Entry<Long, PartyMeetingDataVO> candidates :cadresmap.entrySet()) {
							PartyMeetingDataVO vo =candidates.getValue();
							Long partyMeetingId = candidates.getKey();
							meetingTypeVO.setPartyMeetingId(vo.getPartyMeetingId());//partyMeetingMaintypeid
							meetingTypeVO.setPartyMeetingName(vo.getPartyMeetingName());//partyMeetingMainTypeName
							meetingTypeVO.setId(vo.getId());//partymeetingTypeId
							meetingTypeVO.setName(vo.getName());//patyMeetingType
							meetingTypeVO.getLevelList().addAll(vo.getLevelList());
							Set<Long> inviteeIds = vo.getInviteeIds();
							
							Set<Long> attendedIds = vo.getAttendedIds();
								if(commonMethodsUtilService.isListOrSetValid(attendedIds)){
									meetingTypeVO.setAttendedCount(meetingTypeVO.getAttendedCount()+attendedIds.size());
									for (Long tdpCadreId : attendedIds) {
										if(inviteeIds.contains(tdpCadreId)){
											meetingTypeVO.getInviteeAttendedIdsList().add(tdpCadreId);
											meetingTypeVO.setInviteeAttendedCount(Long.valueOf(String.valueOf(meetingTypeVO.getInviteeAttendedIdsList().size())));
											//meetingTypeVO.setInviteeAttendedCount(meetingTypeVO.getInviteeAttendedCount().longValue()+1l);
										}
									}
								}
								meetingTypeVO.setImagesCnt(meetingTypeVO.getImagesCnt()+vo.getImagesCnt());
								
								//session Details for each partyMeetingType Vo
								if(commonMethodsUtilService.isListOrSetValid(inviteeIds)){
									meetingTypeVO.setInvitedCount(meetingTypeVO.getInvitedCount()+inviteeIds.size());
									if(partyMeetingId.longValue() == meetingTypeVO.getMeetingId().longValue()){
										meetingTypeVO.setRecentMeetingInviteesCnt(Long.valueOf(inviteeIds.size()));
										meetingTypeVO.setRecentImagesCnt(vo.getImagesCnt());
									}
								}
								if(commonMethodsUtilService.isListOrSetValid(meetingTypeVO.getLevelList()) && partyMeetingId.longValue() == meetingTypeVO.getMeetingId().longValue()){
									for (PartyMeetingDataVO sessionVO : meetingTypeVO.getLevelList()) {
										Set<Long> sessionAttendedIds = sessionVO.getRecentAttendedIds();
										if(commonMethodsUtilService.isListOrSetValid(sessionAttendedIds)){
											sessionVO.setRecentAttended(Long.valueOf(sessionAttendedIds.size()));
											for (Long tdpCadreId : sessionAttendedIds) {
												if(inviteeIds.contains(tdpCadreId)){
													sessionVO.getInviteeAttendedIdsList().add(tdpCadreId);
													sessionVO.setRecentInviteeAttended(Long.valueOf(String.valueOf(sessionVO.getInviteeAttendedIdsList().size())));
													//sessionVO.setRecentInviteeAttended(sessionVO.getRecentInviteeAttended().longValue()+1l); 
													sessionVO.setLatePerc(calculatePercantage(sessionVO.getLateCount(),meetingTypeVO.getRecentMeetingInviteesCnt()).toString());
												}
											}
											
											sessionVO.setRecentAbcent(Math.abs(meetingTypeVO.getRecentMeetingInviteesCnt().longValue()-sessionVO.getRecentInviteeAttended().longValue()));
											sessionVO.setRecentNonInvitee(Math.abs(sessionVO.getRecentAttended().longValue()-sessionVO.getRecentInviteeAttended().longValue()));
											sessionVO.setAbcentPerc(calculatePercantage(sessionVO.getRecentAbcent(), meetingTypeVO.getRecentMeetingInviteesCnt()).toString());
											sessionVO.setAttendedPerc(calculatePercantage(sessionVO.getRecentInviteeAttended(),meetingTypeVO.getRecentMeetingInviteesCnt()).toString());
											//sessionVO.setLatePerc(calculatePercantage(sessionVO.getRecentLate(),meetingTypeVO.getRecentMeetingInviteesCnt()).toString());
										}
									}
								}
						}
					}
					meetingTypeVO.setAbsentCount(Math.abs(meetingTypeVO.getInvitedCount().longValue()-meetingTypeVO.getInviteeAttendedCount().longValue()));
					meetingTypeVO.setNonInviteesCount(Math.abs(meetingTypeVO.getAttendedCount().longValue()-meetingTypeVO.getInviteeAttendedCount().longValue()));
				}
			}
			
			
			if(commonMethodsUtilService.isMapValid(totalCntMap))
			returnVO.getLevelList().addAll(totalCntMap.values());
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised at getLocationWiseSpecialMeetings", e);
		}
		return returnVO;
	}
	
	/**
	* @author Hymavathi G 
	* @Description :Used to calculate late attended time
	*  @since 13-oct-2017
	*  @return :void
	*/
	public void calculateLateTime(Map<Long,PartyMeetingDataVO> totalCntMap,List<Object[]> attendeesList,Map<Long,Map<Long,PartyMeetingDataVO>> meetingTypeMap){
		
		try{
			if(commonMethodsUtilService.isListOrSetValid(attendeesList)){
				for (Object[] param : attendeesList) {
						Map<Long,PartyMeetingDataVO> cadresmap = meetingTypeMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
						PartyMeetingDataVO  meetingTypeVO = totalCntMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
						if(commonMethodsUtilService.isMapValid(cadresmap)){
							for (Entry<Long, PartyMeetingDataVO> candidates :cadresmap.entrySet()) {
								PartyMeetingDataVO vo =candidates.getValue();
								Long partyMeetingId = candidates.getKey();
								if(partyMeetingId.longValue() == meetingTypeVO.getMeetingId().longValue()){
									Set<Long> inviteeIds = vo.getInviteeIds();
									PartyMeetingDataVO sessionVO = getMatchedVO(vo.getLevelList(),commonMethodsUtilService.getLongValueForObject(param[6]));
									if(sessionVO == null){
										sessionVO=new PartyMeetingDataVO();
										sessionVO.setId(commonMethodsUtilService.getLongValueForObject(param[6]));
										sessionVO.setName(commonMethodsUtilService.getStringValueForObject(param[7]));
											//vo.setAtrStatus(commonMethodsUtilService.getStringValueForObject(param[8]));
											//vo.setMonth(commonMethodsUtilService.getStringValueForObject(param[9]));
										vo.getLevelList().add(sessionVO);
										}
										sessionVO.getRecentAttendedIds().add(commonMethodsUtilService.getLongValueForObject(param[4]));
										vo.getAttendedIds().add(commonMethodsUtilService.getLongValueForObject(param[4]));
										if(inviteeIds.contains(commonMethodsUtilService.getLongValueForObject(param[4]))){
											SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
											SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									        Date lateTime = sdf1.parse(commonMethodsUtilService.getStringValueForObject(param[8]).substring(0, 10)+" "+commonMethodsUtilService.getStringValueForObject(param[9]));
									        Date attendedTme = sdf2.parse(commonMethodsUtilService.getStringValueForObject(param[8]));
									        long attendedMilliSec = attendedTme.getTime();
									        long lateMilliSec = lateTime.getTime();
											if(attendedMilliSec>=lateMilliSec){
												sessionVO.getInviteeAttendedIdsList().add(commonMethodsUtilService.getLongValueForObject(param[4]));
												//sessionVO.setRecentInviteeAttended(Long.valueOf(String.valueOf(sessionVO.getInviteeAttendedIdsList().size())));
												sessionVO.setRecentLate(Long.valueOf(String.valueOf(sessionVO.getInviteeAttendedIdsList().size())));
											}
										}
									}
								}
							}
						}
			      }
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised at calculateLateTime", e);
		}
	}
	
	public Double calculatePercantage(Long subCount,Long totalCount){
		Double d=0.0d;
		if(subCount.longValue()>0l && totalCount.longValue()==0l)
			LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

		if(totalCount.longValue() > 0l){
			d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
		}
		return d;
	}
	
	public PartyMeetingDataVO getMatchedVO(List<PartyMeetingDataVO> voList, Long id) {
		if (voList != null && voList.size() > 0) {
			for (PartyMeetingDataVO vo : voList) {
				if (id != null && vo.getId().longValue() == id.longValue())
					return vo;
			}
		}
		return null;
	}
	
	/**
	* @author Nandhini k 
	* @param levelId,List<Long> levelVals,fromDateStr,toDateStr,meetingTypeId
	* @Description :this service used for get the location wise MeetingStatus
	*  @since 11-oct-2017
	*  @return :List<PartyMeetingDataVO> 
	*/
	public List<PartyMeetingDataVO> getLocationWiseMeetingStatusDetails(Long searchLevelId,List<Long> levelValues,String startDateStr,String endDateStr,Long meetingTypeId,Long partyMeetinLevelId){
		List<PartyMeetingDataVO> locationList = new ArrayList<PartyMeetingDataVO>(0);
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			// Here converting stirng to date formatte
			if(startDateStr != null && startDateStr.trim().length() > 0 && endDateStr != null && endDateStr.trim().length() > 0){
				fromDate = sdf.parse(startDateStr);
				toDate = sdf.parse(endDateStr);
			}
			List<String> btnMonths = dateUtilService.getMonthsBetweenDatesStringFormat(fromDate, toDate);
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("MMM yyyy");
			SimpleDateFormat sdf3 = new SimpleDateFormat("MMM dd");
			SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");
			
			//SimpleDateFormat sdf4 = new SimpleDateFormat("MMM dd");
			
			List<Object[]> list = null;
			Map<Long,PartyMeetingDataVO> locationMap = new HashMap<Long, PartyMeetingDataVO>(0);
			Map<String,PartyMeetingDataVO> dateMap = new HashMap<String, PartyMeetingDataVO>(0);
			
			if(searchLevelId != null && searchLevelId.longValue() >0l && searchLevelId.longValue() == 2L){
				if(partyMeetinLevelId != null && partyMeetinLevelId.longValue() >0L && partyMeetinLevelId.longValue() == 2L){
					list = districtDAO.getDistrictIdsByState(levelValues);
				}
			}else if(searchLevelId != null && searchLevelId.longValue() >0l && searchLevelId.longValue() == 3L){
				if(partyMeetinLevelId != null && partyMeetinLevelId.longValue() >0L && partyMeetinLevelId.longValue() == 2L){
					list = districtDAO.getDistrictDetailsByDistrictIds(levelValues);
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] param : list) {
					PartyMeetingDataVO locationVO = new PartyMeetingDataVO();
						locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						locationVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						locationVO.setDatesList(setBetweenMonthsVO(btnMonths));
						locationMap.put(locationVO.getId(),locationVO);	
					}
				}
				
			List<Object[]> statusList = partyMeetingStatusDAO.getMeetingStatusByLocation(searchLevelId, levelValues, fromDate, toDate, meetingTypeId, partyMeetinLevelId);
				if(commonMethodsUtilService.isListOrSetValid(statusList)){
					for (Object[] param : statusList) {
						Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
						PartyMeetingDataVO finalDataVO = locationMap.get(locationId);
						if(finalDataVO != null){
							PartyMeetingDataVO monthVO = getMatchedVOByMonth(finalDataVO.getDatesList(),sdf4.format(sdf1.parse(commonMethodsUtilService.getStringValueForObject(param[2]))));
							if(monthVO != null){
								monthVO.setMomStatus(commonMethodsUtilService.getStringValueForObject(param[1]));
								monthVO.setConductedDate(commonMethodsUtilService.getStringValueForObject(param[2]));
								if(monthVO.getConductedDate() != null){
									Date dated = sdf1.parse(monthVO.getConductedDate());
									monthVO.setConductedDate(sdf3.format(dated));
								}
								if(monthVO.getMonth() != null){
									Date dated = sdf4.parse(monthVO.getMonth());
									monthVO.setMonth(sdf2.format(dated));
								}
							}
						}
					}
				}
				
				if(commonMethodsUtilService.isMapValid(locationMap)){
					locationList = new ArrayList<PartyMeetingDataVO>(locationMap.values());
				}
				
				//Set OverAll Status Counts
				if(commonMethodsUtilService.isListOrSetValid(btnMonths)){
					for (String date : btnMonths) {
						PartyMeetingDataVO locationVO = new PartyMeetingDataVO();
						Date date1 = sdf1.parse(date);
						locationVO.setMonth(sdf2.format(date1));
						dateMap.put(locationVO.getMonth(),locationVO);	
					}
				}
					
				if(commonMethodsUtilService.isListOrSetValid(statusList)){
					for (Object[] param : statusList) {
						PartyMeetingDataVO countVO = dateMap.get(sdf2.format(sdf1.parse(commonMethodsUtilService.getStringValueForObject(param[2]))));
						if(countVO != null){
							if(commonMethodsUtilService.getStringValueForObject(param[1]).trim().equalsIgnoreCase("Y"))
								countVO.setYesCount(countVO.getYesCount()+1);
							else if(commonMethodsUtilService.getStringValueForObject(param[1]).trim().equalsIgnoreCase("N"))
								countVO.setNoCount(countVO.getNoCount()+1);
							else if(commonMethodsUtilService.getStringValueForObject(param[1]).trim().equalsIgnoreCase("M"))
								countVO.setMaybeCount(countVO.getMaybeCount()+1);
							else if(commonMethodsUtilService.getStringValueForObject(param[1]).trim().equalsIgnoreCase("NU"))
								countVO.setNotUpdatedCount(countVO.getNotUpdatedCount()+1);
						}
						
					}
					
				}
				
				if(dateMap != null){
					locationList.get(0).getLevelList().addAll(dateMap.values());
				}
				
		} catch (Exception e) {
			Log.error("Exception raised in getLocationWiseMeetingStatusDetails method of LocationDashboardService"+e);
		}
		return locationList;
	}
	
	public List<PartyMeetingDataVO> setBetweenMonthsVO(List<String> monthList){
		List<PartyMeetingDataVO> returnList = new ArrayList<PartyMeetingDataVO>(0);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
			if(monthList != null && !monthList.isEmpty()){
				for (String monthStr : monthList) {
					PartyMeetingDataVO vo = new PartyMeetingDataVO();
					Date date = sdf.parse(monthStr);
					vo.setMonth(sdf1.format(date));
					returnList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in setBetweenDatesVO in DoorToDoorCampaignDashboardService", e);
		}
		return returnList;
	}
	
	public PartyMeetingDataVO getMatchedVOByMonth(List<PartyMeetingDataVO> monthsList,String month){
		try {
			if(commonMethodsUtilService.isListOrSetValid(monthsList)){
				for (PartyMeetingDataVO meetingDataVO : monthsList) {
					if(meetingDataVO.getMonth().equals(month)){
						return meetingDataVO;
					}
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised in getMatchedVOByDate in DoorToDoorCampaignDashboardService", e);
		}
		return null;
	}
	
	/**
	* @author Hymavathi G 
	* @Description :Used to get Location wise Special meeting invited,attended,images,total party meetings wise count 
	*  @since 25-oct-2017
	*  @return :PartyMeetingDataVO
	*/
	public PartyMeetingDataVO getLocationWiseSpecialMeetingsMeetingsExpanction(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr,Long partyMeetingMainTypeId,Long partyMeetingTypeId){
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
			
			List<Object[]> totalList = partyMeetingDAO.getLocationWiseStateMeetings(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,"",partyMeetingTypeId);
			List<Object[]> invitees = partyMeetingInviteeDAO.getLocationWiseStateMeetingInvitees(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,partyMeetingTypeId);
			List<Object[]> attendees = partyMeetingAttendanceDAO.getLocationWiseStateMeetingAttendees(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,partyMeetingTypeId,null,null);
			List<Object[]> images = partyMeetingDocumentDAO.getLocationWiseStateMeetingImages(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,partyMeetingTypeId);
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
			setMeetingDetails(meetingTypeMap,attendees,"attendees");
			//setMeetingDetails(meetingTypeMap,images,"images");
			
			//if(partyMeetingMainTypeId != null && partyMeetingMainTypeId.longValue() == 3l){
				 List<Object[]> recentTimes = partyMeetingDAO.getLocationWiseStateMeetings(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,"meetings",partyMeetingTypeId);
			
				 if(commonMethodsUtilService.isListOrSetValid(recentTimes)){
					 for (Object[] param : recentTimes) {
						 PartyMeetingDataVO  meetingTypeVO = totalCntMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
						 PartyMeetingDataVO partyMeetingVO = getMatchedVO(meetingTypeVO.getLevelList(),commonMethodsUtilService.getLongValueForObject(param[5]));
						 if(partyMeetingVO == null){
							 partyMeetingVO = new PartyMeetingDataVO();
								 String meetingDate = commonMethodsUtilService.getStringValueForObject(param[4]);
								 if(meetingDate != null && !meetingDate.isEmpty() ){
										Date date = new SimpleDateFormat("yyyy-MM-dd").parse(meetingDate);
										String format = new SimpleDateFormat("MMM dd").format(date);
										partyMeetingVO.setConductedDate(format+","+meetingDate.substring(0,4));
								}
								 partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(param[5]));
								 partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(param[6]));
								 meetingTypeVO.getLevelList().add(partyMeetingVO);
						 }
					}
				 }
			//}
				 
				 calculateLateTimeForMeetings(totalCntMap,attendees,meetingTypeMap,"attendee");
				 calculateLateTimeForMeetings(totalCntMap,images,meetingTypeMap,"images");
			if(commonMethodsUtilService.isMapValid(meetingTypeMap)){
				for (Entry<Long, Map<Long, PartyMeetingDataVO>> entry :meetingTypeMap.entrySet()) {
					Map<Long,PartyMeetingDataVO> cadresmap = entry.getValue();
					PartyMeetingDataVO  meetingTypeVO = totalCntMap.get(entry.getKey());
					if(commonMethodsUtilService.isMapValid(cadresmap)){
						for (Entry<Long, PartyMeetingDataVO> candidates :cadresmap.entrySet()) {
							PartyMeetingDataVO vo =candidates.getValue();
							Long partyMeetingId = candidates.getKey();
							//meetingTypeVO.setPartyMeetingId(vo.getPartyMeetingId());//partyMeetingMaintypeid
							//meetingTypeVO.setPartyMeetingName(vo.getPartyMeetingName());//partyMeetingMainTypeName
							meetingTypeVO.setId(vo.getId());//partymeetingTypeId
							meetingTypeVO.setName(vo.getName());//patyMeetingType
							//meetingTypeVO.getLevelList().addAll(vo.getLevelList());
							Set<Long> inviteeIds = vo.getInviteeIds();
							
							Set<Long> attendedIds = vo.getAttendedIds();
								if(commonMethodsUtilService.isListOrSetValid(attendedIds)){
									meetingTypeVO.setAttendedCount(meetingTypeVO.getAttendedCount()+attendedIds.size());
									for (Long tdpCadreId : attendedIds) {
										if(inviteeIds.contains(tdpCadreId)){
											meetingTypeVO.getInviteeAttendedIdsList().add(tdpCadreId);
											meetingTypeVO.setInviteeAttendedCount(Long.valueOf(String.valueOf(meetingTypeVO.getInviteeAttendedIdsList().size())));
										}
									}
								}
								meetingTypeVO.setImagesCnt(meetingTypeVO.getImagesCnt()+vo.getImagesCnt());
								
								//session Details for each partyMeetingType Vo
								if(commonMethodsUtilService.isListOrSetValid(inviteeIds)){
									meetingTypeVO.setInvitedCount(meetingTypeVO.getInvitedCount()+inviteeIds.size());
									PartyMeetingDataVO partyMeetingVO = getMatchedVO(meetingTypeVO.getLevelList(),partyMeetingId.longValue());
									if(partyMeetingVO != null){
										partyMeetingVO.setRecentMeetingInviteesCnt(Long.valueOf(inviteeIds.size()));
										partyMeetingVO.setRecentImagesCnt(vo.getImagesCnt());
										if(commonMethodsUtilService.isListOrSetValid(partyMeetingVO.getDatesList())){
										for (PartyMeetingDataVO sessionVO : partyMeetingVO.getDatesList()) {
											Set<Long> sessionAttendedIds = sessionVO.getRecentAttendedIds();
											if(commonMethodsUtilService.isListOrSetValid(sessionAttendedIds)){
												sessionVO.setRecentAttended(Long.valueOf(sessionAttendedIds.size()));
												for (Long tdpCadreId : sessionAttendedIds) {
													if(inviteeIds.contains(tdpCadreId)){
														sessionVO.getInviteeAttendedIdsList().add(tdpCadreId);
														sessionVO.setRecentInviteeAttended(Long.valueOf(String.valueOf(sessionVO.getInviteeAttendedIdsList().size())));
														sessionVO.setLatePerc(calculatePercantage(sessionVO.getLateCount(),meetingTypeVO.getRecentMeetingInviteesCnt()).toString());
													}
												}
												
												sessionVO.setRecentAbcent(Math.abs(partyMeetingVO.getRecentMeetingInviteesCnt().longValue()-sessionVO.getRecentInviteeAttended().longValue()));
												//if(sessionVO.getRecentAttended().longValue() >partyMeetingVO.getRecentMeetingInviteesCnt().longValue()){
													sessionVO.setRecentNonInvitee(Math.abs(sessionVO.getRecentAttended().longValue()-sessionVO.getRecentInviteeAttended().longValue()));
												//}
												sessionVO.setAbcentPerc(calculatePercantage(sessionVO.getRecentAbcent(), partyMeetingVO.getRecentMeetingInviteesCnt()).toString());
												sessionVO.setAttendedPerc(calculatePercantage(sessionVO.getRecentInviteeAttended(),partyMeetingVO.getRecentMeetingInviteesCnt()).toString());
											}
										}
										}
									}
								}
						}
					}
					meetingTypeVO.setAbsentCount(Math.abs(meetingTypeVO.getInvitedCount().longValue()-meetingTypeVO.getInviteeAttendedCount().longValue()));
					if(meetingTypeVO.getAttendedCount().longValue() >meetingTypeVO.getInviteeAttendedCount().longValue())
					meetingTypeVO.setNonInviteesCount(Math.abs(meetingTypeVO.getAttendedCount().longValue()-meetingTypeVO.getInviteeAttendedCount().longValue()));
				}
			}
			
			
			if(commonMethodsUtilService.isMapValid(totalCntMap))
			returnVO.getLevelList().addAll(totalCntMap.values());
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception raised at getLocationWiseSpecialMeetingsMeetingsExpanction", e);
		}
		return returnVO;
	}
	
	/**
	* @author Hymavathi G 
	* @Description :Used to calculate late attended time for each session of party meeting
	*  @since 25-oct-2017
	*  @return :void
	*/
	public void calculateLateTimeForMeetings(Map<Long,PartyMeetingDataVO> totalCntMap,List<Object[]> attendeesList,Map<Long,Map<Long,PartyMeetingDataVO>> meetingTypeMap,String type){
		
		try{
			if(commonMethodsUtilService.isListOrSetValid(attendeesList)){
				for (Object[] param : attendeesList) {
						Map<Long,PartyMeetingDataVO> cadresmap = meetingTypeMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
						PartyMeetingDataVO  meetingTypeVO = totalCntMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
						if(commonMethodsUtilService.isMapValid(cadresmap)){
							for (Entry<Long, PartyMeetingDataVO> candidates :cadresmap.entrySet()) {
								PartyMeetingDataVO vo =candidates.getValue();
								//Long partyMeetingId = candidates.getKey();
								PartyMeetingDataVO partyMeetingVO = getMatchedVO(meetingTypeVO.getLevelList(),commonMethodsUtilService.getLongValueForObject(param[5]));
								if(partyMeetingVO != null){
									Set<Long> inviteeIds = vo.getInviteeIds();
									PartyMeetingDataVO sessionVO = getMatchedVO(partyMeetingVO.getDatesList(),commonMethodsUtilService.getLongValueForObject(param[6]));
									if(sessionVO == null){
											sessionVO=new PartyMeetingDataVO();
											sessionVO.setId(commonMethodsUtilService.getLongValueForObject(param[6]));
											sessionVO.setName(commonMethodsUtilService.getStringValueForObject(param[7]));
											partyMeetingVO.getDatesList().add(sessionVO);
										}
									if(type != null && type.equalsIgnoreCase("attendee")){
										sessionVO.getRecentAttendedIds().add(commonMethodsUtilService.getLongValueForObject(param[4]));
										vo.getAttendedIds().add(commonMethodsUtilService.getLongValueForObject(param[4]));
										if(inviteeIds.contains(commonMethodsUtilService.getLongValueForObject(param[4]))){
											SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
											SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									        Date lateTime = sdf1.parse(commonMethodsUtilService.getStringValueForObject(param[8]).substring(0, 10)+" "+commonMethodsUtilService.getStringValueForObject(param[9]));
									        Date attendedTme = sdf2.parse(commonMethodsUtilService.getStringValueForObject(param[8]));
									        long attendedMilliSec = attendedTme.getTime();
									        long lateMilliSec = lateTime.getTime();
											if(attendedMilliSec>lateMilliSec){
												sessionVO.getAttendedIds().add(commonMethodsUtilService.getLongValueForObject(param[4]));
												sessionVO.setRecentLate(Long.valueOf(String.valueOf(sessionVO.getAttendedIds().size())));
											}
										}
									}else if(type != null && type.equalsIgnoreCase("images")){
										sessionVO.setRecentImagesCnt(sessionVO.getImagesCnt()+1);
									}
								  }
								}
							}
						}
			      }
			
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised at calculateLateTimeForMeetings", e);
		}
	}
	
	/**
	* @author Hymavathi G 
	* @Description :Used to show Location wise meeting invitee members details
	*  @since 26-oct-2017
	*  @return :void
	*/
	public List<IdNameVO> getLocationWiseMeetingInviteeMembers(Long locationTypeId, List<Long> locationValues,String fromDateStr,String toDateStr,Long partyMeetingMainTypeId,Long partyMeetingTypeId,Long partyMeetingId,String pastMeetings){
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
			//CoreDashboardPartyMeetingService coreDashboardPartyMeetingService = new CoreDashboardPartyMeetingService();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			Map<Long,IdNameVO> inviteesMap = new HashMap<Long,IdNameVO>();
			List<Object[]> invitees = partyMeetingInviteeDAO.getLocationWiseMeetingInviteeMembers(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,partyMeetingTypeId,partyMeetingId,null);
			if(commonMethodsUtilService.isListOrSetValid(invitees)){
				for(Object[] param :invitees){
					IdNameVO inviteeMem = inviteesMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(inviteeMem == null ){
						inviteeMem = new IdNameVO();
						
						inviteeMem.setId(commonMethodsUtilService.getLongValueForObject(param[0]));//tdpCadreId
						inviteeMem.setName(commonMethodsUtilService.getStringValueForObject(param[1]));//cadreName
						inviteeMem.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[5]));//mobileNo
						inviteeMem.setIsInvitee("false");
						if(param[2] != null || param[3]!=null){
							if(param[2] != null){
								String sts = param[2].toString();
								coreDashboardPartyMeetingService.setStatusToVO( sts , inviteeMem);
							}
							if(param[3] != null){
								String sts = (param[4] != null ? param[4].toString() : "")+" "+(param[3] != null ? param[3].toString() : "");
								coreDashboardPartyMeetingService.setStatusToVO( sts , inviteeMem);
							}
						}
						inviteeMem.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[6]));//districtName
						inviteesMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), inviteeMem);
					}
				}
			}
			List<IdNameVO> partyMeetings = new ArrayList<IdNameVO>();
			List<Object[]> attendees = partyMeetingAttendanceDAO.getLocationWiseStateMeetingAttendees(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,partyMeetingTypeId,null,inviteesMap.keySet());
			if(commonMethodsUtilService.isListOrSetValid(attendees)){
				for(Object[] param :attendees){
					IdNameVO partyMeetingVO = getMatchedVOInList(partyMeetings,commonMethodsUtilService.getLongValueForObject(param[5]));
					if(partyMeetingVO == null){
						partyMeetingVO = new IdNameVO();
						partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(param[5]));
						partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(param[10]));
						partyMeetingVO.setDateStr(commonMethodsUtilService.getStringValueForObject(param[11]));
						partyMeetings.add(partyMeetingVO);
					}
					IdNameVO sessionVO = getMatchedVOInList(partyMeetingVO.getIdnameList(),commonMethodsUtilService.getLongValueForObject(param[6]));
					if(sessionVO == null){
						sessionVO = new IdNameVO();
						sessionVO.setId(commonMethodsUtilService.getLongValueForObject(param[6]));
						sessionVO.setName(commonMethodsUtilService.getStringValueForObject(param[7]));
						sessionVO.setDateStr(commonMethodsUtilService.getStringValueForObject(param[9]));
						sessionVO.setStatus("abscent");
						partyMeetingVO.getIdnameList().add(sessionVO);
					}
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(attendees)){
				for(Object[] param :attendees){
					IdNameVO inviteeMem = inviteesMap.get(commonMethodsUtilService.getLongValueForObject(param[4]));
					if(inviteeMem != null ){
						inviteeMem.setSubList1(partyMeetings);
						IdNameVO partyMeetingVO = getMatchedVOInList(inviteeMem.getSubList1(),commonMethodsUtilService.getLongValueForObject(param[5]));
						if(partyMeetingVO != null){
							/*partyMeetingVO = new IdNameVO();
							partyMeetingVO.setId(commonMethodsUtilService.getLongValueForObject(param[5]));
							partyMeetingVO.setName(commonMethodsUtilService.getStringValueForObject(param[9]));
							partyMeetingVO.setDateStr(commonMethodsUtilService.getStringValueForObject(param[10]));
							inviteeMem.getSubList1().add(partyMeetingVO);*/
						
						
						IdNameVO sessionVO = getMatchedVOInList(partyMeetingVO.getIdnameList(),commonMethodsUtilService.getLongValueForObject(param[6]));
						if(sessionVO != null){
							/*sessionVO = new IdNameVO();
							sessionVO.setId(commonMethodsUtilService.getLongValueForObject(param[6]));
							sessionVO.setName(commonMethodsUtilService.getStringValueForObject(param[7]));*/
							sessionVO.setDateOfbirth(commonMethodsUtilService.getStringValueForObject(param[8]));
							//sessionVO.setDateStr(commonMethodsUtilService.getStringValueForObject(param[9]));
							SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
							SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					        Date lateTime = sdf1.parse(commonMethodsUtilService.getStringValueForObject(param[8]).substring(0, 10)+" "+commonMethodsUtilService.getStringValueForObject(param[9]));
					        Date attendedTme = sdf2.parse(commonMethodsUtilService.getStringValueForObject(param[8]));
					        long attendedMilliSec = attendedTme.getTime();
					        long lateMilliSec = lateTime.getTime();
							if(attendedMilliSec>=lateMilliSec){
								sessionVO.setStatus("late");
							}else if(attendedMilliSec<lateMilliSec){
								sessionVO.setStatus("intime");
							}else{
								sessionVO.setStatus("abscent");
							}
							//partyMeetingVO.getIdnameList().add(sessionVO);
						}
						}
						
						inviteeMem.getNonInviteeIds().add(commonMethodsUtilService.getLongValueForObject(param[5]));//present/attended partyMeetings cadre ids
						inviteeMem.setActualCount((long) inviteeMem.getNonInviteeIds().size());//present/attended partyMeetings Count
						
					}
				}
			}
			
			List<Object[]> invitations = partyMeetingInviteeDAO.getLocationWiseMeetingInviteeMembers(locationValues,locationTypeId,startDate,endDate,partyMeetingMainTypeId,partyMeetingTypeId,null,inviteesMap.keySet());
			if(commonMethodsUtilService.isListOrSetValid(invitees)){
				for(Object[] param :invitees){
					IdNameVO inviteeMem = inviteesMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(inviteeMem != null ){
						inviteeMem.getInviteeIds().add(commonMethodsUtilService.getLongValueForObject(param[9]));//invited partyMeetings cadre ids
						inviteeMem.setInviteeAttnd((long) inviteeMem.getInviteeIds().size());//invited partyMeetings Count
						IdNameVO partyMeetingVO = getMatchedVOInList(inviteeMem.getSubList1(),commonMethodsUtilService.getLongValueForObject(param[9]));
						if(partyMeetingVO != null){
							partyMeetingVO.setRemark(commonMethodsUtilService.getStringValueForObject(param[8]));
						}
					}
				}
			}
			
			if(commonMethodsUtilService.isMapValid(inviteesMap)){
				returnList.addAll(inviteesMap.values());
			}
			Map<String,IdNameVO>  designationsMap = new HashMap<String,IdNameVO>();
			returnList = coreDashboardPartyMeetingService.getOnlyNonInvitteeAttendedMembers(returnList,designationsMap);
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception raised at getLocationWiseMeetingInviteeMembers", e);
		}
		return returnList;
		
	}
	public IdNameVO getMatchedVOInList(List<IdNameVO> voList, Long id) {
		if (voList != null && voList.size() > 0) {
			for (IdNameVO vo : voList) {
				if (id != null && vo.getId().longValue() == id.longValue())
					return vo;
			}
		}
		return null;
	}
}
