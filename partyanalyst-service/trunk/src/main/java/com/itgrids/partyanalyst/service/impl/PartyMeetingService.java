package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDocumentDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingLevelDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingOccurrenceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingTypeDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.service.IPartyMeetingService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class PartyMeetingService implements IPartyMeetingService{
	private static final Logger LOG = Logger.getLogger(PartyMeetingService.class);
	
	private IPartyMeetingDAO partyMeetingDAO;
	private IPartyMeetingTypeDAO partyMeetingTypeDAO;
	private IPartyMeetingLevelDAO partyMeetingLevelDAO;
	private IPartyMeetingOccurrenceDAO partyMeetingOccurrenceDAO;
	private IPartyMeetingInviteeDAO partyMeetingInviteeDAO;
	private IAttendanceDAO attendanceDAO;
	private IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO;
	private IPartyMeetingMinuteDAO partyMeetingMinuteDAO;
	private ITdpCadreDAO tdpCadreDAO;
	private IPartyMeetingAtrPointDAO partyMeetingAtrPointDAO;
	private IPartyMeetingDocumentDAO partyMeetingDocumentDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	
	public IPartyMeetingAtrPointDAO getPartyMeetingAtrPointDAO() {
		return partyMeetingAtrPointDAO;
	}
	public IPartyMeetingDocumentDAO getPartyMeetingDocumentDAO() {
		return partyMeetingDocumentDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setPartyMeetingAtrPointDAO(
			IPartyMeetingAtrPointDAO partyMeetingAtrPointDAO) {
		this.partyMeetingAtrPointDAO = partyMeetingAtrPointDAO;
	}
	public void setPartyMeetingDocumentDAO(
			IPartyMeetingDocumentDAO partyMeetingDocumentDAO) {
		this.partyMeetingDocumentDAO = partyMeetingDocumentDAO;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	public IPartyMeetingDAO getPartyMeetingDAO() {
		return partyMeetingDAO;
	}
	public IPartyMeetingTypeDAO getPartyMeetingTypeDAO() {
		return partyMeetingTypeDAO;
	}
	public IPartyMeetingLevelDAO getPartyMeetingLevelDAO() {
		return partyMeetingLevelDAO;
	}
	public IPartyMeetingOccurrenceDAO getPartyMeetingOccurrenceDAO() {
		return partyMeetingOccurrenceDAO;
	}
	public IPartyMeetingInviteeDAO getPartyMeetingInviteeDAO() {
		return partyMeetingInviteeDAO;
	}
	public IAttendanceDAO getAttendanceDAO() {
		return attendanceDAO;
	}
	public IPartyMeetingAttendanceDAO getPartyMeetingAttendanceDAO() {
		return partyMeetingAttendanceDAO;
	}
	public IPartyMeetingMinuteDAO getPartyMeetingMinuteDAO() {
		return partyMeetingMinuteDAO;
	}
	public void setPartyMeetingDAO(IPartyMeetingDAO partyMeetingDAO) {
		this.partyMeetingDAO = partyMeetingDAO;
	}
	public void setPartyMeetingTypeDAO(IPartyMeetingTypeDAO partyMeetingTypeDAO) {
		this.partyMeetingTypeDAO = partyMeetingTypeDAO;
	}
	public void setPartyMeetingLevelDAO(IPartyMeetingLevelDAO partyMeetingLevelDAO) {
		this.partyMeetingLevelDAO = partyMeetingLevelDAO;
	}
	public void setPartyMeetingOccurrenceDAO(
			IPartyMeetingOccurrenceDAO partyMeetingOccurrenceDAO) {
		this.partyMeetingOccurrenceDAO = partyMeetingOccurrenceDAO;
	}
	public void setPartyMeetingInviteeDAO(
			IPartyMeetingInviteeDAO partyMeetingInviteeDAO) {
		this.partyMeetingInviteeDAO = partyMeetingInviteeDAO;
	}
	public void setAttendanceDAO(IAttendanceDAO attendanceDAO) {
		this.attendanceDAO = attendanceDAO;
	}
	public void setPartyMeetingAttendanceDAO(
			IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO) {
		this.partyMeetingAttendanceDAO = partyMeetingAttendanceDAO;
	}
	public void setPartyMeetingMinuteDAO(
			IPartyMeetingMinuteDAO partyMeetingMinuteDAO) {
		this.partyMeetingMinuteDAO = partyMeetingMinuteDAO;
	}
	
	
	public PartyMeetingVO getPartyMeetingDetailsBySearchType(String memberShipNo,Long constituencyId)
	{
		PartyMeetingVO returnVO = new PartyMeetingVO();
		try {
			List<Long> tdpCadreIdsList = new ArrayList<Long>(0);
			tdpCadreIdsList.add(commonMethodsUtilService.getLongValueForString(memberShipNo));
			if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			{
				List<Object[]> availMeetingDetls =  partyMeetingInviteeDAO.getPartyMeetingsInvitationsDetailsByCadreIds(tdpCadreIdsList);
				Map<String,PartyMeetingVO> levelTypeWiseMeetingsMap = new LinkedHashMap<String,PartyMeetingVO>(0);
				if(availMeetingDetls != null && availMeetingDetls.size()>0)
				{
					for (Object[] meeting : availMeetingDetls) {
						Long meetinglevelId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
						String meetinglevelStr = commonMethodsUtilService.getStringValueForObject(meeting[1]);
						Long meetingTypeId = commonMethodsUtilService.getLongValueForObject(meeting[2]);
						String meetingTypeStr = commonMethodsUtilService.getStringValueForObject(meeting[3]);
						Long invitedCount = commonMethodsUtilService.getLongValueForObject(meeting[4]);
						
						PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
						if(levelTypeWiseMeetingsMap.get(meetingTypeStr) != null)
						{
							partyMeetingVO = levelTypeWiseMeetingsMap.get(meetingTypeStr);
						}
						partyMeetingVO.setId(meetingTypeId);
						partyMeetingVO.setName(meetingTypeStr);
						partyMeetingVO.setLocationId(meetinglevelId); // levelId
						partyMeetingVO.setLocation(meetinglevelStr); // level
						partyMeetingVO.setInvitedCount(invitedCount);
						levelTypeWiseMeetingsMap.put(meetingTypeStr, partyMeetingVO);
					}
					
				}
				
				List<Object[]> attendedMeetingDetls =   partyMeetingAttendanceDAO.getPartyMeetingsAttendenceDetailsByCadreId(tdpCadreIdsList);
				if(attendedMeetingDetls != null && attendedMeetingDetls.size()>0)
				{
					for (Object[] meeting : attendedMeetingDetls) {
						Long meetinglevelId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
						String meetinglevelStr = commonMethodsUtilService.getStringValueForObject(meeting[1]);
						Long meetingTypeId = commonMethodsUtilService.getLongValueForObject(meeting[2]);
						String meetingTypeStr = commonMethodsUtilService.getStringValueForObject(meeting[3]);
						Long attendedCount = commonMethodsUtilService.getLongValueForObject(meeting[4]);
						
						PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
						if(levelTypeWiseMeetingsMap.get(meetingTypeStr) != null)
						{
							partyMeetingVO = levelTypeWiseMeetingsMap.get(meetingTypeStr);
						}
						else
						{
							partyMeetingVO.setId(meetingTypeId);
							partyMeetingVO.setName(meetingTypeStr);
							partyMeetingVO.setLocationId(meetinglevelId); // levelId
							partyMeetingVO.setLocation(meetinglevelStr); // level
						}
						
						partyMeetingVO.setAttendedCount(attendedCount);
						levelTypeWiseMeetingsMap.put(meetingTypeStr, partyMeetingVO);
					}
				}
				
				List<PartyMeetingVO> resultList = null;
				PartyMeetingVO getDetailsForAllMettngs = getMeetingTypeWiseDescription(null);
				if(getDetailsForAllMettngs != null)
				{
					if(getDetailsForAllMettngs.getPartyMeetingVOList() != null && getDetailsForAllMettngs.getPartyMeetingVOList().size()>0)
					{
						for (PartyMeetingVO partyMeetingVO : getDetailsForAllMettngs.getPartyMeetingVOList()) {
							if(levelTypeWiseMeetingsMap.get(partyMeetingVO.getMeetingType()) != null)
							{
								PartyMeetingVO meetingVO =levelTypeWiseMeetingsMap.get(partyMeetingVO.getMeetingType());
								meetingVO.getPartyMeetingVOList().add(partyMeetingVO);
							}
						}
					}
				}
				
				if(levelTypeWiseMeetingsMap != null && levelTypeWiseMeetingsMap.size()>0)
				{
					resultList = new ArrayList<PartyMeetingVO>(0);
					for (String meetingTypeStr : levelTypeWiseMeetingsMap.keySet()) {
						PartyMeetingVO partyMeetingVO = levelTypeWiseMeetingsMap.get(meetingTypeStr);
						if(partyMeetingVO != null)
						{
							Long invitedCount =0L;
							Long attendeCount = 0L;
							partyMeetingVO.setAbsentCount(0L);
							
							if(partyMeetingVO.getAttendedCount() != null)
								attendeCount = partyMeetingVO.getAttendedCount();
							if(partyMeetingVO.getInvitedCount() != null)
								invitedCount = partyMeetingVO.getAttendedCount();
							if(invitedCount.longValue() > attendeCount.longValue())
								partyMeetingVO.setAbsentCount(invitedCount.longValue() - attendeCount.longValue());
								
							resultList.add(partyMeetingVO);
						}
					}
					
					if(resultList != null && resultList.size()>0)
					{
						returnVO.setPartyMeetingVOList(resultList);
					}
				}
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartyMeetingDetailsBySearchType in PartyMeetingService Class.",e);
		}
		return returnVO;
	}
	
	public PartyMeetingVO getMeetingTypeWiseDescription(Long partyMeetingTypeId)
	{
		PartyMeetingVO returnVO = new PartyMeetingVO();
		try {
			
		//	List<Long> tdpCadreIdsList = tdpCadreDAO.getCadreIdByMembershipId(memberShipNo, constituencyId);
			
			List<Object[]> partyMeetingDtls = partyMeetingAttendanceDAO.getTotalAttendedDetailsForCadreIds(null,partyMeetingTypeId);
			Map<String ,PartyMeetingVO> meetingWisedetlsMap = new LinkedHashMap<String, PartyMeetingVO>(0);
			if(partyMeetingDtls != null && partyMeetingDtls.size()>0)
			{
				for (Object[] meeting : partyMeetingDtls) {
					Long meetingId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
					String meetingName = commonMethodsUtilService.getStringValueForObject(meeting[1]);
					Long meetinglevelId = commonMethodsUtilService.getLongValueForObject(meeting[2]);
					String meetinglevelStr = commonMethodsUtilService.getStringValueForObject(meeting[3]);
					Long meetinglevelValue = commonMethodsUtilService.getLongValueForObject(meeting[4]);
					//Long meetingTypeId = commonMethodsUtilService.getLongValueForObject(meeting[5]);
					String meetingTypeStr = commonMethodsUtilService.getStringValueForObject(meeting[6]);
					String fromDateStr = commonMethodsUtilService.getStringValueForObject(meeting[7]);
					String toDateStr = commonMethodsUtilService.getStringValueForObject(meeting[8]);
					Long attendedCount = commonMethodsUtilService.getLongValueForObject(meeting[9]);
					String locationStr = commonMethodsUtilService.getStringValueForObject(meeting[10]);
					PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
					if(meetingWisedetlsMap.get(meetingName) != null)
					{
						partyMeetingVO = meetingWisedetlsMap.get(meetingName);
					}
					partyMeetingVO.setLocation(locationStr);
					partyMeetingVO.setId(meetingId);
					partyMeetingVO.setName(meetingName);
					partyMeetingVO.setMeetingLevelStr(meetinglevelStr);
					partyMeetingVO.setMeetingType(meetingTypeStr);
					partyMeetingVO.setStartDateStr(fromDateStr);
					partyMeetingVO.setEndDateStr(toDateStr);
					partyMeetingVO.setAttendedCount(attendedCount);
					meetingWisedetlsMap.put(meetingName, partyMeetingVO);
				}
			}
			
			List<Object[]> mettingWiseInvitationcountsList = partyMeetingInviteeDAO.getPartyMeetingsInvitationDetlsByCadreIds(null, partyMeetingTypeId);
			if(mettingWiseInvitationcountsList != null && mettingWiseInvitationcountsList.size()>0)
			{
				for (Object[] meeting : mettingWiseInvitationcountsList) {
					//Long meetingId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
					String meetingName = commonMethodsUtilService.getStringValueForObject(meeting[1]);
					Long invitedCount = commonMethodsUtilService.getLongValueForObject(meeting[2]);
					
					PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
					if(meetingWisedetlsMap.get(meetingName) != null)
					{
						partyMeetingVO = meetingWisedetlsMap.get(meetingName);
					}
					partyMeetingVO.setInvitedCount(invitedCount);
					meetingWisedetlsMap.put(meetingName, partyMeetingVO);
				}
			}
			
			List<Object[]> absentpartyMeetingDtls = partyMeetingAttendanceDAO.getAbsentMemberDeails(partyMeetingTypeId);
			if(absentpartyMeetingDtls != null && absentpartyMeetingDtls.size()>0)
			{
				for (Object[] meeting : absentpartyMeetingDtls) {
					//Long meetingId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
					String meetingName = commonMethodsUtilService.getStringValueForObject(meeting[1]);
					Long attendedCount = commonMethodsUtilService.getLongValueForObject(meeting[2]);
					
					PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
					if(meetingWisedetlsMap.get(meetingName) != null)
					{
						partyMeetingVO = meetingWisedetlsMap.get(meetingName);
					}
					Long invitedCount = partyMeetingVO.getInvitedCount();
					if(invitedCount != null && invitedCount.longValue()>0L)						;
						partyMeetingVO.setAbsentCount(invitedCount - attendedCount);
						
					meetingWisedetlsMap.put(meetingName, partyMeetingVO);
				}
			}
			
			
		/*	List<Object[]> mettingWiseMinutscountsList = partyMeetingMinuteDAO.getPartyMeetingsMinutsDetlsByCadreIds(partyMeetingTypeId);
			if(mettingWiseMinutscountsList != null && mettingWiseMinutscountsList.size()>0)
			{
				for (Object[] meeting : mettingWiseMinutscountsList) {
					//Long meetingId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
					String meetingName = commonMethodsUtilService.getStringValueForObject(meeting[1]);
					Long minutsCount = commonMethodsUtilService.getLongValueForObject(meeting[2]);
					
					PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
					if(meetingWisedetlsMap.get(meetingName) != null)
					{
						partyMeetingVO = meetingWisedetlsMap.get(meetingName);
					}
					partyMeetingVO.setMinutsCount(minutsCount);
					meetingWisedetlsMap.put(meetingName, partyMeetingVO);
				}
			}
			
			List<Object[]> atrPointsWiseMinutscountsList = partyMeetingAtrPointDAO.getPartyMeetingsATRPointsDetls(partyMeetingTypeId);
			if(atrPointsWiseMinutscountsList != null && atrPointsWiseMinutscountsList.size()>0)
			{
				for (Object[] meeting : atrPointsWiseMinutscountsList) {
					//Long meetingId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
					String meetingName = commonMethodsUtilService.getStringValueForObject(meeting[1]);
					Long atrPointsCount = commonMethodsUtilService.getLongValueForObject(meeting[2]);
					
					PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
					if(meetingWisedetlsMap.get(meetingName) != null)
					{
						partyMeetingVO = meetingWisedetlsMap.get(meetingName);
					}
					partyMeetingVO.setAtrPointsCount(atrPointsCount);
					meetingWisedetlsMap.put(meetingName, partyMeetingVO);
				}
			}
			
			List<Object[]> documnetsWiseMinutscountsList = partyMeetingDocumentDAO.getPartyMeetingsDocumentsDetls(partyMeetingTypeId);
			if(documnetsWiseMinutscountsList != null && documnetsWiseMinutscountsList.size()>0)
			{
				for (Object[] meeting : documnetsWiseMinutscountsList) {
					//Long meetingId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
					String meetingName = commonMethodsUtilService.getStringValueForObject(meeting[1]);
					Long documentsCount = commonMethodsUtilService.getLongValueForObject(meeting[2]);
					
					PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
					if(meetingWisedetlsMap.get(meetingName) != null)
					{
						partyMeetingVO = meetingWisedetlsMap.get(meetingName);
					}
					partyMeetingVO.setDocumentsCount(documentsCount);
					meetingWisedetlsMap.put(meetingName, partyMeetingVO);
				}
			}
			*/
			List<PartyMeetingVO> resultList = null;
			if(meetingWisedetlsMap != null && meetingWisedetlsMap.size()>0)
			{
				resultList = new ArrayList<PartyMeetingVO>(0);
				for (String  meetingName: meetingWisedetlsMap.keySet()) {
					PartyMeetingVO partyMeetingVO = meetingWisedetlsMap.get(meetingName);
					if(partyMeetingVO != null)
					{
						resultList.add(partyMeetingVO);
					}
				}
			}
			
			if(resultList != null && resultList.size()>0)
			{
				returnVO.setPartyMeetingVOList(resultList);
			}
			
		} catch (Exception e) {
			LOG.error(" Exception occured in getMeetingTypeWiseDescription in PartyMeetingService Class.",e);
		}
		
		return returnVO;
	}
	
	
}
