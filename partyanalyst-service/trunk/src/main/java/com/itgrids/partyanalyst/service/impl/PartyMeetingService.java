package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPoint;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPointHistory;
import com.itgrids.partyanalyst.model.PartyMeetingMinute;
import com.itgrids.partyanalyst.model.PartyMeetingMinuteHistory;
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
			tdpCadreIdsList.add(tdpCadreId);
			if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			{
				List<Object[]> invitationList =  partyMeetingInviteeDAO.getPartyMeetingsInvitationDetlsByCadreIds(tdpCadreIdsList,null);
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
				 
				 List<Object[]> attendedList =  partyMeetingAttendanceDAO.getAttendenceForCadre(tdpCadreId);
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
						List<Object[]> eventNames = partyMeetingDAO.getPartyMeetingDetailsByMeetingIdList(invitationMeetingsList);
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
			tdpCadreIdsList.add(tdpCadreId);
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
	
	public String updateMeetingAtrPoint(final Long atrId, final String request,final String actionTaken,final String raisedBy,final Long updatedBy,final Long locationId,Long partyMeetingId,Long locationScope){
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
					  
					  Integer updateStatus = partyMeetingAtrPointDAO.updateMeetingAtrPoint(atrId,request,actionTaken,raisedBy,updatedBy,new DateUtilService().getCurrentDateAndTime(),locationId);
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
			List<Object[]> atrDetails = partyMeetingAtrPointDAO.getAtrDetailsForAMeeting(partyMeeingId);
			
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
					vo.setUrl(objects[2]!=null?IConstants.LOCAL_FILES_FOLDER+"/"+objects[2].toString().trim():"");
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
}
