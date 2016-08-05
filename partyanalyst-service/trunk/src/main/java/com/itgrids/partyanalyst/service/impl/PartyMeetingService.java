package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityAttendanceAcceptanceDAO;
import com.itgrids.partyanalyst.dao.IAttendanceDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAtrPointHistoryDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingDocumentDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingIvrStatusDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingLevelDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteHistoryDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingOccurrenceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingTypeDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingUserAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITabDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCommitteeMemberDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserAccessLevelValueDAO;
import com.itgrids.partyanalyst.dto.CallTrackingVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.MeetingTrackingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingSummaryVO;
import com.itgrids.partyanalyst.dto.PartyMeetingVO;
import com.itgrids.partyanalyst.dto.PartyMeetingWSVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.ActivityAttendanceAcceptance;
import com.itgrids.partyanalyst.model.PartyMeeting;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPoint;
import com.itgrids.partyanalyst.model.PartyMeetingAtrPointHistory;
import com.itgrids.partyanalyst.model.PartyMeetingMinute;
import com.itgrids.partyanalyst.model.PartyMeetingMinuteHistory;
import com.itgrids.partyanalyst.model.TabDetails;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.IPartyMeetingService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

/**
 * @author Administrator
 *
 */
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
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IPartyMeetingIvrStatusDAO partyMeetingIvrStatusDAO;
	private IBoothDAO boothDAO;
	private IPanchayatDAO panchayatDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IConstituencyDAO constituencyDAO;
	private IDistrictDAO districtDAO;
	private IStateDAO stateDAO;
	private ITdpCommitteeMemberDAO tdpCommitteeMemberDAO;
	private ITdpCadreCandidateDAO tdpCadreCandidateDAO;
	private IActivityAttendanceAcceptanceDAO activityAttendanceAcceptanceDAO;
	private ITabDetailsDAO tabDetailsDAO;
	private IPublicRepresentativeDAO publicRepresentativeDAO;
	private IPartyMeetingUserAccessLevelDAO partyMeetingUserAccessLevelDAO;
	private IUserAccessLevelValueDAO userAccessLevelValueDAO;
	
	
	
	public void setPartyMeetingUserAccessLevelDAO(
			IPartyMeetingUserAccessLevelDAO partyMeetingUserAccessLevelDAO) {
		this.partyMeetingUserAccessLevelDAO = partyMeetingUserAccessLevelDAO;
	}
	public void setUserAccessLevelValueDAO(
			IUserAccessLevelValueDAO userAccessLevelValueDAO) {
		this.userAccessLevelValueDAO = userAccessLevelValueDAO;
	}
	public ITabDetailsDAO getTabDetailsDAO() {
		return tabDetailsDAO;
	}
	public void setTabDetailsDAO(ITabDetailsDAO tabDetailsDAO) {
		this.tabDetailsDAO = tabDetailsDAO;
	}
	public IActivityAttendanceAcceptanceDAO getActivityAttendanceAcceptanceDAO() {
		return activityAttendanceAcceptanceDAO;
	}
	public void setActivityAttendanceAcceptanceDAO(
			IActivityAttendanceAcceptanceDAO activityAttendanceAcceptanceDAO) {
		this.activityAttendanceAcceptanceDAO = activityAttendanceAcceptanceDAO;
	}
	public IPublicRepresentativeDAO getPublicRepresentativeDAO() {
		return publicRepresentativeDAO;
	}
	public void setPublicRepresentativeDAO(
			IPublicRepresentativeDAO publicRepresentativeDAO) {
		this.publicRepresentativeDAO = publicRepresentativeDAO;
	}
	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}
	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}
	public ITdpCommitteeMemberDAO getTdpCommitteeMemberDAO() {
		return tdpCommitteeMemberDAO;
	}
	public void setTdpCommitteeMemberDAO(
			ITdpCommitteeMemberDAO tdpCommitteeMemberDAO) {
		this.tdpCommitteeMemberDAO = tdpCommitteeMemberDAO;
	}
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public IPartyMeetingIvrStatusDAO getPartyMeetingIvrStatusDAO() {
		return partyMeetingIvrStatusDAO;
	}
	public void setPartyMeetingIvrStatusDAO(
			IPartyMeetingIvrStatusDAO partyMeetingIvrStatusDAO) {
		this.partyMeetingIvrStatusDAO = partyMeetingIvrStatusDAO;
	}
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
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
				//List<Long> invitationMeetingsList = new ArrayList<Long>(0);
				Map<Long,Long> invitationMap = new HashMap<Long, Long>(0);
				Map<Long,Long> attendedMap = new HashMap<Long, Long>(0);
				
				if(invitationList != null && invitationList.size()>0)
				{
					for (Object[] param : invitationList) {
						Long count = param[9] != null ? Long.valueOf(param[9].toString()):0L;
						invitationCount = invitationCount+count;
						//invitationMeetingsList.add(param[0] != null ? Long.valueOf(param[0].toString()):0L);
						invitationMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), count);
					}
				}
				 
				 List<Object[]> attendedList =  partyMeetingAttendanceDAO.getAttendenceForCadre(tdpCadreId,toDayDate);
					Long attendedCount = 0L;
					if(attendedList != null && attendedList.size()>0)
					{
						for (Object[] param : attendedList) {
							Long count = param[2] != null ? Long.valueOf(param[2].toString()):0L;
							attendedCount = attendedCount+count;
							attendedMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), count);
							/*Long inviteCount = invitationMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
							if(inviteCount != null && inviteCount.longValue()>0L)
							attendedCount =inviteCount-count;
							*/
							/*if(invitationMeetingsList.contains(param[0] != null ? Long.valueOf(param[0].toString()):0L))
							{
								invitationMeetingsList.remove(param[0] != null ? Long.valueOf(param[0].toString()):0L);
							}*/
						}
					}
					
					//Long absentCount = Long.valueOf(String.valueOf(invitationMeetingsList.size()));
					Long absentCount = 0L;
					
					/*
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
					*/
					Set<Long> partyMeetingIdsList = new HashSet<Long>(0);
					partyMeetingIdsList.addAll(invitationMap.keySet());
					partyMeetingIdsList.addAll(attendedMap.keySet());
					if(commonMethodsUtilService.isListOrSetValid(partyMeetingIdsList)){
						for (Long partyMeetingId : partyMeetingIdsList) {
							Long inviteCount = invitationMap.get(partyMeetingId);
							Long attendCount = attendedMap.get(partyMeetingId);
							if(inviteCount != null && attendCount != null && attendCount.longValue()>0L && 
									 inviteCount.longValue()>0L && inviteCount.longValue()>attendCount.longValue())
								absentCount = inviteCount.longValue()-attendCount.longValue();
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
			Map<Long ,PartyMeetingVO> meetingWisedetlsMap = new LinkedHashMap<Long, PartyMeetingVO>(0);
			List<Object[]> mettingWiseInvitationcountsList = partyMeetingInviteeDAO.getPartyMeetingsInvitationDetlsByCadreIds(tdpCadreIdsList, partyMeetingTypeId,toDayDate);
			List<Long> partyMeetingsList = new ArrayList<Long>(0);
			
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
					
					partyMeetingsList.add(meetingId);
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
					meetingWisedetlsMap.put(meetingId, partyMeetingVO);
					
				}
			}

			List<Object[]> partyMeetingDtls = partyMeetingAttendanceDAO.getTotalAttendedDetailsForCadreIds(tdpCadreIdsList,partyMeetingTypeId,toDayDate);
		
			if(partyMeetingDtls != null && partyMeetingDtls.size()>0)
			{
				for (Object[] meeting : partyMeetingDtls) {
					Long meetingId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
					String meetingName = commonMethodsUtilService.getStringValueForObject(meeting[1]);
					Long attendedCount = commonMethodsUtilService.getLongValueForObject(meeting[9]);
					
					partyMeetingsList.remove(meetingId);
					PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
					if(meetingWisedetlsMap.get(meetingId) != null)
					{
						partyMeetingVO = meetingWisedetlsMap.get(meetingId);
					}
					partyMeetingVO.setAttendedCount(attendedCount);
					meetingWisedetlsMap.put(meetingId, partyMeetingVO);
				}
			}
			
			List<Object[]> absentpartyMeetingDtls = partyMeetingAttendanceDAO.getAbsentMemberDeails(tdpCadreIdsList,partyMeetingTypeId);
			if(absentpartyMeetingDtls != null && absentpartyMeetingDtls.size()>0)
			{
				for (Object[] meeting : absentpartyMeetingDtls) {
					Long meetingId = commonMethodsUtilService.getLongValueForObject(meeting[0]);
					String meetingName = commonMethodsUtilService.getStringValueForObject(meeting[1]);
					Long attendedCount = commonMethodsUtilService.getLongValueForObject(meeting[2]);
					
					PartyMeetingVO partyMeetingVO = new PartyMeetingVO();
					if(meetingWisedetlsMap.get(meetingId) != null)
					{
						partyMeetingVO = meetingWisedetlsMap.get(meetingId);
					}
					
					if(partyMeetingVO.getInvitedCount() != null && partyMeetingVO.getInvitedCount().longValue()>0L)	{	
						Long invitedCount = partyMeetingVO.getInvitedCount();
						partyMeetingVO.setAbsentCount(invitedCount - attendedCount);
					}
						
					meetingWisedetlsMap.put(meetingId, partyMeetingVO);
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
				for (Long  meetingId: meetingWisedetlsMap.keySet()) {
					PartyMeetingVO partyMeetingVO = meetingWisedetlsMap.get(meetingId);
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
				for (Long meetingId : meetingWisedetlsMap.keySet()) {
					if(partyMeetingsList.contains(meetingId))
					{
						absentMeetingsList.add(meetingWisedetlsMap.get(meetingId));
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
						if(subVO.getLocationScopeId().equals(3l)){
							locationIds.add(Long.parseLong((subVO.getLocationValue().toString()).substring(1)));
						}else{
							locationIds.add(subVO.getLocationValue());
						}
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
	public PartyMeetingSummaryVO getMeetingSummaryForLocation(Long typeOfMeeting,Long locationLevel,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId,String startDateStr,String endDateStr,Long meetingLevel){
		LOG.debug(" Entered Into getMeetingSummaryForLocation");
		PartyMeetingSummaryVO finalVO = new PartyMeetingSummaryVO();
		List<PartyMeetingSummaryVO> fnlLst = new ArrayList<PartyMeetingSummaryVO>();
		Long atrUpdatedMeetings=0l;
		Long momUpdatedMeetings=0l;
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
			
			List<Object[]> partyMeetingsRslt = partyMeetingDAO.getAllMeetings(typeOfMeeting, locationLevel, statesList, districtList, constituencyList, mandalList, townList, divisonList, villageList, wardList, startDate, endDate,meetingLevel);
			
			List<Long> locationIds = new ArrayList<Long>(0);
			
			if(meetingLevel==1l){
				locationIds.addAll(statesList);
			}else if(meetingLevel==2l){
				locationIds.addAll(districtList);
			}else if(meetingLevel==3l){
				locationIds.addAll(constituencyList);
			}else if(meetingLevel==4l){
				locationIds.addAll(mandalList);
			}else if(meetingLevel==5l){
				locationIds.addAll(townList);
			}else if(meetingLevel==6l){
				locationIds.addAll(divisonList);
			}else if(meetingLevel==7l){
				locationIds.addAll(villageList);
			}else if(meetingLevel==8l){
				locationIds.addAll(wardList);
			}
			
			if(locationIds.get(0)==0l && partyMeetingsRslt!=null && partyMeetingsRslt.size()>0){
				for(Object[] obj:partyMeetingsRslt){
					locationIds.add((Long)(obj[4]));
				}
			}
			
			List<IdNameVO> nameRslt = cadreCommitteeService.getLocationNameByLocationIds(locationIds, meetingLevel);
			
			List<Long> mtngLctnsLst = new ArrayList<Long>();
			Map<Long,String> lctnNmsMap = new HashMap<Long, String>();
			if(nameRslt!=null && nameRslt.size()>0){
				for(IdNameVO temp:nameRslt){
					lctnNmsMap.put(temp.getId(), temp.getName());
				}
			}
			
			Map<Long, Long> constNOMap = new HashMap<Long, Long>();
			if(meetingLevel.equals(3l)){
				if(locationIds!=null && locationIds.size()>0){
					List<Object[]> constNums = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(locationIds);
					if(constNums!=null && constNums.size()>0){
						for (Object[] obj : constNums) {
							constNOMap.put((Long)obj[0],(Long)obj[1]);
						}
					}
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
					if(meetingLevel.equals(3l)){
						tmp.setAssemblyNo(constNOMap.get(Long.valueOf(obj[14].toString())));
					}
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
				
				if(momAndAtrRsltLst!=null && momAndAtrRsltLst.size()>0){
					for (PartyMeetingSummaryVO partyMeetingSummaryVO : momAndAtrRsltLst) {
						if((partyMeetingSummaryVO.getMomFilesCount()!=null && partyMeetingSummaryVO.getMomFilesCount()>0l) || (partyMeetingSummaryVO.getMomPointsCount()!=null && partyMeetingSummaryVO.getMomPointsCount()>0l)){
							momUpdatedMeetings++;
						}
						if((partyMeetingSummaryVO.getAtrFilesCount()!=null && partyMeetingSummaryVO.getAtrFilesCount()>0l) || (partyMeetingSummaryVO.getAtrTextCount()!=null && partyMeetingSummaryVO.getAtrTextCount()>0l)){
							atrUpdatedMeetings++;
						}
					}
				}
			}
			
			if(fnlLst.size()>0){
				fnlLst.get(0).setMeetingsCount(mtngLctnsLst.size());													// For Overall Summary -- Meetings Count( Unique Locations for overall Meetings)
				fnlLst.get(0).setPlannedMeetings(fnlLst.size());
				fnlLst.get(0).setConductedMeetingsPercent((new BigDecimal(fnlLst.get(0).getConductedMeetings()*(100.0)/fnlLst.get(0).getPlannedMeetings())).setScale(2, BigDecimal.ROUND_HALF_UP).toString());// For Overall Summary -- All Meetings
				fnlLst.get(0).setMomUpdatedMeetings(momUpdatedMeetings);
				fnlLst.get(0).setAtrUpdatedMeetings(atrUpdatedMeetings);
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
			
			if(invtsTtlRslt!=null && invtsTtlRslt.size()>0){
				for(Object[] obj:invtsTtlRslt){
					if(obj[0]!=null){
						//Long meetingId = Long.valueOf(obj[0].toString());
						
						PartyMeetingSummaryVO temp = new PartyMeetingSummaryVO();
						temp.setMeetingId(Long.valueOf(obj[0].toString()));
						temp.setLocationId(Long.valueOf(obj[1].toString()));
						temp.setTotalInvitees(Long.valueOf(obj[2].toString()));
						temp.setInviteesAttendedPercent("0.0");
						temp.setAbsentPercentage("0.0");
						temp.setNonInviteesAttendedPercent("0.0");
						
						temp.setInviteesAttended(0l);
						temp.setNonInviteesAttended(0l);
						temp.setTotalAttended(0L);
						temp.setTotalAbsent(0L);
						finalVOLst.add(temp);
						
					}
				}
			}
			
			if(ttlRslt!=null && ttlRslt.size()>0){
				for(Object[] obj:ttlRslt){
					Long meetingId = Long.valueOf(obj[0].toString());
					PartyMeetingSummaryVO temp = getMatchedMeeting(meetingId, finalVOLst);
					if(temp==null){
						temp = new PartyMeetingSummaryVO();
					}
					temp.setTotalAttended(Long.valueOf(obj[2].toString()));
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
	 * @Version PartyMeetingService.java  Sep 1, 2015 6:23:32 PM 
	 * @param locationId
	 * @param resultList
	 * @param locationType
	 * @return
	 */
	public PartyMeetingSummaryVO getMatchedLocationByLocationType(Long locationId,List<PartyMeetingSummaryVO> resultList, String locationType){
		if(locationId !=null && resultList!=null && resultList.size()>0){
			if(locationType.equalsIgnoreCase("constituency")){
				for(PartyMeetingSummaryVO temp:resultList){
					if(temp.getConstituencyId().equals(locationId)){
						return temp;
					}
				}
			}else if(locationType.equalsIgnoreCase("district")){
				for(PartyMeetingSummaryVO temp:resultList){
					if(temp.getDistrictId().equals(locationId)){
						return temp;
					}
				}
			}else if(locationType.equalsIgnoreCase("state")){
				for(PartyMeetingSummaryVO temp:resultList){
					if(temp.getStateId().equals(locationId)){
						return temp;
					}
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
	public PartyMeetingSummaryVO getMeetingSummaryForLocationCumulative(Long typeOfMeeting,Long locationLevel,Long stateId,Long distId,Long constId,Long manTowDivId,Long wardPanId,String startDateStr,String endDateStr,Long meetingLevel){
		LOG.debug(" Entered Into getMeetingSummaryForLocation");
		PartyMeetingSummaryVO finalVO = new PartyMeetingSummaryVO();
		List<PartyMeetingSummaryVO> fnlLst = new ArrayList<PartyMeetingSummaryVO>();
		Long atrUpdatedMeetings=0l;
		Long momUpdatedMeetings=0l;
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
			
			List<Object[]> partyMeetingsRslt = partyMeetingDAO.getAllMeetings(typeOfMeeting, locationLevel, statesList, districtList, constituencyList, mandalList, townList, divisonList, villageList, wardList, startDate, endDate , meetingLevel);
			
			List<Long> locationIds = new ArrayList<Long>(0);
			
			if(meetingLevel==1l){
				locationIds.addAll(statesList);
			}else if(meetingLevel==2l){
				locationIds.addAll(districtList);
			}else if(meetingLevel==3l){
				locationIds.addAll(constituencyList);
			}else if(meetingLevel==4l){
				locationIds.addAll(mandalList);
			}else if(meetingLevel==5l){
				locationIds.addAll(townList);
			}else if(meetingLevel==6l){
				locationIds.addAll(divisonList);
			}else if(meetingLevel==7l){
				locationIds.addAll(villageList);
			}else if(meetingLevel==8l){
				locationIds.addAll(wardList);
			}
			
			if(locationIds.get(0)==0l && partyMeetingsRslt!=null && partyMeetingsRslt.size()>0){
				for(Object[] obj:partyMeetingsRslt){
					locationIds.add((Long)(obj[4]));
				}
			}
			
			List<IdNameVO> nameRslt = cadreCommitteeService.getLocationNameByLocationIds(locationIds, meetingLevel);
			
			Map<Long,String> lctnNmsMap = new HashMap<Long, String>();
			if(nameRslt!=null && nameRslt.size()>0){
				for(IdNameVO temp:nameRslt){
					lctnNmsMap.put(temp.getId(), temp.getName());
				}
			}
			
			Map<Long, Long> constNOMap = new HashMap<Long, Long>();
			if(meetingLevel.equals(3l)){
				if(locationIds!=null && locationIds.size()>0){
					List<Object[]> constNums = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(locationIds);
						if(constNums!=null && constNums.size()>0){
							for (Object[] obj : constNums) {
								constNOMap.put((Long)obj[0],(Long)obj[1]);
							}
						}
				}
			}
			
			List<Long> prtyMtngIdsLst = new ArrayList<Long>();
			if(partyMeetingsRslt!=null && partyMeetingsRslt.size()>0){
				for(Object[] obj:partyMeetingsRslt){
					PartyMeetingSummaryVO tmp = getMatchedLocation(Long.valueOf(obj[4].toString()), fnlLst);
					boolean isNew = false;
					if(tmp==null){
						isNew = true;
						tmp = new PartyMeetingSummaryVO();
					}
					tmp.setLocationId(Long.valueOf(obj[4].toString()));
					tmp.setLocation(lctnNmsMap.get(Long.valueOf(obj[4].toString())));
					if(meetingLevel.equals(3l)){
						tmp.setAssemblyNo(constNOMap.get(Long.valueOf(obj[14].toString())));
					}
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
						
						attndncVO.setStateId(obj[10]!=null?Long.valueOf(obj[10].toString()):0l);
						attndncVO.setStateName(obj[11]!=null?obj[11].toString():"");
						if(meetingLevel>=2){
							attndncVO.setDistrictId(obj[12]!=null?Long.valueOf(obj[12].toString()):0l);
							attndncVO.setDistrictName(obj[13]!=null?obj[13].toString():"");
						}
						
						if(meetingLevel>=3){
							attndncVO.setConstituencyId(obj[14]!=null?Long.valueOf(obj[14].toString()):0l);
							attndncVO.setConstituencyName(obj[15]!=null?obj[15].toString():"");
							
						}
						
						meetings.add(attndncVO);
						
						prtyMtngIdsLst.add(attndncVO.getMeetingId());
						tmp.setPartyMeetingsList(meetings);
					}
					if(isNew){
						fnlLst.add(tmp);
					}
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
					
					for(PartyMeetingSummaryVO temp:momAndAtrRsltLst){
						if((temp.getMomFilesCount()!=null && temp.getMomFilesCount()>0l) || (temp.getMomPointsCount()!=null && temp.getMomPointsCount()>0l)){
							momUpdatedMeetings++;
						}
						if((temp.getAtrFilesCount()!=null && temp.getAtrFilesCount()>0l) || (temp.getAtrTextCount()!=null && temp.getAtrTextCount()>0l)){
							atrUpdatedMeetings++;
						}
					}
					
				}
			}
			
			cumilateTheResult(fnlLst);
			
			if(fnlLst!=null && fnlLst.size()>0){
				fnlLst.get(0).setAtrUpdatedMeetings(atrUpdatedMeetings);
				fnlLst.get(0).setMomUpdatedMeetings(momUpdatedMeetings);
			}
			
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
							PartyMeetingSummaryVO attendance = temp1.getAttendanceInfo(); 
							if(attendance.getTotalAttended()>0l){
								++ttlMtngs;										//  Increments meetings 
								ttlAttnd+= 		attendance.getTotalAttended();		//	Adding attended
								ttlInvts+= 		attendance.getTotalInvitees();		//	Adding total invitees
								invtsAttnd+= 	attendance.getInviteesAttended();	//	Adding invitees attended
								nonInvtsAttnd+=	attendance.getNonInviteesAttended();	//	Adding non-invitees
								ttlAbsnt+=		attendance.getTotalAbsent();			//	Adding Absentees
							}
						}
						
						if(temp1.getDocTxtInfo()!=null){
							PartyMeetingSummaryVO docsInfo = temp1.getDocTxtInfo();
							
							if(docsInfo.isAtrFilesExist() && docsInfo.isAtrTextExist()){
								++bothCount_atr;
							}else if(docsInfo.isAtrFilesExist()){
								++fileCount_atr;
							}else if(docsInfo.isAtrTextExist()){
								++txtCount_atr;
							}else{
								++nothingCount_atr;
							}
							
							
							if(docsInfo.isMomFilesExist() && docsInfo.isMomTextExist()){
								++bothCount_mom;
							}else if(docsInfo.isMomFilesExist()){
								++fileCount_mom;
							}else if(docsInfo.isMomTextExist()){
								++txtCount_mom;
							}else{
								++nothingCount_mom;
							}
						}else{
							++nothingCount_atr;
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
	
	public PartyMeetingSummaryVO getMeetingSummaryForGrouping(Long typeOfMeeting,
			Long locationLevel,
			Long stateId,
			Long distId,
			Long constId,
			Long manTowDivId,
			Long wardPanId,
			String startDateStr,
			String endDateStr,
			String groupingLocationType,
			Long meetingLevel){
		LOG.debug(" Entered Into getMeetingSummaryForLocation");
		PartyMeetingSummaryVO finalVO = new PartyMeetingSummaryVO();
		List<PartyMeetingSummaryVO> fnlLst = new ArrayList<PartyMeetingSummaryVO>();
		List<PartyMeetingSummaryVO> lctnSplittedRslt = new ArrayList<PartyMeetingSummaryVO>();
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
			
			List<Object[]> partyMeetingsRslt = partyMeetingDAO.getAllMeetings(typeOfMeeting, locationLevel, statesList, districtList, constituencyList, mandalList, townList, divisonList, villageList, wardList, startDate, endDate,meetingLevel);
			
			List<Long> locationIds = new ArrayList<Long>(0);
			
			if(meetingLevel==1l){
				locationIds.addAll(statesList);
			}else if(meetingLevel==2l){
				locationIds.addAll(districtList);
			}else if(meetingLevel==3l){
				locationIds.addAll(constituencyList);
			}else if(meetingLevel==4l){
				locationIds.addAll(mandalList);
			}else if(meetingLevel==5l){
				locationIds.addAll(townList);
			}else if(meetingLevel==6l){
				locationIds.addAll(divisonList);
			}else if(meetingLevel==7l){
				locationIds.addAll(villageList);
			}else if(meetingLevel==8l){
				locationIds.addAll(wardList);
			}
			
			if(locationIds.get(0)==0l && partyMeetingsRslt!=null && partyMeetingsRslt.size()>0){
				for(Object[] obj:partyMeetingsRslt){
					locationIds.add((Long)(obj[4]));
				}
			}
			
			List<IdNameVO> nameRslt = cadreCommitteeService.getLocationNameByLocationIds(locationIds, meetingLevel);
			
			Map<Long,String> lctnNmsMap = new HashMap<Long, String>();
			if(nameRslt!=null && nameRslt.size()>0){
				for(IdNameVO temp:nameRslt){
					lctnNmsMap.put(temp.getId(), temp.getName());
				}
			}
			
			List<Long> prtyMtngIdsLst = new ArrayList<Long>();
			if(partyMeetingsRslt!=null && partyMeetingsRslt.size()>0){
				for(Object[] obj:partyMeetingsRslt){
					PartyMeetingSummaryVO tmp = new PartyMeetingSummaryVO();
					tmp.setMeetingId(Long.valueOf(obj[9].toString()));
					tmp.setMeetingName(obj[8].toString());
					tmp.setScheduledOn(obj[5].toString());
					tmp.setLocation(lctnNmsMap.get(Long.valueOf(obj[4].toString())));
					
					tmp.setStateId(obj[10]!=null?Long.valueOf(obj[10].toString()):0l);
					tmp.setStateName(obj[11]!=null?obj[11].toString():"");
					if(meetingLevel>=2){
						tmp.setDistrictId(obj[12]!=null?Long.valueOf(obj[12].toString()):0l);
						tmp.setDistrictName(obj[13]!=null?obj[13].toString():"");
					}
					
					if(meetingLevel>=3){
						tmp.setConstituencyId(obj[14]!=null?Long.valueOf(obj[14].toString()):0l);
						tmp.setConstituencyName(obj[15]!=null?obj[15].toString():"");
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
			
			
			
				if(groupingLocationType.equalsIgnoreCase("state")){
					if(fnlLst!=null && fnlLst.size()>0){
						for(PartyMeetingSummaryVO temp:fnlLst){
							PartyMeetingSummaryVO stteVO = getMatchedLocationByLocationType(temp.getStateId(), lctnSplittedRslt, groupingLocationType);
							boolean isNew = false;
							if(stteVO==null){
								stteVO = new PartyMeetingSummaryVO();
								stteVO.setStateId(temp.getStateId());
								stteVO.setStateName(temp.getStateName());
								isNew = true;
							}
							List<PartyMeetingSummaryVO> mtngsLst = stteVO.getPartyMeetingsList();
							if(mtngsLst==null){
								mtngsLst = new ArrayList<PartyMeetingSummaryVO>();
							}
							
							mtngsLst.add(temp);
							stteVO.setPartyMeetingsList(mtngsLst);
							
							if(isNew){
								lctnSplittedRslt.add(stteVO);
							}
							
						}
					}
				}else if(groupingLocationType.equalsIgnoreCase("district")){
					if(fnlLst!=null && fnlLst.size()>0){
						for(PartyMeetingSummaryVO temp:fnlLst){
							PartyMeetingSummaryVO stteVO = getMatchedLocationByLocationType(temp.getDistrictId(), lctnSplittedRslt, groupingLocationType);
							boolean isNew = false;
							if(stteVO==null){
								stteVO = new PartyMeetingSummaryVO();
								stteVO.setDistrictId(temp.getDistrictId());
								stteVO.setDistrictName(temp.getDistrictName());
								isNew = true;
							}
							List<PartyMeetingSummaryVO> mtngsLst = stteVO.getPartyMeetingsList();
							if(mtngsLst==null){
								mtngsLst = new ArrayList<PartyMeetingSummaryVO>();
							}
							
							mtngsLst.add(temp);
							stteVO.setPartyMeetingsList(mtngsLst);
							
							if(isNew){
								lctnSplittedRslt.add(stteVO);
							}
							
						}
					}
				}else if(groupingLocationType.equalsIgnoreCase("constituency")){
					Map<Long,Long> constNOMap = new HashMap<Long, Long>();
					
						if(fnlLst!=null && fnlLst.size()>0){
							List<Long> constIds = new ArrayList<Long>();
							for(PartyMeetingSummaryVO temp:fnlLst){
								constIds.add(temp.getConstituencyId());
							}
							
							if(constIds!=null && constIds.size()>0){
								List<Object[]> constNums = delimitationConstituencyDAO.getConstituencyNumbersForConstituenctIds(constIds);
								if(constNums!=null && constNums.size()>0){
									for (Object[] obj : constNums) {
										constNOMap.put((Long)obj[0],(Long)obj[1]);
									}
								}
							}
						}
						for(PartyMeetingSummaryVO temp:fnlLst){
							PartyMeetingSummaryVO stteVO = getMatchedLocationByLocationType(temp.getConstituencyId(), lctnSplittedRslt, groupingLocationType);
							boolean isNew = false;
							if(stteVO==null){
								stteVO = new PartyMeetingSummaryVO();
								stteVO.setConstituencyId(temp.getConstituencyId());
								stteVO.setAssemblyNo(constNOMap.get(temp.getConstituencyId()));
								stteVO.setConstituencyName(temp.getConstituencyName());
								isNew = true;
							}
							List<PartyMeetingSummaryVO> mtngsLst = stteVO.getPartyMeetingsList();
							if(mtngsLst==null){
								mtngsLst = new ArrayList<PartyMeetingSummaryVO>();
							}
							
							mtngsLst.add(temp);
							stteVO.setPartyMeetingsList(mtngsLst);
							
							if(isNew){
								lctnSplittedRslt.add(stteVO);
							}
							
					}
				}
				
				List<Long> meetingIds = new ArrayList<Long>();
				for (PartyMeetingSummaryVO pmsvo : lctnSplittedRslt) {
					if(pmsvo.getPartyMeetingsList().size()>0){
						for(PartyMeetingSummaryVO vo : pmsvo.getPartyMeetingsList()){
							meetingIds.add(vo.getMeetingId());
						}
					}
				}
				
				if(meetingIds.size()>0){
					List<Long> atrDocDtls = partyMeetingDocumentDAO.getDocDetails(meetingIds,"ATR");
					List<Long> minuteDocDtls = partyMeetingDocumentDAO.getDocDetails(meetingIds,"MINUTE");
					List<Long> atrCounts = partyMeetingAtrPointDAO.getAtrHavingMeetings(meetingIds);
					List<Long> momCounts = partyMeetingMinuteDAO.getMOMHavingMeetings(meetingIds);
					
					for (PartyMeetingSummaryVO pmsvo : lctnSplittedRslt) {
						if(pmsvo.getPartyMeetingsList().size()>0){
							for(PartyMeetingSummaryVO vo : pmsvo.getPartyMeetingsList()){
								if(minuteDocDtls.contains(vo.getMeetingId())){
									vo.setMomFilesExist(true);
								}
								if(momCounts.contains(vo.getMeetingId())){
									vo.setMomTextExist(true);
								}
								if(atrDocDtls.contains(vo.getMeetingId())){
									vo.setAtrFilesExist(true);
								}
								if(atrCounts.contains(vo.getMeetingId())){
									vo.setAtrTextExist(true);
								}
							}
						}
					}
					
				}
				
				cumilateTheResult(lctnSplittedRslt);
			
		}catch (Exception e) {
			LOG.error(" Error in getMeetingSummaryForLocation",e);
		}
		
		finalVO.setPartyMeetingsList(lctnSplittedRslt);
		return finalVO;
	}
	
	public PartyMeetingVO getSummaryForAMeeting(Long meetingId,String type){
		PartyMeetingVO vo = new PartyMeetingVO();
		try {
			LOG.info("Entered into getSummaryForAMeeting service");
			List<String> temp = new ArrayList<String>();
			
			List<Object[]> qryrslt=null;
			if(type.equalsIgnoreCase("momText")){
				qryrslt = partyMeetingMinuteDAO.getMinuteDetailsForAMeeting(meetingId);
				
				if(qryrslt != null && qryrslt.size()>0){
					for (Object[] objects : qryrslt) {
						if(objects[2]!=null){
							temp.add(objects[2].toString());
							vo.setSubName(objects[9].toString());
						}
					}
				}
				
				vo.setMinutes(temp);
			}else if(type.equalsIgnoreCase("atrText")){
				qryrslt = partyMeetingAtrPointDAO.getAtrDetailsForAMeeting(meetingId);
				
				if(qryrslt != null && qryrslt.size()>0){
					for (Object[] objects : qryrslt) {
						if(objects[2]!=null){
							temp.add(objects[2].toString());
							vo.setSubName(objects[14].toString());
						}
					}
				}
				vo.setAtrPoints(temp);
			}else{
				qryrslt = partyMeetingDocumentDAO.getMinuteAtrDocumentSummaryForAMeeting(meetingId,type);
				List<PartyMeetingVO> voList = new ArrayList<PartyMeetingVO>();
				if(qryrslt != null && qryrslt.size()>0){
					for (Object[] objects : qryrslt) {
						PartyMeetingVO pmVO = new PartyMeetingVO();
						pmVO.setPath(objects[0].toString());
						pmVO.setName(objects[1].toString());
						pmVO.setSubName(objects[2].toString());
						voList.add(pmVO);
					}
				}
				vo.setDocsList(voList);
			}
			
		} catch (Exception e) {
			LOG.error("Entered into getSummaryForAMeeting service",e);
		}
		
		return vo;
	}
	
	
	public MeetingTrackingVO getPartyMeetingsDetailsForCadreByCommitteeLevel(Long tdpCadreId,String searchTypeStr , 
			 Long committeeLevelId,Long committeeLevelValue,String formDateStr,String toDateStr,String isFirst,int firstRecord,int maxResult)
	{
		MeetingTrackingVO returnVO = new MeetingTrackingVO();
		try {
			Date fromDate = null;
			Date toDate = null;
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
			if(formDateStr != null && !formDateStr.isEmpty())
				fromDate = format.parse(formDateStr);
			if(toDateStr != null && !toDateStr.isEmpty())
				toDate = format.parse(toDateStr);
				
				Map<String,MeetingTrackingVO> monthlyWiseMeetingsMap = new LinkedHashMap<String, MeetingTrackingVO>(0);
				Long availableMeetingsCount = 0L;
				Long conductedMeetingsCount = 0L;
				
				if(committeeLevelId != null && committeeLevelId.longValue()>0L && committeeLevelValue != null && committeeLevelValue.longValue()>0L)
				{
					List<Long> committeeLevelValueList = new ArrayList<Long>(0);
					//List<Long> locationIdsList = new ArrayList<Long>();
					List<Object[]> locationWisePartyMeetingsList = null;
					
					if(searchTypeStr != null && !searchTypeStr.isEmpty())
					{
						committeeLevelValueList.add(committeeLevelValue);
						if(isFirst != null && !isFirst.isEmpty() && isFirst.equalsIgnoreCase("true"))
						{
							if(searchTypeStr.equalsIgnoreCase("constituency"))							
							{
								committeeLevelId = IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID;
							}
							
							BigInteger meetingsCount = partyMeetingDAO.getLocationWiseTotalMeetingsCount(committeeLevelId,committeeLevelValueList,fromDate,toDate);
							if(meetingsCount != null)
								availableMeetingsCount = availableMeetingsCount+Long.valueOf(String.valueOf(meetingsCount));
							
							BigInteger conductedMeetngCount = partyMeetingAttendanceDAO.getLocationWiseTotalMeetingsCount(committeeLevelId, committeeLevelValueList, fromDate, toDate);
							if(conductedMeetngCount == null || conductedMeetngCount.longValue() == 0L)
							{
								conductedMeetngCount = partyMeetingIvrStatusDAO.getLocationWiseTotalMeetingsCount(committeeLevelId,committeeLevelValueList,fromDate,toDate);
							}
								
							if(conductedMeetngCount != null)
								conductedMeetingsCount = conductedMeetingsCount+Long.valueOf(String.valueOf(conductedMeetngCount));					
						}
						
						if(availableMeetingsCount != null && availableMeetingsCount.longValue()>0L)
						{
							if(searchTypeStr.equalsIgnoreCase("VillageORWard"))							
							{
								// villageORWardWiseAccessPartyMeetingsDetails(committeeLevelId,committeeLevelValue,fromDate, toDate, isFirst,
								//		 availableMeetingsCount, conductedMeetingsCount,monthlyWiseMeetingsMap,  firstRecord, maxResult);
								
								locationWisePartyMeetingsList = partyMeetingDAO.getMontlyWiseMeetingsDetails(committeeLevelId,committeeLevelValueList,fromDate,toDate,null,firstRecord,maxResult);
								if(locationWisePartyMeetingsList != null && locationWisePartyMeetingsList.size()>0)
								{
									for (Object[] partyMeeting : locationWisePartyMeetingsList) {
										
										String dateStr = commonMethodsUtilService.getStringValueForObject(partyMeeting[0]); 
										Long totalMeetings = commonMethodsUtilService.getLongValueForObject(partyMeeting[1]);
										
										MeetingTrackingVO meetingsVO = new MeetingTrackingVO();
										meetingsVO.setTotalCount(0L);
										meetingsVO.setActualCount(0L);
										if(monthlyWiseMeetingsMap.get(dateStr) != null)
										{
											meetingsVO = monthlyWiseMeetingsMap.get(dateStr);
											if(meetingsVO.getTotalCount() != null && meetingsVO.getTotalCount().longValue()>0L)
												totalMeetings = totalMeetings+meetingsVO.getTotalCount().longValue();
										}
										meetingsVO.setTotalCount(totalMeetings);
										monthlyWiseMeetingsMap.put(dateStr, meetingsVO);
									}
								}
								
								if(conductedMeetingsCount != null && conductedMeetingsCount.longValue()>0L)
								{
									locationWisePartyMeetingsList = partyMeetingAttendanceDAO.getMontlyWiseMeetingsDetails(committeeLevelId, committeeLevelValueList, fromDate, toDate, new ArrayList<String>(monthlyWiseMeetingsMap.keySet()));
									if(locationWisePartyMeetingsList == null || locationWisePartyMeetingsList.size()==0)					
										locationWisePartyMeetingsList = partyMeetingIvrStatusDAO.getMontlyWiseMeetingsDetails(committeeLevelId,committeeLevelValueList,null,null,new ArrayList<String>(monthlyWiseMeetingsMap.keySet()));
									if(locationWisePartyMeetingsList != null && locationWisePartyMeetingsList.size()>0)
									{
										for (Object[] partyMeeting : locationWisePartyMeetingsList) {
											
											String dateStr = commonMethodsUtilService.getStringValueForObject(partyMeeting[0]);  
											Long totalMeetings = commonMethodsUtilService.getLongValueForObject(partyMeeting[1]);
											
											MeetingTrackingVO meetingsVO = null;
											if(monthlyWiseMeetingsMap.get(dateStr) != null)
											{
												meetingsVO = monthlyWiseMeetingsMap.get(dateStr);
												if(meetingsVO.getActualCount() != null && meetingsVO.getActualCount().longValue()>0L)
													totalMeetings = totalMeetings+meetingsVO.getActualCount().longValue();
												meetingsVO.setActualCount(totalMeetings);
												monthlyWiseMeetingsMap.put(dateStr, meetingsVO);
											}
										}
									}
								}
								
							}
							else if(searchTypeStr.equalsIgnoreCase("MandalORTownORDivision"))							
							{
								//mandalORTownORDidvisionWiseAccessPartyMeetingsDetails(committeeLevelId,committeeLevelValue,fromDate, toDate, isFirst,
									//	 availableMeetingsCount, conductedMeetingsCount,monthlyWiseMeetingsMap,  firstRecord, maxResult);
								
								locationWisePartyMeetingsList = partyMeetingDAO.getMontlyWiseMeetingsDetails(committeeLevelId,committeeLevelValueList,fromDate,toDate,null,firstRecord,maxResult);
								if(locationWisePartyMeetingsList != null && locationWisePartyMeetingsList.size()>0)
								{
									for (Object[] partyMeeting : locationWisePartyMeetingsList) {
										
										String dateStr = commonMethodsUtilService.getStringValueForObject(partyMeeting[0]); 
										Long totalMeetings = commonMethodsUtilService.getLongValueForObject(partyMeeting[1]);
										
										MeetingTrackingVO meetingsVO = new MeetingTrackingVO();
										meetingsVO.setTotalCount(0L);
										meetingsVO.setActualCount(0L);
										if(monthlyWiseMeetingsMap.get(dateStr) != null)
										{
											meetingsVO = monthlyWiseMeetingsMap.get(dateStr);
											if(meetingsVO.getTotalCount() != null && meetingsVO.getTotalCount().longValue()>0L)
												totalMeetings = totalMeetings+meetingsVO.getTotalCount().longValue();
										}
										meetingsVO.setTotalCount(totalMeetings);
										monthlyWiseMeetingsMap.put(dateStr, meetingsVO);
									}
								}
								
								if(conductedMeetingsCount != null && conductedMeetingsCount.longValue()>0L)
								{
									locationWisePartyMeetingsList = partyMeetingAttendanceDAO.getMontlyWiseMeetingsDetails(committeeLevelId, committeeLevelValueList, fromDate, toDate, new ArrayList<String>(monthlyWiseMeetingsMap.keySet()));
									if(locationWisePartyMeetingsList == null || locationWisePartyMeetingsList.size()==0)					
										locationWisePartyMeetingsList = partyMeetingIvrStatusDAO.getMontlyWiseMeetingsDetails(committeeLevelId,committeeLevelValueList,null,null,new ArrayList<String>(monthlyWiseMeetingsMap.keySet()));
									if(locationWisePartyMeetingsList != null && locationWisePartyMeetingsList.size()>0)
									{
										for (Object[] partyMeeting : locationWisePartyMeetingsList) {
											
											String dateStr = commonMethodsUtilService.getStringValueForObject(partyMeeting[0]);  
											Long totalMeetings = commonMethodsUtilService.getLongValueForObject(partyMeeting[1]);
											
											MeetingTrackingVO meetingsVO = null;
											if(monthlyWiseMeetingsMap.get(dateStr) != null)
											{
												meetingsVO = monthlyWiseMeetingsMap.get(dateStr);
												if(meetingsVO.getActualCount() != null && meetingsVO.getActualCount().longValue()>0L)
													totalMeetings = totalMeetings+meetingsVO.getActualCount().longValue();
												meetingsVO.setActualCount(totalMeetings);
												monthlyWiseMeetingsMap.put(dateStr, meetingsVO);
											}
										}
									}
								}
								
							}
							else if(searchTypeStr.equalsIgnoreCase("constituency"))							
							{
									//committeeLevelValueList.add(committeeLevelValue);
									//getPartyMeetingsConductedDetails(IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID, committeeLevelValueList, fromDate, toDate, isFirst, null, null,monthlyWiseMeetingsMap,firstRecord,maxResult);
									locationWisePartyMeetingsList = partyMeetingDAO.getMontlyWiseMeetingsDetails(IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID,committeeLevelValueList,fromDate,toDate,null,firstRecord,maxResult);
									if(locationWisePartyMeetingsList != null && locationWisePartyMeetingsList.size()>0)
									{
										for (Object[] partyMeeting : locationWisePartyMeetingsList) {
											
											String dateStr = commonMethodsUtilService.getStringValueForObject(partyMeeting[0]); 
											Long totalMeetings = commonMethodsUtilService.getLongValueForObject(partyMeeting[1]);
											
											MeetingTrackingVO meetingsVO = new MeetingTrackingVO();
											meetingsVO.setTotalCount(0L);
											meetingsVO.setActualCount(0L);
											if(monthlyWiseMeetingsMap.get(dateStr) != null)
											{
												meetingsVO = monthlyWiseMeetingsMap.get(dateStr);
												if(meetingsVO.getTotalCount() != null && meetingsVO.getTotalCount().longValue()>0L)
													totalMeetings = totalMeetings+meetingsVO.getTotalCount().longValue();
											}
											meetingsVO.setTotalCount(totalMeetings);
											monthlyWiseMeetingsMap.put(dateStr, meetingsVO);
										}
									}
									
									if(conductedMeetingsCount != null && conductedMeetingsCount.longValue()>0L)
									{
										locationWisePartyMeetingsList = partyMeetingAttendanceDAO.getMontlyWiseMeetingsDetails(IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID, committeeLevelValueList, fromDate, toDate, new ArrayList<String>(monthlyWiseMeetingsMap.keySet()));
										if(locationWisePartyMeetingsList == null || locationWisePartyMeetingsList.size()==0)					
											locationWisePartyMeetingsList = partyMeetingIvrStatusDAO.getMontlyWiseMeetingsDetails(IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID,committeeLevelValueList,null,null,new ArrayList<String>(monthlyWiseMeetingsMap.keySet()));
										if(locationWisePartyMeetingsList != null && locationWisePartyMeetingsList.size()>0)
										{
											for (Object[] partyMeeting : locationWisePartyMeetingsList) {
												
												String dateStr = commonMethodsUtilService.getStringValueForObject(partyMeeting[0]);  
												Long totalMeetings = commonMethodsUtilService.getLongValueForObject(partyMeeting[1]);
												
												MeetingTrackingVO meetingsVO = null;
												if(monthlyWiseMeetingsMap.get(dateStr) != null)
												{
													meetingsVO = monthlyWiseMeetingsMap.get(dateStr);
													if(meetingsVO.getActualCount() != null && meetingsVO.getActualCount().longValue()>0L)
														totalMeetings = totalMeetings+meetingsVO.getActualCount().longValue();
													meetingsVO.setActualCount(totalMeetings);
													monthlyWiseMeetingsMap.put(dateStr, meetingsVO);
												}
											}
										}
									}
							}
							else if(searchTypeStr.equalsIgnoreCase("district"))							
							{
									//committeeLevelValueList.add(committeeLevelValue);
									//getPartyMeetingsConductedDetails(IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID, committeeLevelValueList, fromDate, toDate, isFirst, null, null,monthlyWiseMeetingsMap,firstRecord,maxResult);
									locationWisePartyMeetingsList = partyMeetingDAO.getMontlyWiseMeetingsDetails(IConstants.DISTRICT_COMMITTEE_LEVEL_ID,committeeLevelValueList,fromDate,toDate,null,firstRecord,maxResult);
									if(locationWisePartyMeetingsList != null && locationWisePartyMeetingsList.size()>0)
									{
										for (Object[] partyMeeting : locationWisePartyMeetingsList) {
											
											String dateStr = commonMethodsUtilService.getStringValueForObject(partyMeeting[0]); 
											Long totalMeetings = commonMethodsUtilService.getLongValueForObject(partyMeeting[1]);
											
											MeetingTrackingVO meetingsVO = new MeetingTrackingVO();
											meetingsVO.setTotalCount(0L);
											meetingsVO.setActualCount(0L);
											if(monthlyWiseMeetingsMap.get(dateStr) != null)
											{
												meetingsVO = monthlyWiseMeetingsMap.get(dateStr);
												if(meetingsVO.getTotalCount() != null && meetingsVO.getTotalCount().longValue()>0L)
													totalMeetings = totalMeetings+meetingsVO.getTotalCount().longValue();
											}
											meetingsVO.setTotalCount(totalMeetings);
											monthlyWiseMeetingsMap.put(dateStr, meetingsVO);
										}
									}
									
									if(conductedMeetingsCount != null && conductedMeetingsCount.longValue()>0L)
									{
										locationWisePartyMeetingsList = partyMeetingAttendanceDAO.getMontlyWiseMeetingsDetails(IConstants.DISTRICT_COMMITTEE_LEVEL_ID, committeeLevelValueList, fromDate, toDate, new ArrayList<String>(monthlyWiseMeetingsMap.keySet()));
										if(locationWisePartyMeetingsList == null || locationWisePartyMeetingsList.size()==0)					
											locationWisePartyMeetingsList = partyMeetingIvrStatusDAO.getMontlyWiseMeetingsDetails(IConstants.DISTRICT_COMMITTEE_LEVEL_ID,committeeLevelValueList,null,null,new ArrayList<String>(monthlyWiseMeetingsMap.keySet()));
										if(locationWisePartyMeetingsList != null && locationWisePartyMeetingsList.size()>0)
										{
											for (Object[] partyMeeting : locationWisePartyMeetingsList) {
												
												String dateStr = commonMethodsUtilService.getStringValueForObject(partyMeeting[0]);  
												Long totalMeetings = commonMethodsUtilService.getLongValueForObject(partyMeeting[1]);
												
												MeetingTrackingVO meetingsVO = null;
												if(monthlyWiseMeetingsMap.get(dateStr) != null)
												{
													meetingsVO = monthlyWiseMeetingsMap.get(dateStr);
													if(meetingsVO.getActualCount() != null && meetingsVO.getActualCount().longValue()>0L)
														totalMeetings = totalMeetings+meetingsVO.getActualCount().longValue();
													meetingsVO.setActualCount(totalMeetings);
													monthlyWiseMeetingsMap.put(dateStr, meetingsVO);
												}
											}
										}
									}
							}
							else if(searchTypeStr.equalsIgnoreCase("state"))							
							{							
								//committeeLevelValueList.add(committeeLevelValue);
								//getPartyMeetingsConductedDetails(IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID, committeeLevelValueList, fromDate, toDate, isFirst, null, null,monthlyWiseMeetingsMap,firstRecord,maxResult);
								locationWisePartyMeetingsList = partyMeetingDAO.getMontlyWiseMeetingsDetails(IConstants.STATE_COMMITTEE_LEVEL_ID,committeeLevelValueList,fromDate,toDate,null,firstRecord,maxResult);
								if(locationWisePartyMeetingsList != null && locationWisePartyMeetingsList.size()>0)
								{
									for (Object[] partyMeeting : locationWisePartyMeetingsList) {
										
										String dateStr = commonMethodsUtilService.getStringValueForObject(partyMeeting[0]); 
										Long totalMeetings = commonMethodsUtilService.getLongValueForObject(partyMeeting[1]);
										
										MeetingTrackingVO meetingsVO = new MeetingTrackingVO();
										meetingsVO.setTotalCount(0L);
										meetingsVO.setActualCount(0L);
										if(monthlyWiseMeetingsMap.get(dateStr) != null)
										{
											meetingsVO = monthlyWiseMeetingsMap.get(dateStr);
											if(meetingsVO.getTotalCount() != null && meetingsVO.getTotalCount().longValue()>0L)
												totalMeetings = totalMeetings+meetingsVO.getTotalCount().longValue();
										}
										meetingsVO.setTotalCount(totalMeetings);
										monthlyWiseMeetingsMap.put(dateStr, meetingsVO);
									}
								}
								
								if(conductedMeetingsCount != null && conductedMeetingsCount.longValue()>0L)
								{
									locationWisePartyMeetingsList = partyMeetingAttendanceDAO.getMontlyWiseMeetingsDetails(IConstants.STATE_COMMITTEE_LEVEL_ID, committeeLevelValueList, fromDate, toDate, new ArrayList<String>(monthlyWiseMeetingsMap.keySet()));
									if(locationWisePartyMeetingsList == null || locationWisePartyMeetingsList.size()==0)					
										locationWisePartyMeetingsList = partyMeetingIvrStatusDAO.getMontlyWiseMeetingsDetails(IConstants.STATE_COMMITTEE_LEVEL_ID,committeeLevelValueList,null,null,new ArrayList<String>(monthlyWiseMeetingsMap.keySet()));
									if(locationWisePartyMeetingsList != null && locationWisePartyMeetingsList.size()>0)
									{
										for (Object[] partyMeeting : locationWisePartyMeetingsList) {
											
											String dateStr = commonMethodsUtilService.getStringValueForObject(partyMeeting[0]);  
											Long totalMeetings = commonMethodsUtilService.getLongValueForObject(partyMeeting[1]);
											
											MeetingTrackingVO meetingsVO = null;
											if(monthlyWiseMeetingsMap.get(dateStr) != null)
											{
												meetingsVO = monthlyWiseMeetingsMap.get(dateStr);
												if(meetingsVO.getActualCount() != null && meetingsVO.getActualCount().longValue()>0L)
													totalMeetings = totalMeetings+meetingsVO.getActualCount().longValue();
												meetingsVO.setActualCount(totalMeetings);
												monthlyWiseMeetingsMap.put(dateStr, meetingsVO);
											}
										}
									}
								}
							}
						}
					}
					
					Map<String,MeetingTrackingVO> returnListMap = new LinkedHashMap<String, MeetingTrackingVO>(0);
					if(monthlyWiseMeetingsMap != null && monthlyWiseMeetingsMap.size()>0)
					{
						for (String dateStr : monthlyWiseMeetingsMap.keySet()) {
							MeetingTrackingVO meetingVO = monthlyWiseMeetingsMap.get(dateStr);
							if(meetingVO != null)
							{			
								String[] dateArr = dateStr.split("-");
								if(dateArr != null && dateArr.length>0)
								{
									int day = Integer.valueOf(dateArr[1]);
									meetingVO.setMonthName(IConstants.MONTH_NAMES[day-1]+" - "+dateArr[0]);
								}
								
								if(returnListMap.get(meetingVO.getMonthName()) != null)
								{
									MeetingTrackingVO existingMeetingVO = returnListMap.get(meetingVO.getMonthName());
									if(existingMeetingVO.getTotalCount() != null && existingMeetingVO.getTotalCount().longValue()>0L)
									{
										Long totalMeetings = existingMeetingVO.getTotalCount().longValue()+meetingVO.getTotalCount().longValue();
										meetingVO.setTotalCount(totalMeetings);
									}
										
									if(existingMeetingVO.getActualCount() != null && existingMeetingVO.getActualCount().longValue()>0L)
									{
										Long actualMeetingsCount = existingMeetingVO.getActualCount().longValue()+meetingVO.getActualCount().longValue();
										meetingVO.setActualCount(actualMeetingsCount);
									}
								}
								returnListMap.put(meetingVO.getMonthName(), meetingVO);
							}
						} 
					}
					
					if(returnListMap != null && returnListMap.size()>0)
					{
						for (String monthYear : returnListMap.keySet()) {
							returnVO.getMeetingTrackingVOList().add(returnListMap.get(monthYear));
						}
					}
					returnVO.setTotalCount(availableMeetingsCount);
					returnVO.setActualCount(conductedMeetingsCount);
				}	
		} catch (Exception e) {
			LOG.error("Exception Occured in getPartyMeetingsDetailsForCadreByCommitteeLevel() method, Exception - ",e);
		}
		
		return returnVO;
	}

	public PartyMeetingWSVO getAttendedDetailsForPartyMeeting(Long partyMeetingId){
		
		PartyMeetingWSVO partyMeetingWSVo = new PartyMeetingWSVO();
		
		try {
			Long attendedCount = 0l;
			Long invitedCount = 0l;
			Long inviteesAttendedCount = 0l;
			Long nonInviteesCount = 0l;
			Long absentCount = 0l;
			
			List<Long> inviteesList = partyMeetingInviteeDAO.getInvitedCadreIdsByPartyMeetingId(partyMeetingId);
			List<Long> attendedList = partyMeetingAttendanceDAO.getAttendedCadreIdsByPartyMeetingId(partyMeetingId);
			if(attendedList != null && attendedList.size() > 0){
				attendedCount = (long) attendedList.size();
			}
			
			if(attendedList != null && attendedList.size() > 0){
				if(inviteesList != null && inviteesList.size() > 0){
					invitedCount = (long) inviteesList.size();
					for (Long cadreId : attendedList) {
						if(inviteesList.contains(cadreId)){
							inviteesAttendedCount = inviteesAttendedCount+1l;
						}
						else{
							nonInviteesCount = nonInviteesCount+1l;
						}
					}
				}
				else{
					nonInviteesCount = (long) attendedList.size();
				}
			}
			
			absentCount = invitedCount - inviteesAttendedCount;
			
			partyMeetingWSVo.setAttendedCount(attendedCount);
			partyMeetingWSVo.setInviteesCount(invitedCount);
			partyMeetingWSVo.setInviteesAttendedCount(inviteesAttendedCount);
			partyMeetingWSVo.setNonInviteesAttendedCount(nonInviteesCount);
			partyMeetingWSVo.setAbsentCount(absentCount);
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getAttendedDetailsForPartyMeeting() method, Exception - ",e);
		}
		return partyMeetingWSVo;
	}
	
	public PartyMeetingWSVO getTdpCadreDetailsForPartyMeeting(Long partyMeetingId,String searchType,List<String> designationsList){
		
		PartyMeetingWSVO finalvo = new PartyMeetingWSVO();
		
		try {
			
			List<PartyMeetingWSVO> partyMeetingWSVoList = new ArrayList<PartyMeetingWSVO>();
			Map<Long,PartyMeetingWSVO> partyMeetingVoMap = new LinkedHashMap<Long, PartyMeetingWSVO>();
			Map<Long,PartyMeetingWSVO> finalPPartyMeetingVoMap = new LinkedHashMap<Long, PartyMeetingWSVO>();
			//Map<String,Long> designationWiseMap = new LinkedHashMap<String, Long>();
			Map<String,PartyMeetingWSVO> designationMap = new LinkedHashMap<String, PartyMeetingWSVO>();
			Map<Long,String> constituencyMap = new LinkedHashMap<Long, String>();
			List<PartyMeetingWSVO> designationWiseCountsList = new ArrayList<PartyMeetingWSVO>();
			Set<Long> partyMembersTdpCadreIdsList = new java.util.HashSet<Long>();
			List<Long> inviteesPresentList = new ArrayList<Long>();
			List<Long> nonInviteesPresentList = new ArrayList<Long>();
			List<Long> absentList = new ArrayList<Long>();
			List<Long> inviteesList = partyMeetingInviteeDAO.getInvitedCadreIdsByPartyMeetingId(partyMeetingId);
			List<Long> attendedList = partyMeetingAttendanceDAO.getAttendedCadreIdsByPartyMeetingId(partyMeetingId);
			
			List<Long> tdpCadreIdsList = new ArrayList<Long>();
			
			if(attendedList != null && attendedList.size() > 0){
				if(inviteesList != null && inviteesList.size() > 0){
					for (Long cadreId : attendedList) {
						if(inviteesList.contains(cadreId)){
							inviteesPresentList.add(cadreId);
						}
						else{
							nonInviteesPresentList.add(cadreId);
						}
					}
				}
				else{
					nonInviteesPresentList = attendedList;
				}
			}
			
			if(inviteesList != null && inviteesList.size() > 0){
				if(inviteesPresentList != null && inviteesPresentList.size() > 0){
					for (Long inviteeId : inviteesList) {
						if(!(inviteesPresentList.contains(inviteeId))){
							absentList.add(inviteeId);
						}
					}
				}
				else{
					absentList = inviteesList;
				}
			}
			
			if(searchType.equalsIgnoreCase("TP")){
				tdpCadreIdsList = attendedList;
			}
			else if(searchType.equalsIgnoreCase("TI")){
				tdpCadreIdsList = inviteesList;
			}
			else if(searchType.equalsIgnoreCase("IP")){
				tdpCadreIdsList = inviteesPresentList;
			}
			else if(searchType.equalsIgnoreCase("NI")){
				tdpCadreIdsList = nonInviteesPresentList;
			}
			else if(searchType.equalsIgnoreCase("AB")){
				tdpCadreIdsList = absentList;
			}
			
			if(tdpCadreIdsList == null || tdpCadreIdsList.size() == 0)
			{
				if(inviteesList != null && inviteesList.size()>0)
					tdpCadreIdsList.addAll(inviteesList);
			}
			if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			{
				List<Object[]> candidateDetails = publicRepresentativeDAO.getCandidateDetailsByCandidateId(tdpCadreIdsList);
				if(candidateDetails != null && candidateDetails.size() > 0){
					for (Object[] obj : candidateDetails) {
						Long cadreId = Long.valueOf(obj[0] != null ? obj[0].toString():"0L");
						Long constituencyId = Long.valueOf(obj[5] != null ? obj[5].toString():"0L");
						String constituencyName = constituencyDAO.getConstituencyNameByConstituencyId(constituencyId);
						
						constituencyMap.put(cadreId, constituencyName);
					}
				}
				
				//0.tdpCadreId,1.firstname,2.dateOfBirth,3.age,4.mobileNo,5.image,6.memberShipNo
				List<Object[]> cadreDetails = tdpCadreDAO.getCadreFormalDetails(tdpCadreIdsList);
				if(cadreDetails != null && cadreDetails.size() > 0){
					for (Object[] obj : cadreDetails) {
						PartyMeetingWSVO vo = new PartyMeetingWSVO();
						
						Long cadreId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
						String image = obj[5] != null ? obj[5].toString():"";
						vo.setTdpCadreId(cadreId);
						vo.setName(obj[1] != null ? obj[1].toString():"");
						vo.setDateOfBirth(obj[2] != null ? obj[2].toString():"");
						vo.setAge(Long.valueOf(obj[3] != null ? obj[3].toString():"0"));
						//vo.setMobileNo(obj[4] != null ? obj[4].toString():"");
						vo.setImgStr("http://mytdp.com/images/"+IConstants.CADRE_IMAGES+"/"+image+"");
						vo.setMemberShipNo(obj[6] != null ? obj[6].toString():"");
						vo.setOwnConstituency(obj[8] != null ? obj[8].toString():"");
						
						String constituencyName = constituencyMap.get(cadreId);
						if(constituencyName != null && constituencyName.trim().length() > 0){
							vo.setParticipatedConstituency(constituencyName);
						}
						
						partyMeetingVoMap.put(cadreId, vo);
					}
				}
				
				//0.publicRepresentativeTypeId,1.type,2.tdpCadreId
				List<Object[]> publicRepDetails = tdpCadreCandidateDAO.getPublicRepresentativeDetailsForCadreIds(tdpCadreIdsList);
				if(publicRepDetails != null && publicRepDetails.size() > 0){
					for (Object[] obj : publicRepDetails) {
						PartyMeetingWSVO vo1 = new PartyMeetingWSVO();
						Long cadreId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
						String type = obj[1] != null ? obj[1].toString():"";
						
						PartyMeetingWSVO vo = null;
						if(designationsList != null && designationsList.size()>0){
							if(designationsList.size() == 1 && designationsList.contains("OTHERS"))
								partyMeetingVoMap.remove(cadreId);
							else if(designationsList.contains(type)){
								vo = partyMeetingVoMap.get(cadreId);
							}
							else
							{
								if(designationsList.contains("OTHERS")){
									partyMeetingVoMap.remove(cadreId);
								}
							}
						}
						else{
							vo = partyMeetingVoMap.get(cadreId);
						}
						
						if(vo != null )
						{

							if(inviteesList.contains(cadreId)){
								if(attendedList.contains(cadreId))
									vo.setMemberType("Invitee Present");
								else
									vo.setMemberType("Invitee Absent");
							}
							else if(attendedList.contains(cadreId)){
								vo.setMemberType("Non Invitee Present");
							}
								
							if(vo.getDesignation() != null && !vo.getDesignation().isEmpty())
							{
								vo.setDesignation(vo.getDesignation()+","+type);
								vo.setRoles(vo.getRoles()+","+type);
							}
							else{
								vo.setDesignation(type);
								vo.setRoles(type);
							}
							
							PartyMeetingWSVO desgvo = designationMap.get(type.trim());
							if(searchType.equalsIgnoreCase("TP")){
								if(desgvo != null){
									desgvo.setCount(desgvo.getCount()+1l);
									if(inviteesList.contains(cadreId)){
										desgvo.setInviteesCount(Long.valueOf(desgvo.getInviteesCount().toString())+1l);
									}
									else{
										desgvo.setNonInviteesAttendedCount(Long.valueOf(desgvo.getNonInviteesAttendedCount().toString())+1l);
									}
								}
								else{
									vo1.setDesignation(type);
									vo1.setCount(1l);
									if(inviteesList.contains(cadreId)){
										vo1.setInviteesCount(1l);
									}
									else{
										vo1.setNonInviteesAttendedCount(1l);
									}
									designationMap.put(type.trim(), vo1);
								}
							}
							else if(searchType.equalsIgnoreCase("TI")){
								if(desgvo != null){
									desgvo.setCount(desgvo.getCount()+1l);
									if(attendedList.contains(cadreId)){
										desgvo.setInviteesAttendedCount(Long.valueOf(desgvo.getInviteesAttendedCount().toString())+1l);
									}
									else{
										desgvo.setAbsentCount(Long.valueOf(desgvo.getAbsentCount().toString())+1l);
									}
								}
								else{
									vo1.setDesignation(type);
									vo1.setCount(1l);
									if(attendedList.contains(cadreId)){
										vo1.setInviteesAttendedCount(1l);
									}
									else{
										vo1.setAbsentCount(1l);
									}
									designationMap.put(type.trim(), vo1);
								}
							}
							else{
								if(desgvo != null){
									desgvo.setCount(desgvo.getCount()+1l);
								}
								else{
									vo1.setDesignation(type);
									vo1.setCount(1l);
									
									designationMap.put(type.trim(), vo1);
								}
							}
							
							/*Long count = designationWiseMap.get(type.trim());
							if(count != null && count.longValue() > 0L){
								count = count++;
							}
							else{
								designationWiseMap.put(type.trim(), 1l);
							}*/
							
							partyMembersTdpCadreIdsList.add(commonMethodsUtilService.getLongValueForObject(obj[2]));
						}
					}
				}
				
				 List<Object[]> partyPositionDetails= tdpCommitteeMemberDAO.getPartyPositionsBycadreIdsList(tdpCadreIdsList);
				 if(partyPositionDetails !=null && partyPositionDetails.size()>0)
				 {
					 for (Object[] partyPosition : partyPositionDetails) {
						 PartyMeetingWSVO vo1 = new PartyMeetingWSVO();
						 
						 String level=partyPosition[0] != null ? partyPosition[0].toString() : "" ;
						 String role=partyPosition[1] != null ? partyPosition[1].toString() : "";
						 String state = commonMethodsUtilService.getStringValueForObject(partyPosition[6]);
						 Long cadreId = Long.valueOf(partyPosition[5] != null ? partyPosition[5].toString() : "0"); 
						 String commiteestr=partyPosition[2] != null ? partyPosition[2].toString() : "";
						 
						 if(level != null && !level.isEmpty()&&level.equalsIgnoreCase("state"))
						 {
							 level = state+" "+level;
						 }
						 String partyPositionStr = level +" " +role+" ( "+commiteestr+" )";
						 
						 PartyMeetingWSVO vo = null;
							if(designationsList != null && designationsList.size()>0){
								if(designationsList.size() == 1 && designationsList.contains("OTHERS"))
									partyMeetingVoMap.remove(cadreId);
								else if(designationsList.contains(role.trim())){
									vo = partyMeetingVoMap.get(cadreId);
								}
							}
							else{
								vo = partyMeetingVoMap.get(cadreId);
							}
							
							if(vo != null )
							{
								if(inviteesList.contains(cadreId)){
									if(attendedList.contains(cadreId))
										vo.setMemberType("Invitee Present");
									else
										vo.setMemberType("Invitee Absent");
								}
								else if(attendedList.contains(cadreId)){
									vo.setMemberType("Non Invitee Present");
								}
							 
							 if(vo.getDesignation() != null && vo.getDesignation().length() > 0){
								 vo.setDesignation(vo.getDesignation()+" , "+partyPositionStr);
								 vo.setRoles(vo.getRoles().trim()+","+role.trim());
							 }
							 else{
								 vo.setDesignation(partyPositionStr);
								 vo.setRoles(role.trim());
							 }
							 
							 PartyMeetingWSVO desgvo = designationMap.get(role.trim());
							if(searchType.equalsIgnoreCase("TP")){
								if(desgvo != null){
									desgvo.setCount(desgvo.getCount()+1l);
									if(inviteesList.contains(cadreId)){
										desgvo.setInviteesCount(Long.valueOf(desgvo.getInviteesCount().toString())+1l);
									}
									else{
										desgvo.setNonInviteesAttendedCount(Long.valueOf(desgvo.getNonInviteesAttendedCount().toString())+1l);
									}
								}
								else{
									vo1.setDesignation(role);
									vo1.setCount(1l);
									if(inviteesList.contains(cadreId)){
										vo1.setInviteesCount(1l);
									}
									else{
										vo1.setNonInviteesAttendedCount(1l);
									}
									designationMap.put(role.trim(), vo1);
								}
							}
							else if(searchType.equalsIgnoreCase("TI")){
								if(desgvo != null){
									desgvo.setCount(desgvo.getCount()+1l);
									if(attendedList.contains(cadreId)){
										desgvo.setInviteesAttendedCount(Long.valueOf(desgvo.getInviteesAttendedCount().toString())+1l);
									}
									else{
										desgvo.setAbsentCount(Long.valueOf(desgvo.getAbsentCount().toString())+1l);
									}
								}
								else{
									vo1.setDesignation(role);
									vo1.setCount(1l);
									if(attendedList.contains(cadreId)){
										vo1.setInviteesAttendedCount(1l);
									}
									else{
										vo1.setAbsentCount(1l);
									}
									designationMap.put(role.trim(), vo1);
								}
							}
							else{
								if(desgvo != null){
									desgvo.setCount(desgvo.getCount()+1l);
								}
								else{
									vo1.setDesignation(role);
									vo1.setCount(1l);
									
									designationMap.put(role.trim(), vo1);
								}
							}
							/* Long count = designationWiseMap.get(role.trim());
							 if(count != null && count.longValue() > 0L){
								count = count++;
							}
							else{
								designationWiseMap.put(role.trim(), 1l);
							}*/
							 
							 partyMembersTdpCadreIdsList.add(commonMethodsUtilService.getLongValueForObject(partyPosition[5]));
							}
					 }
				 }
				 
				 int otherMembersCount = 0;
				 
				 if(designationsList == null || designationsList.size() == 0){
					 
					 if(partyMembersTdpCadreIdsList != null && partyMembersTdpCadreIdsList.size()>0) 
						 otherMembersCount = tdpCadreIdsList.size() - partyMembersTdpCadreIdsList.size();
					 else 
						 otherMembersCount = tdpCadreIdsList.size();
					 
					 if(partyMembersTdpCadreIdsList != null && partyMembersTdpCadreIdsList.size()>0) {
						 if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0) {
							 for (Long cadreId : tdpCadreIdsList) {
								if(!partyMembersTdpCadreIdsList.contains(cadreId)){
									PartyMeetingWSVO vo = partyMeetingVoMap.get(cadreId);
									if(vo != null)
									{
										vo.setDesignation("OTHERS");
										vo.setRoles("OTHERS");
										if(inviteesList.contains(cadreId)){
											if(attendedList.contains(cadreId))
												vo.setMemberType("Invitee Present");
											else
												vo.setMemberType("Invitee Absent");
										}
										else if(attendedList.contains(cadreId)){
											vo.setMemberType("Non Invitee Present");
										}
									}
								}
							}
						 }
					 }
					 else  if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0){
						 if(partyMeetingVoMap != null && partyMeetingVoMap.size()>0)
						 {
							 for (Long tdpCadreId : partyMeetingVoMap.keySet()) {
								 PartyMeetingWSVO vo = partyMeetingVoMap.get(tdpCadreId);
								 if(vo != null){
									 if(vo.getDesignation() != null && !vo.getDesignation().isEmpty()){
										 vo.setDesignation(vo.getDesignation());
										 vo.setRoles(vo.getDesignation());
										 
										 if(!inviteesList.contains(tdpCadreId))
											 vo.setMemberType("Non Invitee Present");
									 }
									 else{
										 vo.setDesignation("OTHERS");
										 vo.setRoles("OTHERS");
										 
										 if(!inviteesList.contains(tdpCadreId))
											 vo.setMemberType("Non Invitee Present");
									 }
								 }
									
							}
						 }
					 }
					 if(otherMembersCount >0){
						 PartyMeetingWSVO vo = new PartyMeetingWSVO();
						 vo.setInviteesCount(0L);
						 vo.setNonInviteesAttendedCount(0L);
						 
						 for (Long cadreId : partyMeetingVoMap.keySet()) {
							 PartyMeetingWSVO cadreVO = partyMeetingVoMap.get(cadreId);
							 if(cadreVO.getRoles() != null && cadreVO.getRoles().trim().length()>0 && cadreVO.getRoles().trim().equalsIgnoreCase("OTHERS"))
								if(inviteesList.contains(cadreId))
									vo.setInviteesCount(Long.valueOf(vo.getInviteesCount().toString())+1l);							
								else
									vo.setNonInviteesAttendedCount(Long.valueOf(vo.getNonInviteesAttendedCount().toString())+1l);
						}
						
						 vo.setDesignation("OTHERS");
						 vo.setCount(Long.valueOf(String.valueOf(otherMembersCount)));
						 designationMap.put("OTHERS", vo);
					 }
				 }
				 else{
					 
					 if(partyMembersTdpCadreIdsList != null && partyMembersTdpCadreIdsList.size()>0)
					 {
						 for (Long tdpCadreId : partyMeetingVoMap.keySet()) {
							if(partyMembersTdpCadreIdsList.contains(tdpCadreId))
								finalPPartyMeetingVoMap.put(tdpCadreId,partyMeetingVoMap.get(tdpCadreId));
						}
					 }
					 else
					 {
						 if(partyMeetingVoMap != null && partyMeetingVoMap.size()>0)
						 {
							 for (Long tdpCadreId : partyMeetingVoMap.keySet()) {
								PartyMeetingWSVO vo = partyMeetingVoMap.get(tdpCadreId);
								if(vo != null)
								{
									if(!inviteesList.contains(tdpCadreId))
										vo.setMemberType("Non Invitee Present");
									else 
										vo.setMemberType("Invitee Present");
									
									if(vo.getDesignation()!= null)
										vo.setDesignation(vo.getDesignation());
									else
										vo.setDesignation("OTHERS");
								}
							}
						 }
					 }
				 }
				 designationWiseCountsList.addAll(designationMap.values());
				 
				 //designationWiseMap.put("OTHERS".trim(), Long.valueOf(String.valueOf(otherMembersCount)));
				 
				 /*for (Map.Entry<String, Long> entry : designationWiseMap.entrySet()) {
					    
					 String key = entry.getKey();
					 Long value = entry.getValue();
					    
					 PartyMeetingWSVO vo = new PartyMeetingWSVO();   
					    
					 vo.setDesignation(key);
					 vo.setCount(value);
					 
					 designationWiseCountsList.add(vo);
					}*/
				 
				 if(finalPPartyMeetingVoMap != null && finalPPartyMeetingVoMap.size()>0)
					 partyMeetingWSVoList.addAll(finalPPartyMeetingVoMap.values());
				 else
					 partyMeetingWSVoList.addAll(partyMeetingVoMap.values());
				 
				Long attendedCount = 0l;
				Long invitedCount = 0l;
				Long inviteesAttendedCount = 0l;
				Long nonInviteesCount = 0l;
				Long absentCount = 0l;
				
				if(attendedList != null && attendedList.size() > 0){
					attendedCount = (long) attendedList.size();
				}
				
				if(attendedList != null && attendedList.size() > 0){
					if(inviteesList != null && inviteesList.size() > 0){
						invitedCount = (long) inviteesList.size();
						for (Long cadreId : attendedList) {
							if(inviteesList.contains(cadreId)){
								inviteesAttendedCount = inviteesAttendedCount+1l;
							}
							else{
								nonInviteesCount = nonInviteesCount+1l;
							}
						}
					}
					else{
						nonInviteesCount = (long) attendedList.size();
					}
				}
				
				absentCount = invitedCount - inviteesAttendedCount;
				
				finalvo.setAttendedCount(attendedCount);
				finalvo.setInviteesCount(invitedCount);
				finalvo.setInviteesAttendedCount(inviteesAttendedCount);
				finalvo.setNonInviteesAttendedCount(nonInviteesCount);
				finalvo.setAbsentCount(absentCount);
				finalvo.setPartyMeetingWSVoList(partyMeetingWSVoList);
				finalvo.setDesignationWiseCountsList(designationWiseCountsList);
			}
			
		} catch (Exception e) {
			LOG.error("Exception Occured in getTdpCadreDetailsForPartyMeeting() method, Exception - ",e);
		}
		return finalvo;
	}
	
	public ResultStatus updateAttendanceAcceptedMemberDetails( final PartyMeetingWSVO inputvo){
		
		ResultStatus status = new ResultStatus();
		try {
			
			status = (ResultStatus) transactionTemplate.execute(new TransactionCallback() {
				public ResultStatus doInTransaction(TransactionStatus arg0) {
					ResultStatus resultStatus = new ResultStatus();
					if(inputvo.getId() != null && inputvo.getId().longValue()>0L && inputvo.getTdpCadreId() != null && inputvo.getTdpCadreId().longValue()>0L){
						
						TabDetails tabDetails = new TabDetails();
						
						tabDetails.setAttendedTime(inputvo.getAttendedTime());
						tabDetails.setImei(inputvo.getImei());
						tabDetails.setUniqueKey(inputvo.getUniqueKey());
						tabDetails.setInsertedTime(inputvo.getInsertedTime());
						tabDetails.setLatitude(inputvo.getLatitude());
						tabDetails.setLongitude(inputvo.getLongitude());
						tabDetails.setTabUserId(inputvo.getTabUserId());
						tabDetails.setSyncSource("WS");
						tabDetails.setTabPrimaryKey(inputvo.getTabPrimaryKey());
						tabDetails.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						
						tabDetails = tabDetailsDAO.save(tabDetails);
						
						ActivityAttendanceAcceptance activityAttendanceAcceptance = new ActivityAttendanceAcceptance();
						activityAttendanceAcceptance.setTdpCadreId(inputvo.getTdpCadreId());
						activityAttendanceAcceptance.setActivityValue(inputvo.getId());
						if(inputvo.getStatus() != null && inputvo.getStatus().trim().length()>0){
							if(inputvo.getStatus().trim().equalsIgnoreCase("R"))
								activityAttendanceAcceptance.setApproveType("REJECTED");
							else if(inputvo.getStatus().trim().equalsIgnoreCase("A"))
								activityAttendanceAcceptance.setApproveType("ACCEPTED");
						}
						activityAttendanceAcceptance.setInsertedBy(1L);
						activityAttendanceAcceptance.setUpdatedBy(1L);
						activityAttendanceAcceptance.setInsertedTime(new DateUtilService().getCurrentDateAndTime());
						activityAttendanceAcceptance.setUpdatedTime(new DateUtilService().getCurrentDateAndTime());
						activityAttendanceAcceptanceDAO.save(activityAttendanceAcceptance);
					}
					
					resultStatus.setMessage("SUCCESS");
					return resultStatus;
				}
			});
			
			
		} catch (Exception e) {
			LOG.error("Exception Occured in updateAttendanceAcceptedMemberDetails() method, Exception - ",e);
			status.setMessage("FAILURE");
		}
		
		return status;
	}
	
	public List<PartyMeetingVO> getLevelWiseMeetingDetails(String fromDateStr,String toDateStr,Long userId,Long stateId){
		
		List<PartyMeetingVO> finalList =null;
		
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			Date startDate =null;
			Date endDate =null;
			
			if(fromDateStr !=null && toDateStr !=null){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			
			
			/*List<Object[]> levelsObj =  partyMeetingLevelDAO.getPartyMeetingLevels();
			
			//Map<Long,PartyMeetingVO> levelMap = new HashMap<Long, PartyMeetingVO>();
			
			if(levelsObj !=null && levelsObj.size()>0){
				for (Object[] objects : levelsObj) {
					
					PartyMeetingVO VO = new PartyMeetingVO();
					
					VO.setId(objects[0] !=null ? (Long)objects[0]:0l);
					VO.setName(objects[1] !=null ? objects[1].toString():"");
					
					VO.setInvitedCount(0l);//conductedCount
					VO.setNonInviteeCount(0l);//notConductedCount
					VO.setAttendedCount(0l);//OverAll Planned Count
					
					levelMap.put(VO.getId(), VO);
					
				}
			}*/
			
			Map<Long,PartyMeetingVO> levelMap = new HashMap<Long, PartyMeetingVO>();
			Map<String,List<Long>> userLevelValuesMap = new HashMap<String, List<Long>>();
			
			//GETTING USER ACCESS LEVELS
			List<Object[]> allLevels = partyMeetingUserAccessLevelDAO.getrAccessLevelsOfUserId(userId);
			if(allLevels!=null && allLevels.size()>0){
				for(Object[] obj:allLevels){
					PartyMeetingVO mv = new PartyMeetingVO();
					mv.setId(Long.valueOf(obj[0].toString()));
					mv.setName(obj[1].toString());
					
					
					mv.setInvitedCount(0l);//conductedCount
					mv.setNonInviteeCount(0l);//notConductedCount
					mv.setAttendedCount(0l);//OverAll Planned Count
					
					levelMap.put(mv.getId(), mv);
				}
			}
			
			//GETTING USER ACCESS LOCATIONS
			List<Long> levelValues = new ArrayList<Long>(0);
			List<String> levelStr=new ArrayList<String>(0);
			String level="";
			Map<Long,List<Long>> levelMapFinal  = new HashMap<Long, List<Long>>();
			List<Object[]> allLevelValues = userAccessLevelValueDAO.getAccessValuesOfUserId(userId);
			if(allLevelValues!=null && allLevelValues.size()>0){
				/*
				for(Object[] obj:allLevelValues){
					List<Long> locationIds = userLevelValuesMap.get(Long.valueOf(obj[0].toString()));
					if(locationIds==null){
						locationIds = new ArrayList<Long>();
					}
					locationIds.add(Long.valueOf(obj[1].toString()));
					userLevelValuesMap.put(Long.valueOf(obj[0].toString()), locationIds);
				}*/
				
				for(Object[] obj:allLevelValues){
					
					List<Long> locationIds = userLevelValuesMap.get(obj[2] !=null ? obj[2].toString():"");
					if(locationIds==null){
						locationIds = new ArrayList<Long>();
						userLevelValuesMap.put(obj[2].toString(), locationIds);
					}
					locationIds.add(Long.valueOf(obj[1].toString()));
					
					
					/*levelValues.add(obj[1] !=null ? Long.valueOf(obj[1].toString()):0l);
					levelStr.add(obj[2] !=null ? obj[2].toString():"");*/
					
				}
			}
			if(userLevelValuesMap !=null && userLevelValuesMap.size()>0){
				
				Map.Entry<String, List<Long>> entry = userLevelValuesMap.entrySet().iterator().next();				
				level=entry.getKey();
				levelValues = entry.getValue();
				
			}
			
			if(stateId.longValue() > 0l){
				levelValues.clear();
				levelValues.add(stateId);
			}
			
			List<Object[]> conductedObj = partyMeetingDAO.getLevelWiseMeetingDetails(startDate,endDate,level,levelValues);
			
			if(conductedObj !=null && conductedObj.size()>0){
				for(Object[] obj : conductedObj){
					
					PartyMeetingVO inVo = new PartyMeetingVO();
					if(obj[0] !=null && ((Long)obj[0] == 4l || (Long)obj[0] == 5l || (Long)obj[0] == 6l) ){
						inVo = levelMap.get(4l);
					}else if(obj[0] !=null && ((Long)obj[0] ==7l || (Long)obj[0] == 8l)){
						inVo = levelMap.get(7l);
					}else{
						inVo = levelMap.get((Long)obj[0]);
					}
					
					/*if(inVo == null){
						inVo = new PartyMeetingVO();
						levelMap.put((Long)obj[0], inVo);
					}*/
					
					if(inVo !=null){
						if(obj[2] !=null){
							if(!obj[2].toString().isEmpty() && obj[2].toString().equalsIgnoreCase("Y")){
								inVo.setInvitedCount(inVo.getInvitedCount() + (obj[3] !=null ? (Long)obj[3]:0l));
							}else if(!obj[2].toString().isEmpty() && obj[2].toString().equalsIgnoreCase("N")){
								inVo.setNonInviteeCount(inVo.getNonInviteeCount() + (obj[3] !=null ? (Long)obj[3]:0l));
							}
						}else{
							inVo.setAttendedCount(inVo.getAttendedCount() +  (obj[3] !=null ? (Long)obj[3]:0l));
						}
					}
					
					
				}
			}
			
			if(levelMap !=null && levelMap.size()>0){
					finalList = new ArrayList<PartyMeetingVO>(levelMap.values());
			}
		
			
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getLevelWiseMeetingDetails() method, Exception - ",e);
		}
		return finalList;
	}
	
	public String updateConductedDetails(Long meetingId,String isConducted,String remarks,String conductedDate){
		String returnStr = null;
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
			
			Date cdDate = null;
			if(conductedDate !=null && !conductedDate.isEmpty()){
				cdDate = sdf.parse(conductedDate);
			}
			
		int value =	partyMeetingDAO.updateConductedDetails(meetingId,isConducted,remarks,cdDate);
		if(value>0){
			returnStr = "success";
		}else{
			returnStr = "failure";
		}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in updateConductedDetails() method, Exception - ",e);
		}
		return returnStr;
	}
	public String updateConductedStatus(Long meetingId,String isConducted,Long userId){
		String returnStr = null;
		try{
			
		Date date = new DateUtilService().getCurrentDateAndTime();
		
		if(isConducted !=null && isConducted.equalsIgnoreCase("0")){
			isConducted = null;
		}
		
		int value =	partyMeetingDAO.updateConductedStatus(meetingId,isConducted,userId,date);
		if(value>0){
			returnStr = "success";
		}else{
			returnStr = "failure";
		}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in updateConductedStatus() method, Exception - ",e);
		}
		return returnStr;
	}
	
	public String updateConductedDate(Long meetingId,String conductedDate,Long userId){
		String returnStr = null;
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			
			Date cdDate = null;
			if(conductedDate !=null && !conductedDate.isEmpty()){
				cdDate = sdf.parse(conductedDate);
			}
			
			Date date = new DateUtilService().getCurrentDateAndTime();
			
		int value =	partyMeetingDAO.updateConductedDate(meetingId,cdDate,userId,date);
		if(value>0){
			returnStr = "success";
		}else{
			returnStr = "failure";
		}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in updateConductedDate() method, Exception - ",e);
		}
		return returnStr;
	}
	public String updateConductedReason(Long meetingId,String remarks,Long userId){
		String returnStr = null;
		try{			
		
		Date date = new DateUtilService().getCurrentDateAndTime();
		
		if(remarks ==null || remarks.isEmpty()){
			remarks=null;
		}
		
		int value =	partyMeetingDAO.updateConductedReason(meetingId,remarks,userId,date);
		if(value>0){
			returnStr = "success";
		}else{
			returnStr = "failure";
		}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in updateConductedReason() method, Exception - ",e);
		}
		return returnStr;
	}
	
}
