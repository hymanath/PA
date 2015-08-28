package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.mapping.Array;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointHistoryDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDocumentDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingLevelDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteHistoryDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingOccurrenceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingTypeDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CallTrackingVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.PartyMeetingSummaryVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.model.PartyMeeting;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPoint;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPointHistory;
import com.itgrids.partyanalyst.model.PartyMeetingMinute;
import com.itgrids.partyanalyst.model.PartyMeetingMinuteHistory;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IPartyMeetingService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

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
	private IPartyMeetingMinuteHistoryDAO partyMeetingMinuteHistoryDAO;
	private TransactionTemplate transactionTemplate;
	private IPartyMeetingAtrPointHistoryDAO partyMeetingAtrPointHistoryDAO;
	private ICadreCommitteeService cadreCommitteeService;
	
	
	
	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}
	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}
	public IPartyMeetingAtrPointHistoryDAO getPartyMeetingAtrPointHistoryDAO() {
		return partyMeetingAtrPointHistoryDAO;
	}
	public void setPartyMeetingAtrPointHistoryDAO(
			IPartyMeetingAtrPointHistoryDAO partyMeetingAtrPointHistoryDAO) {
		this.partyMeetingAtrPointHistoryDAO = partyMeetingAtrPointHistoryDAO;
	}
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public IPartyMeetingMinuteHistoryDAO getPartyMeetingMinuteHistoryDAO() {
		return partyMeetingMinuteHistoryDAO;
	}
	public void setPartyMeetingMinuteHistoryDAO(
			IPartyMeetingMinuteHistoryDAO partyMeetingMinuteHistoryDAO) {
		this.partyMeetingMinuteHistoryDAO = partyMeetingMinuteHistoryDAO;
	}
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
	
	public PartyMeetingVO getPartyMeetingsForCadrePeople(Long tdpCadreId)
	{
		PartyMeetingVO returnVO = new PartyMeetingVO();
		try {
			List<Long> tdpCadreIdsList = new ArrayList<Long>(0);
			tdpCadreIdsList.add(tdpCadreId);Date toDayDate = new DateUtilService().getCurrentDateAndTime();
			if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			{
				List<Object[]> invitationList =  partyMeetingInviteeDAO.getPartyMeetingsInvitationDetlsByCadreIds(tdpCadreIdsList,null,toDayDate);
				Long invitationCount = 0L;
				List<Long> invitationMeetingsList = new ArrayList<Long>(0);
				if(invitationList != null && invitationList.size()>0)
				{
					for (Object[] param : invitationList) {
						Long count = param[2] != null ? Long.valueOf(param[2].toString()):0L;
						invitationCount = invitationCount+count;
						invitationMeetingsList.add(param[0] != null ? Long.valueOf(param[0].toString()):0L);
					}
				}
				 
				 List<Object[]> attendedList =  partyMeetingAttendanceDAO.getAttendenceForCadre(tdpCadreId,toDayDate);
					Long attendedCount = 0L;
					if(attendedList != null && attendedList.size()>0)
					{
						for (Object[] param : attendedList) {
							Long count = param[2] != null ? Long.valueOf(param[2].toString()):0L;
							attendedCount = attendedCount+count;
							if(invitationMeetingsList.contains(param[0] != null ? Long.valueOf(param[0].toString()):0L))
							{
								invitationMeetingsList.remove(param[0] != null ? Long.valueOf(param[0].toString()):0L);
							}
						}
					}
					
					Long absentCount = Long.valueOf(String.valueOf(invitationMeetingsList.size()));
					
					if(absentCount > 0L)
					{
						List<Object[]> eventNames = partyMeetingDAO.getPartyMeetingDetailsByMeetingIdList(invitationMeetingsList,toDayDate);
						if(eventNames != null && eventNames.size()>0)
						{
							List<PartyMeetingVO> partyMeetingList = new ArrayList<PartyMeetingVO>(0);
							for (Object[] param : eventNames) {
								PartyMeetingVO vo = new PartyMeetingVO();
								vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
								vo.setMeetingType(commonMethodsUtilService.getStringValueForObject(param[2])+" - "+commonMethodsUtilService.getStringValueForObject(param[3]));
								vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
								vo.setLocation(commonMethodsUtilService.getStringValueForObject(param[4]));
								vo.setMemberStatus("Absent");
								partyMeetingList.add(vo);
							}
							returnVO.setPartyMeetingVOList(partyMeetingList);
						}
					}
					
					returnVO.setAbsentCount(absentCount);
					returnVO.setInvitedCount(invitationCount);
					returnVO.setAttendedCount(attendedCount);
					
					
			}
		} catch (Exception e) {
			LOG.error(" Exception occured in getPartyMeetingsForCadrePeople in PartyMeetingService Class.",e);
		}
		return returnVO;
	}
	
	public PartyMeetingVO getPartyMeetingDetailsBySearchType(Long tdpCadreId )
	{
		PartyMeetingVO returnVO = new PartyMeetingVO();
		try {
			List<Long> tdpCadreIdsList = new ArrayList<Long>(0);
			tdpCadreIdsList.add(tdpCadreId);Date toDayDate = new DateUtilService().getCurrentDateAndTime();
			if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			{
				List<Object[]> availMeetingDetls =  partyMeetingInviteeDAO.getPartyMeetingsInvitationsDetailsByCadreIds(tdpCadreIdsList,toDayDate);
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
				
				List<Object[]> attendedMeetingDetls =   partyMeetingAttendanceDAO.getPartyMeetingsAttendenceDetailsByCadreId(tdpCadreIdsList,toDayDate);
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
				/*PartyMeetingVO getDetailsForAllMettngs = getMeetingTypeWiseDescription(null,tdpCadreId);
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
				}*/
				
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
	
	public PartyMeetingVO getMeetingTypeWiseDescription(Long partyMeetingTypeId,Long tdpCadreId)
	{
		PartyMeetingVO returnVO = new PartyMeetingVO();
		try {
			
		//	List<Long> tdpCadreIdsList = tdpCadreDAO.getCadreIdByMembershipId(memberShipNo, constituencyId);
			Date toDayDate = new DateUtilService().getCurrentDateAndTime();
			List<Long> tdpCadreIdsList = new ArrayList<Long>(0);
			tdpCadreIdsList.add(tdpCadreId);
			Map<String ,PartyMeetingVO> meetingWisedetlsMap = new LinkedHashMap<String, PartyMeetingVO>(0);
			List<Object[]> mettingWiseInvitationcountsList = partyMeetingInviteeDAO.getPartyMeetingsInvitationDetlsByCadreIds(tdpCadreIdsList, partyMeetingTypeId,toDayDate);
			List<String> partyMeetingsList = new ArrayList<String>(0);
			
			if(mettingWiseInvitationcountsList != null && mettingWiseInvitationcountsList.size()>0)
			{
				for (Object[] meeting : mettingWiseInvitationcountsList) {
					
					Long meetingId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
					String meetingName = commonMethodsUtilService.getStringValueForObject(meeting[1]);
					//Long meetinglevelId = commonMethodsUtilService.getLongValueForObject(meeting[2]);
					String meetinglevelStr = commonMethodsUtilService.getStringValueForObject(meeting[3]);
					//Long meetinglevelValue = commonMethodsUtilService.getLongValueForObject(meeting[4]);
					//Long meetingTypeId = commonMethodsUtilService.getLongValueForObject(meeting[5]);
					String meetingTypeStr = commonMethodsUtilService.getStringValueForObject(meeting[6]);
					String fromDateStr = commonMethodsUtilService.getStringValueForObject(meeting[7]);
					String toDateStr = commonMethodsUtilService.getStringValueForObject(meeting[8]);
					
					Long invitationCount = commonMethodsUtilService.getLongValueForObject(meeting[9]);
					String locationStr = commonMethodsUtilService.getStringValueForObject(meeting[10]);
					
					partyMeetingsList.add(meetingName);
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
					partyMeetingVO.setInvitedCount(invitationCount);
					meetingWisedetlsMap.put(meetingName, partyMeetingVO);
					
				}
			}

			List<Object[]> partyMeetingDtls = partyMeetingAttendanceDAO.getTotalAttendedDetailsForCadreIds(tdpCadreIdsList,partyMeetingTypeId,toDayDate);
		
			if(partyMeetingDtls != null && partyMeetingDtls.size()>0)
			{
				for (Object[] meeting : partyMeetingDtls) {
					Long meetingId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
					String meetingName = commonMethodsUtilService.getStringValueForObject(meeting[1]);
					Long attendedCount = commonMethodsUtilService.getLongValueForObject(meeting[9]);
					
					partyMeetingsList.remove(meetingName);
					PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
					if(meetingWisedetlsMap.get(meetingName) != null)
					{
						partyMeetingVO = meetingWisedetlsMap.get(meetingName);
					}
					partyMeetingVO.setAttendedCount(attendedCount);
					meetingWisedetlsMap.put(meetingName, partyMeetingVO);
				}
			}
			
			List<Object[]> absentpartyMeetingDtls = partyMeetingAttendanceDAO.getAbsentMemberDeails(tdpCadreIdsList,partyMeetingTypeId);
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
					
					if(partyMeetingVO.getInvitedCount() != null && partyMeetingVO.getInvitedCount().longValue()>0L)	{	
						Long invitedCount = partyMeetingVO.getInvitedCount();
						partyMeetingVO.setAbsentCount(invitedCount - attendedCount);
					}
						
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
			
			if(meetingWisedetlsMap != null && meetingWisedetlsMap.size()>0)
			{
				PartyMeetingVO partyMeetingVO  = resultList.get(0);
				List<PartyMeetingVO> absentMeetingsList = new ArrayList<PartyMeetingVO>(0);
				for (String meetingStr : meetingWisedetlsMap.keySet()) {
					if(partyMeetingsList.contains(meetingStr))
					{
						absentMeetingsList.add(meetingWisedetlsMap.get(meetingStr));
					}
				}
				partyMeetingVO.setPartyMeetingVOList(absentMeetingsList);
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
	
	public String updateMeetingPoint(final Long minuteId,final String minuteText,final Long updatedBy,Long partyMeetingId){
			String updateStatusString="failed";
		try {
			LOG.info("Entered into updateMeetingPoint");
			
			if(minuteId.longValue()>0l){
				updateStatusString = (String) transactionTemplate.execute(new TransactionCallback() 
		    	{
				  public Object doInTransaction(TransactionStatus status) 
				  {
					  String updated = "success";
					  PartyMeetingMinute pmm = partyMeetingMinuteDAO.get(minuteId);
						
						PartyMeetingMinuteHistory pmmh = new PartyMeetingMinuteHistory();
						
						pmmh.setPartyMeetingMinuteId(pmm.getPartyMeetingMinuteId());
						pmmh.setPartyMeetingId(pmm.getPartyMeetingId());
						pmmh.setMinutePoint(pmm.getMinutePoint());
						pmmh.setInsertedById(pmm.getInsertedBy().getUserId());
						pmmh.setUpdatedById(pmm.getUpdatedBy().getUserId());
						pmmh.setInsertedTime(pmm.getInsertedTime());
						pmmh.setUpdatedTime(pmm.getUpdatedTime());
						
						partyMeetingMinuteHistoryDAO.save(pmmh);
						
						Integer updateStatus = partyMeetingMinuteDAO.updateMeetingPoint(minuteId,minuteText,updatedBy,new DateUtilService().getCurrentDateAndTime());
						if(updateStatus.intValue()==0){
							updated  = "failed";
						}
						
						return updated;
				  }
	           });
			}else{
				PartyMeetingMinute pmm = new PartyMeetingMinute();
				
				pmm.setPartyMeetingId(partyMeetingId);
				pmm.setMinutePoint(minuteText);
				pmm.setInsertedById(updatedBy);
				pmm.setUpdatedById(updatedBy);
				pmm.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
				pmm.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
				pmm.setIsDeleted("N");
				
				partyMeetingMinuteDAO.save(pmm);
				
				return "success";
			}
			
			
		} catch (Exception e) {
			LOG.error("Exception raised at updateMeetingPoint", e);
		}
		return updateStatusString;
	}
		
	public String deleteMeetingMinutePoint(final Long minuteId,final Long updatedBy){
		String updateStatusString="failed";
		try {
			LOG.info("Entered into deleteMeetingMinutePoint");
			
			updateStatusString = (String) transactionTemplate.execute(new TransactionCallback() 
	    	{
			  public Object doInTransaction(TransactionStatus status) 
			  {
				  String updated = "success";
				  PartyMeetingMinute pmm = partyMeetingMinuteDAO.get(minuteId);
					
					PartyMeetingMinuteHistory pmmh = new PartyMeetingMinuteHistory();
					
					pmmh.setPartyMeetingMinuteId(pmm.getPartyMeetingMinuteId());
					pmmh.setPartyMeetingId(pmm.getPartyMeetingId());
					pmmh.setMinutePoint(pmm.getMinutePoint());
					pmmh.setInsertedById(pmm.getInsertedBy().getUserId());
					pmmh.setUpdatedById(pmm.getUpdatedBy().getUserId());
					pmmh.setInsertedTime(pmm.getInsertedTime());
					pmmh.setUpdatedTime(pmm.getUpdatedTime());
					
					partyMeetingMinuteHistoryDAO.save(pmmh);
					
					Integer deleteStatus = partyMeetingMinuteDAO.deleteMeetingMinutePoint(minuteId,updatedBy,new DateUtilService().getCurrentDateAndTime());
					if(deleteStatus.intValue()==0){
						updated  = "failed";
					}
					return updated;
			  }
	       });
		}catch (Exception e) {
			LOG.error("Exception raised at deleteMeetingMinutePoint", e);
		}
		return updateStatusString;
	}
	
	public String updateMeetingAtrPoint(final Long atrId, final String request,final String actionTaken,final String raisedBy,final Long updatedBy,final Long locationId,Long partyMeetingId,final Long locationScope){
		String updateStatusString="failed";
		try {
			LOG.info("Entered into updateMeetingAtrPoint");
			
			if(atrId.longValue()>0l){
				updateStatusString = (String) transactionTemplate.execute(new TransactionCallback() 
		    	{
				  public Object doInTransaction(TransactionStatus status) 
				  {
					  String updated = "success";
					  PartyMeetingAtrPoint pmap = partyMeetingAtrPointDAO.get(atrId);
					  
					  PartyMeetingAtrPointHistory pmaph = new PartyMeetingAtrPointHistory();
					  
					  pmaph.setPartyMeetingAtrPointId(pmap.getPartyMeetingAtrPointId());
					  pmaph.setPartyMeetingId(pmap.getPartyMeetingId());
					  pmaph.setRequest(pmap.getRequest());
					  pmaph.setActionTaken(pmap.getActionTaken());
					  //pmaph.setRequestFrom(pmap.getRequestFrom());
					  pmaph.setLocationScopeId(pmap.getLocationScopeId());
					  pmaph.setLocationValue(pmap.getLocationValue());
					  pmaph.setAddressId(pmap.getAddressId());
					  pmaph.setRaisedBy(pmap.getRaisedBy());
					  pmaph.setInsertedById(pmap.getInsertedById());
					  pmaph.setUpdatedById(pmap.getUpdatedById());
					  pmaph.setInsertedTime(pmap.getInsertedTime());
					  pmaph.setUpdatedTime(pmap.getUpdatedTime());
					  
					  
					  partyMeetingAtrPointHistoryDAO.save(pmaph);
					  
					  Long locationScopeTemp = locationScope;
					  Long locationIdTemp = locationId; 
					  
					  if(locationScope==4l){
								String manTowDiv = locationId.toString();
								char temp = manTowDiv.charAt(0);
								locationScopeTemp=Long.parseLong(temp+"");
								if(locationScopeTemp==4l){
									locationIdTemp = Long.parseLong(manTowDiv.substring(1));
								}
								if(locationScopeTemp==5l){
									locationIdTemp = Long.parseLong(manTowDiv.substring(1));
								}
								if(locationScopeTemp==6l){
									locationIdTemp = Long.parseLong(manTowDiv.substring(1));
								}
								
					  }
						
						if(locationScope==5l){
								String vilwrdId = locationId.toString();
								char temp = vilwrdId.charAt(0);
								locationScopeTemp=Long.parseLong(temp+"");
								
								if(locationScopeTemp==7l){
									locationIdTemp = Long.parseLong(vilwrdId.substring(1));
								}
								if(locationScopeTemp==8l){
									locationIdTemp = Long.parseLong(vilwrdId.substring(1));
								}
						}
					  
					  Integer updateStatus = partyMeetingAtrPointDAO.updateMeetingAtrPoint(atrId,request,actionTaken,raisedBy,updatedBy,new DateUtilService().getCurrentDateAndTime(),locationIdTemp);
					  if(updateStatus.intValue()==0){
							updated  = "failed";
						}
						
						return updated;
					  
				  }
		    	});
			}else{
				
				PartyMeetingAtrPoint pmap = new PartyMeetingAtrPoint();
				
				pmap.setPartyMeetingId(partyMeetingId);
				pmap.setRequest(request);
				pmap.setActionTaken(actionTaken);
				//pmap.setRequestFrom(null);
				pmap.setLocationScopeId(locationScope);
				pmap.setLocationValue(locationId);
				pmap.setAddressId(updatedBy);
				pmap.setRaisedBy(raisedBy);
				pmap.setInsertedById(updatedBy);
				pmap.setUpdatedById(updatedBy);
				pmap.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
				pmap.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
				pmap.setIsDeleted("N");
				
				partyMeetingAtrPointDAO.save(pmap);
				updateStatusString="success";
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised at updateMeetingAtrPoint", e);
		}
		return updateStatusString;
	}
	
	public String deleteMeetingAtrPoint(final Long atrId,final Long updatedBy){
		String updateStatusString="failed";
		try {
			LOG.info("Entered into deleteMeetingAtrPoint");
			
			updateStatusString = (String) transactionTemplate.execute(new TransactionCallback() 
	    	{
			  public Object doInTransaction(TransactionStatus status) 
			  {
				  String updated = "success";
				  PartyMeetingAtrPoint pmap = partyMeetingAtrPointDAO.get(atrId);
					
					PartyMeetingAtrPointHistory pmaph = new PartyMeetingAtrPointHistory();
					
					pmaph.setPartyMeetingAtrPointId(pmap.getPartyMeetingAtrPointId());
					pmaph.setPartyMeetingId(pmap.getPartyMeetingId());
					pmaph.setRequest(pmap.getRequest());
					pmaph.setActionTaken(pmap.getActionTaken());
					//pmaph.setRequestFrom(pmap.getRequestFrom());
					pmaph.setLocationScopeId(pmap.getLocationScopeId());
					pmaph.setLocationValue(pmap.getLocationValue());
					pmaph.setAddressId(pmap.getAddressId());
					pmaph.setRaisedBy(pmap.getRaisedBy());
					pmaph.setInsertedById(pmap.getInsertedById());
					pmaph.setUpdatedById(pmap.getUpdatedById());
					pmaph.setInsertedTime(pmap.getInsertedTime());
					pmaph.setUpdatedTime(pmap.getUpdatedTime());
					  
					partyMeetingAtrPointHistoryDAO.save(pmaph);
					
					
					Integer deleteStatus = partyMeetingAtrPointDAO.deleteMeetingAtrPoint(atrId,updatedBy,new DateUtilService().getCurrentDateAndTime());
					if(deleteStatus.intValue()==0){
						updated  = "failed";
					}
					return updated;
			  }
	       });
		}catch (Exception e) {
			LOG.error("Exception raised at deleteMeetingAtrPoint", e);
		}
		return updateStatusString;
	}
	
	public String deletePartyMeetingDocument(Long docId){
		String status = "failed";
		try {
			LOG.info("Entered into deletePartyMeetingDocument");
			Integer count = partyMeetingDocumentDAO.deletePartyMeetingDocument(docId);
			
			if(count.intValue()>0){
				status="success";
			}else{
				status = "failed";
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at deletePartyMeetingDocument", e);
		}
		return status;
	}
	
	public PartyMeetingVO getAtrPointsForAMeeting(Long partyMeeingId){
		PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
		try {
			LOG.info("Entered into getAtrPointsForAMeeting");
			PartyMeeting partyMeetingDetails = partyMeetingDAO.get(partyMeeingId);
			List<Object[]> atrDetails = partyMeetingAtrPointDAO.getAtrDetailsForAMeeting(partyMeeingId);
			
			if(partyMeetingDetails != null){
				partyMeetingVO.setId(partyMeetingDetails.getPartyMeetingId()!=null?partyMeetingDetails.getPartyMeetingId():0l);
				partyMeetingVO.setName(partyMeetingDetails.getMeetingName()!=null?partyMeetingDetails.getMeetingName():"");
				partyMeetingVO.setPartyMeetingTypeId(partyMeetingDetails.getPartyMeetingType()!=null?partyMeetingDetails.getPartyMeetingType().getPartyMeetingTypeId():0l);
				partyMeetingVO.setPartyMeetingType(partyMeetingDetails.getPartyMeetingType()!=null?partyMeetingDetails.getPartyMeetingType().getType():"");
				partyMeetingVO.setMeetingLevelId(partyMeetingDetails.getPartyMeetingLevel()!=null?partyMeetingDetails.getPartyMeetingLevel().getPartyMeetingLevelId():0l);
				partyMeetingVO.setMeetingLevel(partyMeetingDetails.getPartyMeetingLevel()!=null?partyMeetingDetails.getPartyMeetingLevel().getLevel():"");
				partyMeetingVO.setLocationValue(partyMeetingDetails.getLocationValue()!=null?partyMeetingDetails.getLocationValue():0l);
				if(partyMeetingDetails.getStartDate()!=null && partyMeetingDetails.getEndDate()!=null){
					partyMeetingVO.setStartDate(partyMeetingDetails.getStartDate());
					partyMeetingVO.setEndDate(partyMeetingDetails.getEndDate());
				}
				
				if(partyMeetingVO.getMeetingLevelId()!=0 && partyMeetingVO.getLocationValue()!=0){
					List<Long> locationIds = new ArrayList<Long>();
					locationIds.add(partyMeetingVO.getLocationValue());
					List<IdNameVO> rslt = cadreCommitteeService.getLocationNameByLocationIds(locationIds, partyMeetingVO.getMeetingLevelId());
					if(rslt!=null && rslt.size()>0){
						partyMeetingVO.setLocationName(rslt.get(0).getName());
					}
				}
				
			}
			
			if(atrDetails!=null && atrDetails.size()>0){
				List<PartyMeetingVO> vo = new ArrayList<PartyMeetingVO>();
				for (Object[] objects : atrDetails) {
					PartyMeetingVO subVO = new PartyMeetingVO();
					
					subVO.setPartyMeetingAtrPointId(objects[0]!=null?(Long)objects[0]:0l);
					subVO.setId(objects[1]!=null?(Long)objects[1]:0l);
					subVO.setRequest(objects[2]!=null?objects[2].toString():"");
					subVO.setActionTaken(objects[3]!=null?objects[3].toString():"");
					subVO.setRequestFrom(objects[4]!=null?objects[4].toString():"");
					subVO.setLocationScopeId(objects[5]!=null?(Long)objects[5]:0l);
					subVO.setLocationValue(objects[6]!=null?(Long)objects[6]:0l);
					subVO.setRaisedBy(objects[7]!=null?objects[7].toString():"");
					subVO.setInsertedById(objects[8]!=null?(Long)objects[8]:0l);
					subVO.setInsertedBy(objects[9]!=null?objects[9].toString():"");
					subVO.setUpdatedById(objects[10]!=null?(Long)objects[10]:0l);
					subVO.setUpdatedBy(objects[11]!=null?objects[11].toString():"");
					subVO.setInsertedTime(objects[12]!=null?objects[12].toString():"");
					subVO.setUpdatedTime(objects[13]!=null?objects[13].toString():"");
					
					if(subVO.getLocationValue()!=0 && subVO.getLocationScopeId()!=0){
						List<Long> locationIds = new ArrayList<Long>();
						locationIds.add(subVO.getLocationValue());
						List<IdNameVO> rslt = cadreCommitteeService.getLocationNameByLocationIds(locationIds, subVO.getLocationScopeId()+1);
						if(rslt!=null && rslt.size()>0){
							subVO.setLocationName(rslt.get(0).getName());
						}
					}
					
					vo.add(subVO);
				}
				partyMeetingVO.setAtrDetails(vo);
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getAtrPointsForAMeeting", e);
		}
		return partyMeetingVO;
	}
	
	public PartyMeetingVO getDocumentDetailsForAMeeting(Long partyMeetingId){
		PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
		try {
			LOG.info("Entered into getDocumentDetailsForAMeeting");
			
			List<Object[]> documentDetails = partyMeetingDocumentDAO.getDocumentDetailsForMinutesAtr(partyMeetingId);
			
			if(documentDetails!=null && documentDetails.size()>0){
				
				List<CallTrackingVO> atrDocs = new ArrayList<CallTrackingVO>();
				List<CallTrackingVO> minDocs = new ArrayList<CallTrackingVO>();
				
				for (Object[] objects : documentDetails) {
			
					CallTrackingVO vo = new CallTrackingVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setUrl(objects[2]!=null?IConstants.LOCAL_FILES+"/"+objects[2].toString().trim():"");
					vo.setName(objects[10].toString());
					
					if(objects[3]!=null && objects[3].toString().equalsIgnoreCase("MINUTE")){
						minDocs.add(vo);
					}else if(objects[3]!=null && objects[3].toString().equalsIgnoreCase("ATR")){
						atrDocs.add(vo);
					}
				}
				
				partyMeetingVO.setMinutesDocuments(minDocs);
				partyMeetingVO.setAtrDocuments(atrDocs);
				
			}
			
		}catch (Exception e) {
			LOG.error("Exception raised at getDocumentDetailsForAMeeting", e);
		}
		return partyMeetingVO;
	}
	
	public PartyMeetingVO getTheMinutePointsForAMeeting(Long meetingId){
		
		PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
		
		try{
			LOG.info("Entered into getPartyMeetingMinutesAtrDetails");
			
			PartyMeeting partyMeetingDetails = partyMeetingDAO.get(meetingId);
			List<Object[]> minutesDetails = partyMeetingMinuteDAO.getMinuteDetailsForAMeeting(meetingId);
			
			if(partyMeetingDetails != null){
				partyMeetingVO.setId(partyMeetingDetails.getPartyMeetingId()!=null?partyMeetingDetails.getPartyMeetingId():0l);
				partyMeetingVO.setName(partyMeetingDetails.getMeetingName()!=null?partyMeetingDetails.getMeetingName():"");
				partyMeetingVO.setPartyMeetingTypeId(partyMeetingDetails.getPartyMeetingType()!=null?partyMeetingDetails.getPartyMeetingType().getPartyMeetingTypeId():0l);
				partyMeetingVO.setPartyMeetingType(partyMeetingDetails.getPartyMeetingType()!=null?partyMeetingDetails.getPartyMeetingType().getType():"");
				partyMeetingVO.setMeetingLevelId(partyMeetingDetails.getPartyMeetingLevel()!=null?partyMeetingDetails.getPartyMeetingLevel().getPartyMeetingLevelId():0l);
				partyMeetingVO.setMeetingLevel(partyMeetingDetails.getPartyMeetingLevel()!=null?partyMeetingDetails.getPartyMeetingLevel().getLevel():"");
				partyMeetingVO.setLocationValue(partyMeetingDetails.getLocationValue()!=null?partyMeetingDetails.getLocationValue():0l);
				if(partyMeetingDetails.getStartDate()!=null && partyMeetingDetails.getEndDate()!=null){
					partyMeetingVO.setStartDate(partyMeetingDetails.getStartDate());
					partyMeetingVO.setEndDate(partyMeetingDetails.getEndDate());
				}
				
			}
			
			if(minutesDetails!=null && minutesDetails.size()>0){
				List<PartyMeetingVO> vo = new ArrayList<PartyMeetingVO>();
				for (Object[] objects : minutesDetails) {
					PartyMeetingVO subVO = new PartyMeetingVO();
					
					subVO.setPartyMeetingMinuteId(objects[0]!=null?(Long)objects[0]:0l);
					subVO.setId(objects[1]!=null?(Long)objects[1]:0l);
					subVO.setMinutePoint(objects[2]!=null?objects[2].toString():"");
					subVO.setInsertedById(objects[3]!=null?(Long)objects[3]:0l);
					subVO.setInsertedBy(objects[4]!=null?objects[4].toString():"");
					subVO.setUpdatedById(objects[5]!=null?(Long)objects[5]:0l);
					subVO.setUpdatedBy(objects[6]!=null?objects[6].toString():"");
					subVO.setInsertedTime(objects[7]!=null?objects[7].toString():"");
					subVO.setUpdatedTime(objects[8]!=null?objects[8].toString():"");
					
					vo.add(subVO);
					
				}
				partyMeetingVO.setMinutesDetails(vo);
			}
		}catch (Exception e) {
			LOG.error("Exception raised at getTheMinutePointsForAMeeting", e);
		}
		
		return partyMeetingVO;
	}
	
	
	 /**
     * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
     * @since 21-AUG-2015
     * Service To Give Meetings Summary For Locations
     * @param List<Long> LocationIds, Long LocationLevel, String startDate, String endDate
     * @return PartyMeetingSummaryVO
     */
	//public PartyMeetingSummaryVO getMeetingSummaryForLocation(Long locationLevel, List<Long> locationIds, String startDateStr, String endDateStr){
	public PartyMeetingSummaryVO getMeetingSummaryForLocation(Long typeOfMeeting,Long locationLevel,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId,String startDateStr,String endDateStr){
		LOG.debug(" Entered Into getMeetingSummaryForLocation");
		PartyMeetingSummaryVO finalVO = new PartyMeetingSummaryVO();
		List<PartyMeetingSummaryVO> fnlLst = new ArrayList<PartyMeetingSummaryVO>();
		try{
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			
			Date startDate= null;
			Date endDate= null;
			
			if(startDateStr.trim().length()>0){
				startDate= format.parse(startDateStr);
			}
			if(startDateStr.trim().length()>0){
				endDate= format.parse(endDateStr);
			}
			
			List<Long> statesList = new ArrayList<Long>(0);
			statesList.add(stateId);
			
			List<Long> districtList = new ArrayList<Long>(0);
			districtList.add(distId);
			
			List<Long> constituencyList = new ArrayList<Long>(0);
			constituencyList.add(constId);
			
			List<Long> mandalList = new ArrayList<Long>(0);
			List<Long> townList = new ArrayList<Long>(0);
			List<Long> divisonList = new ArrayList<Long>(0);
			List<Long> villageList = new ArrayList<Long>(0);
			List<Long> wardList = new ArrayList<Long>(0);
			
			if(locationLevel==4){
				String mtdId = manTowDivId.toString();
				char temp = mtdId.charAt(0);
				locationLevel=Long.parseLong(temp+"");
				if(locationLevel==1l){
					mandalList.add(Long.parseLong(mtdId.substring(1)));
					locationLevel = 4l;
				}else if(locationLevel==2l){
					townList.add(Long.parseLong(mtdId.substring(1)));
					locationLevel = 5l;
				}else if(locationLevel==3l){
					divisonList.add(Long.parseLong(mtdId.substring(1)));
					locationLevel = 6l;
				}
			}
			
			if(locationLevel==5){
				String vwId = wardPanId.toString();
				char temp = vwId.charAt(0);
				locationLevel=Long.parseLong(temp+"");
				if(locationLevel==1l){
					villageList.add(Long.parseLong(vwId.substring(1)));
					locationLevel=7l;
				}else if(locationLevel==2l){
					wardList.add(Long.parseLong(vwId.substring(1)));
					locationLevel=8l;
				}
			}
			// GETTING MEETINGS DETAILS WITH THE LOCATIONS 
			//partyMeetingDAO.getAllMeetings(meetingType, locationLevel, stateList, districtList, constituencyList, mandalList, townList, divisonList, villageList, wardList, startDate, endDate);
			
			List<Object[]> partyMeetingsRslt = partyMeetingDAO.getAllMeetings(typeOfMeeting, locationLevel, statesList, districtList, constituencyList, mandalList, townList, divisonList, villageList, wardList, startDate, endDate);
			
			List<Long> locationIds = new ArrayList<Long>(0);
			
			if(locationLevel==1l){
				locationIds.addAll(statesList);
			}else if(locationLevel==2l){
				locationIds.addAll(districtList);
			}else if(locationLevel==3l){
				locationIds.addAll(constituencyList);
			}else if(locationLevel==4l){
				locationIds.addAll(mandalList);
			}else if(locationLevel==5l){
				locationIds.addAll(townList);
			}else if(locationLevel==6l){
				locationIds.addAll(divisonList);
			}else if(locationLevel==7l){
				locationIds.addAll(villageList);
			}else if(locationLevel==8l){
				locationIds.addAll(wardList);
			}
			
			if(locationIds.get(0)==0l && partyMeetingsRslt!=null && partyMeetingsRslt.size()>0){
				for(Object[] obj:partyMeetingsRslt){
					locationIds.add((Long)(obj[4]));
				}
			}
			
			List<IdNameVO> nameRslt = cadreCommitteeService.getLocationNameByLocationIds(locationIds, locationLevel);
			
			List<Long> mtngLctnsLst = new ArrayList<Long>();
			Map<Long,String> lctnNmsMap = new HashMap<Long, String>();
			if(nameRslt!=null && nameRslt.size()>0){
				for(IdNameVO temp:nameRslt){
					lctnNmsMap.put(temp.getId(), temp.getName());
				}
			}
			
			List<Long> prtyMtngIdsLst = new ArrayList<Long>();
			if(partyMeetingsRslt!=null && partyMeetingsRslt.size()>0){
				for(Object[] obj:partyMeetingsRslt){
					if(!mtngLctnsLst.contains(Long.valueOf(obj[4].toString()))){
						mtngLctnsLst.add(Long.valueOf(obj[4].toString()));					// For Overall Summary -- Unique Meetings
					}
					PartyMeetingSummaryVO tmp = new PartyMeetingSummaryVO();
					tmp.setMeetingId(Long.valueOf(obj[9].toString()));
					tmp.setMeetingName(obj[8].toString());
					tmp.setScheduledOn(obj[5].toString());
					tmp.setLocation(lctnNmsMap.get(Long.valueOf(obj[4].toString())));
					fnlLst.add(tmp);
					prtyMtngIdsLst.add(tmp.getMeetingId());
				}
			}
			
			if(prtyMtngIdsLst.size()>0){
				// GETTING & SETTING OF MEETING ATTENDANCE DETAILS,ATR,MOM DETAILS OF MEETINGS
				List<PartyMeetingSummaryVO> attndnceRsltLst = getAttendentsInformation(prtyMtngIdsLst);
				List<PartyMeetingSummaryVO> momAndAtrRsltLst = getAtrAndMOMOfMeetings(prtyMtngIdsLst);
				
				if(attndnceRsltLst!=null && attndnceRsltLst.size()>0 && fnlLst!=null && fnlLst.size()>0){
					
					fnlLst.get(0).setConductedMeetings(attndnceRsltLst.get(0).getConductedMeetings());									// For Overall Summary -- Meetings Conducted (If Attendance is Greter Than Zero)
					fnlLst.get(0).setAverageInviteesAttended(attndnceRsltLst.get(0).getAverageInviteesAttended());						// For Overall Summary -- Total Invitees Attended / Meetings Conducted
					fnlLst.get(0).setAverageInviteesAttendedPercent(attndnceRsltLst.get(0).getAverageInviteesAttendedPercent());		// For Overall Summary -- Total Invitees Attended / All Invitees
					
					for(PartyMeetingSummaryVO temp:fnlLst){
						PartyMeetingSummaryVO attndncVO = getMatchedMeeting(temp.getMeetingId(), attndnceRsltLst);
						if(attndncVO!=null){
							temp.setAttendanceInfo(attndncVO);
						}
					}
				}
				
				if(momAndAtrRsltLst!=null && momAndAtrRsltLst.size()>0 && fnlLst!=null && fnlLst.size()>0){
					for(PartyMeetingSummaryVO temp:fnlLst){
						PartyMeetingSummaryVO docsTxtVO = getMatchedMeeting(temp.getMeetingId(), momAndAtrRsltLst);
						if(docsTxtVO!=null){
							temp.setDocTxtInfo(docsTxtVO);
						}
					}
				}
			}
			
			if(fnlLst.size()>0){
				fnlLst.get(0).setMeetingsCount(mtngLctnsLst.size());													// For Overall Summary -- Meetings Count( Unique Locations for overall Meetings)
				fnlLst.get(0).setPlannedMeetings(fnlLst.size());
				fnlLst.get(0).setConductedMeetingsPercent((new BigDecimal(fnlLst.get(0).getConductedMeetings()*(100.0)/fnlLst.get(0).getPlannedMeetings())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());// For Overall Summary -- All Meetings
			}
			
		}catch (Exception e) {
			LOG.error(" Error in getMeetingSummaryForLocation",e);
		}
		
		finalVO.setPartyMeetingsList(fnlLst);
		return finalVO;
	}
	
	
	/**
     * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
     * @since 21-AUG-2015
     * Service To Give Attendents Information (Invitees Total, Attended, Absented, Others)
     * @param List<Long> PartyMeetingIds
     * @return PartyMeetingSummaryVO
     */
	public List<PartyMeetingSummaryVO> getAttendentsInformation(List<Long> partyMeetingIds){
		LOG.debug("Entered Into getAttendentsInformation");
		List<PartyMeetingSummaryVO> finalVOLst = new ArrayList<PartyMeetingSummaryVO>();
		try{
			List<Object[]> ttlRslt = partyMeetingAttendanceDAO.getTotalAttendentsOfMeetings(partyMeetingIds);
			List<Object[]> invtsAttndRslt = partyMeetingAttendanceDAO.getInviteesAttendedCountOfMeetings(partyMeetingIds);
			List<Object[]> invtsTtlRslt = partyMeetingInviteeDAO.getPartyMeetingInviteesForMeetings(partyMeetingIds);
			
			if(ttlRslt!=null && ttlRslt.size()>0){
				for(Object[] obj:ttlRslt){
					PartyMeetingSummaryVO temp = new PartyMeetingSummaryVO();
					temp.setMeetingId(Long.valueOf(obj[0].toString()));
					temp.setLocationId(Long.valueOf(obj[1].toString()));
					temp.setTotalAttended(Long.valueOf(obj[2].toString()));
					
					temp.setInviteesAttendedPercent("0.0");
					temp.setAbsentPercentage("0.0");
					temp.setNonInviteesAttendedPercent("0.0");
					
					temp.setInviteesAttended(0l);
					temp.setTotalInvitees(0l);
					temp.setNonInviteesAttended(0l);
					finalVOLst.add(temp);
				}
			}
			
			if(invtsTtlRslt!=null && invtsTtlRslt.size()>0){
				for(Object[] obj:invtsTtlRslt){
					if(obj[0]!=null){
						Long meetingId = Long.valueOf(obj[0].toString());
						
						PartyMeetingSummaryVO temp = getMatchedMeeting(meetingId, finalVOLst);
						if(temp==null){
							temp = new PartyMeetingSummaryVO();
						}
						temp.setTotalInvitees(Long.valueOf(obj[2].toString()));
					}
				}
			}
			
			if(invtsAttndRslt!=null && invtsAttndRslt.size()>0){
				for(Object[] obj:invtsAttndRslt){
					if(obj[0]!=null){
						Long meetingId = Long.valueOf(obj[0].toString());
						Long invitsAttended = Long.valueOf(obj[2].toString());
						PartyMeetingSummaryVO temp = getMatchedMeeting(meetingId, finalVOLst);
						if(temp==null){
							temp = new PartyMeetingSummaryVO();
						}
						temp.setInviteesAttended(invitsAttended);
					}
				}
			}
			
			int mtngNtCnductd = 0;
			Long ttlAttnddInvts = 0l;
			Long overAllInvitees = 0l;
			if(finalVOLst!=null && finalVOLst.size()>0){
				for(PartyMeetingSummaryVO temp:finalVOLst){
					Long ttlAttended = temp.getTotalAttended();
					if(ttlAttended!=null && ttlAttended>0){
						Long ttlInvits = temp.getTotalInvitees();
						Long invitsAttended = temp.getInviteesAttended();
						
						ttlAttnddInvts = ttlAttnddInvts + ttlAttended ; 				// For Overall Summary  
						mtngNtCnductd = mtngNtCnductd + 1;								// For Overall Summary
						overAllInvitees += ttlInvits;
						
						Long othrsAttnd = ttlAttended - invitsAttended;
						Long invitsAbsent = ttlInvits - invitsAttended;
						
						temp.setTotalAbsent(invitsAbsent);
						temp.setNonInviteesAttended(othrsAttnd);
						
						if(ttlAttended!=null && ttlInvits!=null && invitsAttended!=null && invitsAbsent!=null){
							String invtsAttnddPrcnt = (new BigDecimal(invitsAttended*(100.0)/ttlInvits)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							String othrsAttnddPrcnt = (new BigDecimal(othrsAttnd*(100.0)/ttlAttended)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							String invtsAbsntAttnddPrcnt = (new BigDecimal(invitsAbsent*(100.0)/ttlAttended)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							
							temp.setAbsentPercentage(invtsAbsntAttnddPrcnt);
							temp.setNonInviteesAttendedPercent(othrsAttnddPrcnt);
							temp.setInviteesAttendedPercent(invtsAttnddPrcnt);
						}
						
					}
					
				}
			}
			
			if(finalVOLst.size()>0){
				finalVOLst.get(0).setConductedMeetings(mtngNtCnductd);
				//finalVOLst.get(0).setAverageInviteesAttended((int)Math.ceil((double)ttlAttnddInvts/mtngNtCnductd));
				finalVOLst.get(0).setAverageInviteesAttendedPercent((new BigDecimal(ttlAttnddInvts*(100.0)/overAllInvitees)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			}
			
		}catch (Exception e) {
			LOG.error("Error In getAttendentsInformation",e);
		}
		return finalVOLst;
	}
	
	/**
     * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
     * @since 21-AUG-2015
     * Service To Give MOM,ATR & Documents Information
     * @param List<Long> PartyMeetingIds
     * @return PartyMeetingSummaryVO
     * Last modified   : $LastChangedDate$
	 * Revision		   : $Rev$
	 * Version		   : $Id$
	 * Last modified by: $Author$
     */
	
	public List<PartyMeetingSummaryVO> getAtrAndMOMOfMeetings(List<Long> partyMeetingIds){
		LOG.debug("Entered Into getAttendentsInformation");
		List<PartyMeetingSummaryVO> finalVOLst = new ArrayList<PartyMeetingSummaryVO>();
		try{
			List<Object[]> ttlRslt = partyMeetingDocumentDAO.getPartyMeetingDocsOfMeetingIds(partyMeetingIds);
			List<Object[]> momRslt = partyMeetingMinuteDAO.getMinuteDetailsForMeetings(partyMeetingIds);
			List<Object[]> atrRslt = partyMeetingAtrPointDAO.getAtrPointsOfMeetings(partyMeetingIds);
			
			if(ttlRslt!=null && ttlRslt.size()>0){
				for(Object[] obj:ttlRslt){
					Long meetingId = Long.valueOf(obj[0].toString());
					PartyMeetingSummaryVO temp = getMatchedMeeting(meetingId, finalVOLst);
					boolean isNew = false;
					if(temp==null){
						isNew = true;
						temp = new PartyMeetingSummaryVO();
						temp.setMeetingId(meetingId);
					}
					if(obj[1].toString().equalsIgnoreCase("ATR")){
						temp.setAtrFilesExist(true);
						temp.setAtrFilesCount(Long.valueOf(obj[2].toString()));
					}else{
						temp.setMomFilesExist(true);
						temp.setMomFilesCount(Long.valueOf(obj[2].toString()));
					}
					if(isNew){
						finalVOLst.add(temp);
					}
				}
			}
			
			if(momRslt!=null && momRslt.size()>0){
				for(Object[] obj:momRslt){
					Long meetingId = Long.valueOf(obj[0].toString());
					PartyMeetingSummaryVO temp = getMatchedMeeting(meetingId, finalVOLst);
					boolean isNew = false;
					if(temp==null){
						isNew = true;
						temp = new PartyMeetingSummaryVO();
						temp.setMeetingId(meetingId);
					}
					temp.setMomTextExist(true);
					temp.setMomPointsCount(Long.valueOf(obj[1].toString()));
					
					if(isNew){
						finalVOLst.add(temp);
					}
				}
			}
			
			if(atrRslt!=null && atrRslt.size()>0){
				for(Object[] obj:atrRslt){
					Long meetingId = Long.valueOf(obj[0].toString());
					PartyMeetingSummaryVO temp = getMatchedMeeting(meetingId, finalVOLst);
					boolean isNew = false;
					if(temp==null){
						isNew = true;
						temp = new PartyMeetingSummaryVO();
						temp.setMeetingId(meetingId);
					}
					temp.setAtrTextExist(true);
					temp.setAtrTextCount(Long.valueOf(obj[1].toString()));
					
					if(isNew){
						finalVOLst.add(temp);
					}
				}
			}
			
		}catch (Exception e) {
			LOG.error("Error In getAttendentsInformation",e);
		}
		return finalVOLst;
	}
	
	
	/**
     * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
     * @since 21-AUG-2015
     * Helper Service Method To Get Matched Meeting to Set Other Values for the Same Meeting
     * @param Long PartyMeetingId, List<Long> PartyMeetingIds
     * @return PartyMeetingSummaryVO
     */
	public PartyMeetingSummaryVO getMatchedMeeting(Long meetingId,List<PartyMeetingSummaryVO> meetings){
		if(meetingId !=null && meetings!=null && meetings.size()>0){
			for(PartyMeetingSummaryVO temp:meetings){
				if(temp.getMeetingId().equals(meetingId)){
					return temp;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * @Author  Sasi
	 * @Version PartyMeetingService.java  Aug 27, 2015 7:06:45 PM 
	 * @param locationId
	 * @param resultList
	 * @return
	 */
	public PartyMeetingSummaryVO getMatchedLocation(Long locationId,List<PartyMeetingSummaryVO> resultList){
		if(locationId !=null && resultList!=null && resultList.size()>0){
			for(PartyMeetingSummaryVO temp:resultList){
				if(temp.getLocationId().equals(locationId)){
					return temp;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * @Author  Sasi
	 * @Version PartyMeetingService.java  Aug 27, 2015 5:00:20 PM 
	 * @param typeOfMeeting
	 * @param locationLevel
	 * @param stateId
	 * @param distId
	 * @param constId
	 * @param manTowDivId
	 * @param wardPanId
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 * 
	 */
	public PartyMeetingSummaryVO getMeetingSummaryForLocationCumulative(Long typeOfMeeting,Long locationLevel,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId,String startDateStr,String endDateStr){
		LOG.debug(" Entered Into getMeetingSummaryForLocation");
		PartyMeetingSummaryVO finalVO = new PartyMeetingSummaryVO();
		List<PartyMeetingSummaryVO> fnlLst = new ArrayList<PartyMeetingSummaryVO>();
		try{
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			
			Date startDate= null;
			Date endDate= null;
			
			if(startDateStr.trim().length()>0){
				startDate= format.parse(startDateStr);
			}
			if(startDateStr.trim().length()>0){
				endDate= format.parse(endDateStr);
			}
			
			List<Long> statesList = new ArrayList<Long>(0);
			statesList.add(stateId);
			
			List<Long> districtList = new ArrayList<Long>(0);
			districtList.add(distId);
			
			List<Long> constituencyList = new ArrayList<Long>(0);
			constituencyList.add(constId);
			
			List<Long> mandalList = new ArrayList<Long>(0);
			List<Long> townList = new ArrayList<Long>(0);
			List<Long> divisonList = new ArrayList<Long>(0);
			List<Long> villageList = new ArrayList<Long>(0);
			List<Long> wardList = new ArrayList<Long>(0);
			
			if(locationLevel==4){
				String mtdId = manTowDivId.toString();
				char temp = mtdId.charAt(0);
				locationLevel=Long.parseLong(temp+"");
				if(locationLevel==1l){
					mandalList.add(Long.parseLong(mtdId.substring(1)));
					locationLevel = 4l;
				}else if(locationLevel==2l){
					townList.add(Long.parseLong(mtdId.substring(1)));
					locationLevel = 5l;
				}else if(locationLevel==3l){
					divisonList.add(Long.parseLong(mtdId.substring(1)));
					locationLevel = 6l;
				}
			}
			
			if(locationLevel==5){
				String vwId = wardPanId.toString();
				char temp = vwId.charAt(0);
				locationLevel=Long.parseLong(temp+"");
				if(locationLevel==1l){
					villageList.add(Long.parseLong(vwId.substring(1)));
					locationLevel=7l;
				}else if(locationLevel==2l){
					wardList.add(Long.parseLong(vwId.substring(1)));
					locationLevel=8l;
				}
			}
			// GETTING MEETINGS DETAILS WITH THE LOCATIONS 
			//partyMeetingDAO.getAllMeetings(meetingType, locationLevel, stateList, districtList, constituencyList, mandalList, townList, divisonList, villageList, wardList, startDate, endDate);
			
			List<Object[]> partyMeetingsRslt = partyMeetingDAO.getAllMeetings(typeOfMeeting, locationLevel, statesList, districtList, constituencyList, mandalList, townList, divisonList, villageList, wardList, startDate, endDate);
			
			List<Long> locationIds = new ArrayList<Long>(0);
			
			if(locationLevel==1l){
				locationIds.addAll(statesList);
			}else if(locationLevel==2l){
				locationIds.addAll(districtList);
			}else if(locationLevel==3l){
				locationIds.addAll(constituencyList);
			}else if(locationLevel==4l){
				locationIds.addAll(mandalList);
			}else if(locationLevel==5l){
				locationIds.addAll(townList);
			}else if(locationLevel==6l){
				locationIds.addAll(divisonList);
			}else if(locationLevel==7l){
				locationIds.addAll(villageList);
			}else if(locationLevel==8l){
				locationIds.addAll(wardList);
			}
			
			if(locationIds.get(0)==0l && partyMeetingsRslt!=null && partyMeetingsRslt.size()>0){
				for(Object[] obj:partyMeetingsRslt){
					locationIds.add((Long)(obj[4]));
				}
			}
			
			List<IdNameVO> nameRslt = cadreCommitteeService.getLocationNameByLocationIds(locationIds, locationLevel);
			
			Map<Long,String> lctnNmsMap = new HashMap<Long, String>();
			if(nameRslt!=null && nameRslt.size()>0){
				for(IdNameVO temp:nameRslt){
					lctnNmsMap.put(temp.getId(), temp.getName());
				}
			}
			
			List<Long> prtyMtngIdsLst = new ArrayList<Long>();
			if(partyMeetingsRslt!=null && partyMeetingsRslt.size()>0){
				for(Object[] obj:partyMeetingsRslt){
					PartyMeetingSummaryVO tmp = getMatchedLocation(Long.valueOf(obj[4].toString()), fnlLst);
					if(tmp==null){
						tmp = new PartyMeetingSummaryVO();
					}
					tmp.setLocationId(Long.valueOf(obj[4].toString()));
					tmp.setLocation(lctnNmsMap.get(Long.valueOf(obj[4].toString())));
					List<PartyMeetingSummaryVO> meetings = tmp.getPartyMeetingsList();
					if(meetings==null){
						meetings = new ArrayList<PartyMeetingSummaryVO>();
					}
					PartyMeetingSummaryVO attndncVO = getMatchedMeeting(Long.valueOf(obj[9].toString()), meetings);
					if(attndncVO==null){
						attndncVO = new PartyMeetingSummaryVO();
						attndncVO.setMeetingId(Long.valueOf(obj[9].toString()));
						attndncVO.setMeetingName(obj[8].toString());
						attndncVO.setScheduledOn(obj[5].toString());
						meetings.add(attndncVO);
						tmp.setPartyMeetingsList(meetings);
					}
					fnlLst.add(tmp);
					prtyMtngIdsLst.add(tmp.getMeetingId());
				}
			}
			
			if(prtyMtngIdsLst.size()>0){
				// GETTING & SETTING OF MEETING ATTENDANCE DETAILS,ATR,MOM DETAILS OF MEETINGS
				List<PartyMeetingSummaryVO> attndnceRsltLst = getAttendentsInformation(prtyMtngIdsLst);
				List<PartyMeetingSummaryVO> momAndAtrRsltLst = getAtrAndMOMOfMeetings(prtyMtngIdsLst);
				
				if(attndnceRsltLst!=null && attndnceRsltLst.size()>0 && fnlLst!=null && fnlLst.size()>0){
					for(PartyMeetingSummaryVO temp:fnlLst){
						if(temp!=null && temp.getPartyMeetingsList()!=null && temp.getPartyMeetingsList().size()>0){
							for(PartyMeetingSummaryVO temp1:temp.getPartyMeetingsList()){
								PartyMeetingSummaryVO attndncVO = getMatchedMeeting(temp1.getMeetingId(), attndnceRsltLst);
								if(attndncVO!=null){
									temp1.setAttendanceInfo(attndncVO);
								}
							}
						}
						
					}
				}
				
				if(momAndAtrRsltLst!=null && momAndAtrRsltLst.size()>0 && fnlLst!=null && fnlLst.size()>0){
					for(PartyMeetingSummaryVO temp:fnlLst){
						if(temp!=null && temp.getPartyMeetingsList()!=null && temp.getPartyMeetingsList().size()>0){
							for(PartyMeetingSummaryVO temp1:temp.getPartyMeetingsList()){
								PartyMeetingSummaryVO docsTxtVO = getMatchedMeeting(temp1.getMeetingId(), momAndAtrRsltLst);
								if(docsTxtVO!=null){
									temp1.setDocTxtInfo(docsTxtVO);
								}
							}
						}
					}
				}
			}
			
			cumilateTheResult(fnlLst);
			
		}catch (Exception e) {
			LOG.error(" Error in getMeetingSummaryForLocation",e);
		}
		
		finalVO.setPartyMeetingsList(fnlLst);
		return finalVO;
	}
	
	/**
	 * @Author  Sasi
	 * @Version PartyMeetingService.java  Aug 27, 2015 8:44:56 PM 
	 * @param resultList
	 */
	public void cumilateTheResult(List<PartyMeetingSummaryVO> resultList){
		if(resultList!=null && resultList.size()>0){
			for(PartyMeetingSummaryVO temp:resultList){
				List<PartyMeetingSummaryVO> meetings = temp.getPartyMeetingsList();
				
				int  ttlMtngs 		= 0;
				Long ttlInvts 		= 0l;
				Long ttlAttnd 		= 0l;
				Long invtsAttnd 	= 0l;
				Long nonInvtsAttnd 	= 0l;
				Long ttlAbsnt 		= 0l;
				
				int bothCount_atr 		= 0;
				int fileCount_atr		= 0;
				int txtCount_atr		= 0;
				int nothingCount_atr	= 0;
				
				int bothCount_mom 		= 0;
				int fileCount_mom		= 0;
				int txtCount_mom		= 0;
				int nothingCount_mom	= 0;
				
				if(meetings!=null && meetings.size()>0){
					for(PartyMeetingSummaryVO temp1:meetings){
						if(temp1.getAttendanceInfo()!=null){
							if(temp1.getTotalAttended()>0l){
								++ttlMtngs;										//  Increments meetings 
								ttlAttnd+= 		temp1.getTotalAttended();		//	Adding attended
								ttlInvts+= 		temp1.getTotalInvitees();		//	Adding total invitees
								invtsAttnd+= 	temp1.getInviteesAttended();	//	Adding invitees attended
								nonInvtsAttnd+=	temp1.getNonInviteesAttended();	//	Adding non-invitees
								ttlAbsnt+=		temp1.getTotalAbsent();			//	Adding Absentees
							}
						}
						
						if(temp1.isAtrFilesExist() && temp1.isAtrTextExist()){
							++bothCount_atr;
						}else if(temp1.isAtrFilesExist()){
							++fileCount_atr;
						}else if(temp1.isAtrTextExist()){
							++txtCount_atr;
						}else{
							++nothingCount_atr;
						}
						
						
						if(temp1.isMomFilesExist() && temp1.isMomTextExist()){
							++bothCount_mom;
						}else if(temp1.isMomFilesExist()){
							++fileCount_mom;
						}else if(temp1.isMomTextExist()){
							++txtCount_mom;
						}else{
							++nothingCount_mom;
						}
					}
				}
				
				PartyMeetingSummaryVO atrDocTemp = new PartyMeetingSummaryVO();
				atrDocTemp.setBothCount(bothCount_atr);
				atrDocTemp.setOnlyFileCount(fileCount_atr);
				atrDocTemp.setOnlyTxtCount(txtCount_atr);
				atrDocTemp.setNothingCount(nothingCount_atr);
				temp.setAtrDocTxtInfo(atrDocTemp);
				
				PartyMeetingSummaryVO momDocTemp = new PartyMeetingSummaryVO();
				momDocTemp.setBothCount(bothCount_mom);
				momDocTemp.setOnlyFileCount(fileCount_mom);
				momDocTemp.setOnlyTxtCount(txtCount_mom);
				momDocTemp.setNothingCount(nothingCount_mom);
				temp.setMomDocTxtInfo(momDocTemp);
				
				temp.setMeetingsCount(ttlMtngs);
				temp.setTotalAttended(ttlAttnd);
				temp.setTotalInvitees(ttlInvts);
				temp.setInviteesAttended(invtsAttnd);
				temp.setNonInviteesAttended(nonInvtsAttnd);
				temp.setTotalAbsent(ttlAbsnt);
			}
		}
	}
}
