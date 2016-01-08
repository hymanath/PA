package com.itgrids.partyanalyst.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IActivityLocationAttendanceDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.IActivityLocationPublicAttendanceDAO;
import com.itgrids.partyanalyst.dao.IAttendanceDAO;
import com.itgrids.partyanalyst.dao.IAttendanceErrorDAO;
import com.itgrids.partyanalyst.dao.IAttendanceTabUserDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceTabUserDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceTabUserDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchAttendeeDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampBatchDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.hibernate.ActivityLocationInfoDAO;
import com.itgrids.partyanalyst.dao.hibernate.ActivityLocationPublicAttendanceDAO;
import com.itgrids.partyanalyst.dto.ActivityAttendanceVO;
import com.itgrids.partyanalyst.dto.AttendanceTabUserVO;
import com.itgrids.partyanalyst.dto.AttendanceVO;
import com.itgrids.partyanalyst.dto.PartyMeetingInviteeVO;
import com.itgrids.partyanalyst.dto.PartyMeetingLocationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.UserAttendanceDetailsVO;
import com.itgrids.partyanalyst.dto.UserPartyMeetingVO;
import com.itgrids.partyanalyst.dto.UserTrainingCampBatchVO;
import com.itgrids.partyanalyst.dto.UserTrainingCampScheduleVO;
import com.itgrids.partyanalyst.model.ActivityLocationAttendance;
import com.itgrids.partyanalyst.model.ActivityLocationInfo;
import com.itgrids.partyanalyst.model.ActivityLocationPublicAttendance;
import com.itgrids.partyanalyst.model.Attendance;
import com.itgrids.partyanalyst.model.AttendanceError;
import com.itgrids.partyanalyst.model.PartyMeeting;
import com.itgrids.partyanalyst.model.PartyMeetingAttendance;
import com.itgrids.partyanalyst.model.TrainingCampAttendance;
import com.itgrids.partyanalyst.model.TrainingCampBatch;
import com.itgrids.partyanalyst.model.TrainingCampSchedule;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IAttendanceService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class AttendanceService implements IAttendanceService{

	private static Logger LOG = Logger.getLogger(AttendanceService.class);
	
	private ITdpCadreDAO tdpCadreDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IAttendanceDAO attendanceDAO;
	private IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO;
	private ITrainingCampAttendanceDAO trainingCampAttendanceDAO;
	private IAttendanceErrorDAO attendanceErrorDAO;
	private IAttendanceTabUserDAO attendanceTabUserDAO;
	private IPartyMeetingAttendanceTabUserDAO partyMeetingAttendanceTabUserDAO;
	private ITrainingCampAttendanceTabUserDAO trainingCampAttendanceTabUserDAO;
	private ITrainingCampBatchDAO trainingCampBatchDAO;
	private IPartyMeetingInviteeDAO partyMeetingInviteeDAO;
	private IVoterDAO voterDAO;
	private ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO;
	private TransactionTemplate transactionTemplate = null;
	private IActivityLocationPublicAttendanceDAO activityLocationPublicAttendanceDAO;
	private IActivityLocationAttendanceDAO  activityLocationAttendanceDAO;
	

	public IActivityLocationAttendanceDAO getActivityLocationAttendanceDAO() {
		return activityLocationAttendanceDAO;
	}

	public void setActivityLocationAttendanceDAO(
			IActivityLocationAttendanceDAO activityLocationAttendanceDAO) {
		this.activityLocationAttendanceDAO = activityLocationAttendanceDAO;
	}

	public IActivityLocationPublicAttendanceDAO getActivityLocationPublicAttendanceDAO() {
		return activityLocationPublicAttendanceDAO;
	}

	public void setActivityLocationPublicAttendanceDAO(
			IActivityLocationPublicAttendanceDAO activityLocationPublicAttendanceDAO) {
		this.activityLocationPublicAttendanceDAO = activityLocationPublicAttendanceDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setTrainingCampBatchAttendeeDAO(
			ITrainingCampBatchAttendeeDAO trainingCampBatchAttendeeDAO) {
		this.trainingCampBatchAttendeeDAO = trainingCampBatchAttendeeDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	public void setPartyMeetingInviteeDAO(
			IPartyMeetingInviteeDAO partyMeetingInviteeDAO) {
		this.partyMeetingInviteeDAO = partyMeetingInviteeDAO;
	}

	public void setTrainingCampBatchDAO(ITrainingCampBatchDAO trainingCampBatchDAO) {
		this.trainingCampBatchDAO = trainingCampBatchDAO;
	}

	public void setTrainingCampAttendanceTabUserDAO(
			ITrainingCampAttendanceTabUserDAO trainingCampAttendanceTabUserDAO) {
		this.trainingCampAttendanceTabUserDAO = trainingCampAttendanceTabUserDAO;
	}

	public void setPartyMeetingAttendanceTabUserDAO(
			IPartyMeetingAttendanceTabUserDAO partyMeetingAttendanceTabUserDAO) {
		this.partyMeetingAttendanceTabUserDAO = partyMeetingAttendanceTabUserDAO;
	}

	public void setAttendanceTabUserDAO(IAttendanceTabUserDAO attendanceTabUserDAO) {
		this.attendanceTabUserDAO = attendanceTabUserDAO;
	}

	public void setAttendanceErrorDAO(IAttendanceErrorDAO attendanceErrorDAO) {
		this.attendanceErrorDAO = attendanceErrorDAO;
	}

	public void setTrainingCampAttendanceDAO(
			ITrainingCampAttendanceDAO trainingCampAttendanceDAO) {
		this.trainingCampAttendanceDAO = trainingCampAttendanceDAO;
	}

	public void setPartyMeetingAttendanceDAO(
			IPartyMeetingAttendanceDAO partyMeetingAttendanceDAO) {
		this.partyMeetingAttendanceDAO = partyMeetingAttendanceDAO;
	}

	public void setAttendanceDAO(IAttendanceDAO attendanceDAO) {
		this.attendanceDAO = attendanceDAO;
	}

	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}

	public AttendanceVO saveAttendance(AttendanceVO attendanceVO)
	{
		AttendanceVO result = new AttendanceVO();
		result.setUniqueKey(attendanceVO.getUniqueKey());
		result.setTabPrimaryKey(attendanceVO.getTabPrimaryKey());
		try{
			
			String membershipId = attendanceVO.getMembershipId();
			Long tdpCadreId = null;
			
			if(attendanceVO.getSyncSource() == null)
				attendanceVO.setSyncSource("WS");
			
			if(membershipId == null || membershipId.trim().length() < 8 || (membershipId.trim().length() > 8  && membershipId.trim().length() < 12) || membershipId.trim().length() > 12)
			{
				attendanceVO.setErrorDescription("Invalid Membership Id");
				saveAttendanceError(attendanceVO);
				result.setErrorDescription("Invalid Membership Id");
				result.setStatus("Success");
				return result;
			}
			if(membershipId.trim().length() == 12)
				membershipId = membershipId.trim().substring(4);
			
			List<Long> list = tdpCadreDAO.getTdpCadreIdByMembershipId(membershipId);
			
			if(list == null || list.size() == 0)
			{
				attendanceVO.setErrorDescription("Membership Id not Exist");
				saveAttendanceError(attendanceVO);
				result.setErrorDescription("Membership Id not Exist");
				result.setStatus("Success");
				return result;
			}
			else if(list.size() > 1)
			{
				attendanceVO.setErrorDescription("Multiple Membership Ids Exist");
				saveAttendanceError(attendanceVO);
				result.setErrorDescription("Multiple Membership Ids Exist");
				result.setStatus("Success");
				return result;
			}
			
			tdpCadreId = list.get(0);
			attendanceVO.setTdpCadreId(tdpCadreId);
			
			Attendance attendance = new Attendance();
			attendance.setTdpCadreId(tdpCadreId);
			attendance.setAttendedTime(dateUtilService.getDateByStringAndFormat(attendanceVO.getAttendedTime(),"yyyy-MM-dd HH:mm:ss"));
			attendance.setRfid(attendanceVO.getRfid() != null ? attendanceVO.getRfid().trim() : "");
			attendance.setImei(attendanceVO.getImei() != null ? attendanceVO.getImei().trim() : "");
			attendance.setUniqueKey(attendanceVO.getUniqueKey() != null ? attendanceVO.getUniqueKey().trim() : "");
			attendance.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			attendance.setLatitude(attendanceVO.getLatitude() != null ? attendanceVO.getLatitude().trim() : "");
			attendance.setLongitude(attendanceVO.getLongitude() != null ? attendanceVO.getLongitude().trim() : "");
			attendance.setTabUserId(attendanceVO.getTabUserId());
			attendance.setCurrentTabUserId(attendanceVO.getCurrentTabUserId());
			attendance.setSyncSource(attendanceVO.getSyncSource());
			attendance.setTabPrimaryKey(attendanceVO.getTabPrimaryKey());
			attendance = attendanceDAO.save(attendance);
			
			if(attendanceVO.getType().equalsIgnoreCase("Meeting"))
			{
				PartyMeetingAttendance partyMeetingAttendance = new PartyMeetingAttendance();
				partyMeetingAttendance.setAttendance(attendance);
				partyMeetingAttendance.setPartyMeetingId(attendanceVO.getPartyMeetingId());
				partyMeetingAttendance.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				partyMeetingAttendance = partyMeetingAttendanceDAO.save(partyMeetingAttendance);
			}
			else if(attendanceVO.getType().equalsIgnoreCase("Training"))
			{
				TrainingCampAttendance trainingCampAttendance = new TrainingCampAttendance();
				trainingCampAttendance.setAttendance(attendance);
				trainingCampAttendance.setTrainingCampBatchId(attendanceVO.getTrainingCampBatchId());
				
				TrainingCampBatch trainingCampBatch = trainingCampBatchDAO.get(attendanceVO.getTrainingCampBatchId());
				
				if(trainingCampBatch != null)
				{
					trainingCampAttendance.setTrainingCampScheduleId(trainingCampBatch.getTrainingCampSchedule() != null ?
							trainingCampBatch.getTrainingCampSchedule().getTrainingCampScheduleId() : null);
					trainingCampAttendance.setTrainingCampProgramId((trainingCampBatch.getTrainingCampSchedule() != null &&  trainingCampBatch.getTrainingCampSchedule().getTrainingCampProgram() != null) ?
							trainingCampBatch.getTrainingCampSchedule().getTrainingCampProgram().getTrainingCampProgramId() : null);
				}
				
				trainingCampAttendance.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				trainingCampAttendance = trainingCampAttendanceDAO.save(trainingCampAttendance);
			}
			result.setStatus("Success");
			voterDAO.flushAndclearSession();
			
			List<Long> idList = attendanceDAO.getPrimarykey(attendanceVO.getUniqueKey(),attendanceVO.getImei(),attendanceVO.getTabPrimaryKey());
			if(idList != null && list.size() > 0)
				result.setServerPrimaryKey(idList.get(0));
			
		}catch(Exception e)
		{
			LOG.error("Exception occured in saveAttendance Method- ",e);
			attendanceVO.setErrorDescription(e.toString());
			saveAttendanceError(attendanceVO);
			result.setStatus("Failure");
		}
		return result;
	}
	
	public ResultStatus saveAttendanceError(AttendanceVO attendanceVO)
	{
		ResultStatus resultStatus = new ResultStatus();
		try{
			AttendanceError attendanceError = new AttendanceError();
			attendanceError.setPartMeetingId(attendanceVO.getPartyMeetingId());
			attendanceError.setTrainingCampScheduleId(attendanceVO.getTrainingCampScheduleId());
			attendanceError.setTrainingCampBatchId(attendanceVO.getTrainingCampBatchId());
			attendanceError.setTrainingCampTopicId(attendanceVO.getTrainingCampTopicId());
			attendanceError.setTrainingCampProgramId(attendanceVO.getTrainingProgramId());
			attendanceError.setTdpCadreId(attendanceVO.getTdpCadreId());
			attendanceError.setMembershopId(attendanceVO.getMembershipId());
			attendanceError.setAttendedTime(dateUtilService.getDateByStringAndFormat(attendanceVO.getAttendedTime(),"yyyy-MM-dd HH:mm:ss"));
			attendanceError.setRfid(attendanceVO.getRfid());
			attendanceError.setImei(attendanceVO.getImei());
			attendanceError.setUniqueKey(attendanceVO.getUniqueKey());
			attendanceError.setInsertedTime(dateUtilService.getCurrentDateAndTime());
			attendanceError.setLatitude(attendanceVO.getLatitude());
			attendanceError.setLongitude(attendanceVO.getLongitude());
			attendanceError.setTabUserId(attendanceVO.getTabUserId());
			attendanceError.setCurrentTabUserId(attendanceVO.getCurrentTabUserId());
			attendanceError.setSyncSource(attendanceVO.getSyncSource());
			attendanceError.setErrorDescription(attendanceVO.getErrorDescription());
			attendanceError.setTabPrimaryKey(attendanceVO.getTabPrimaryKey());
			attendanceErrorDAO.save(attendanceError);
			saveAttendanceInFatalLog(attendanceVO);
		}catch(Exception e)
		{
			LOG.error("Exception occured in saveAttendanceError Method - ",e);
			saveAttendanceInFatalLog(attendanceVO);
		}
		return resultStatus;
	}
	
	public void saveAttendanceInFatalLog(AttendanceVO attendanceVO)
	{
		try{
			LOG.fatal("----------------------------------------------------");
			LOG.fatal("PartyMeetingId - "+attendanceVO.getPartyMeetingId()+"\tTrainingCampScheduleId - "+attendanceVO.getTrainingCampScheduleId()+
					"\tTrainingCampBatchId - "+attendanceVO.getTrainingCampBatchId()+"\tTrainingCampTopicId - "+attendanceVO.getTrainingCampTopicId()+"\tTdpCadreId - "+attendanceVO.getTdpCadreId()+
					"\tMembershopId - "+attendanceVO.getMembershipId()+"\tAttendedTime - "+attendanceVO.getAttendedTime()+"\tRfid - "+attendanceVO.getRfid()+"\tImei - "+attendanceVO.getImei()+"\tUniqueKey - "+attendanceVO.getUniqueKey()+
					"\tInsertedTime - "+dateUtilService.getCurrentDateAndTimeInStringFormat()+"\tLatitude - "+attendanceVO.getLatitude()+"\tLongitude - "+attendanceVO.getLongitude()+"\tTabUserId - "+attendanceVO.getTabUserId()+"\tCurrentTabUserId - "+attendanceVO.getCurrentTabUserId()+
					"\tSyncSource - "+attendanceVO.getSyncSource()+"\tTab Primary Kay - "+attendanceVO.getTabPrimaryKey()+"\tTraining Camp Program Id - "+attendanceVO.getTrainingProgramId());
			LOG.fatal("----------------------------------------------------");
		}catch(Exception e)
		{
			LOG.error("Exception occured in saveAttendanceInFatalLog() - ",e);
		}
	}
	
	public AttendanceTabUserVO loginAttendanceTabUser(AttendanceTabUserVO inputVO)
	{
		AttendanceTabUserVO resultVO = new AttendanceTabUserVO();
		try{
			List<Object[]> list = attendanceTabUserDAO.getAttendanceTabUserByUsernameAndPassword(inputVO.getUsername().trim(),inputVO.getPassword().trim());
			if(list != null && list.size() > 0)
			{
				Object[] params = list.get(0);
				resultVO.setUserId((Long)params[0]);
				resultVO.setFirstname(params[1] != null ? params[1].toString() : "");
				resultVO.setLastname(params[2] != null ? params[2].toString() : "");
				resultVO.setUsername(params[3] != null ? params[3].toString() : "");
				resultVO.setPassword(params[4] != null ? params[4].toString() : "");
				resultVO.setStatus("Success");
			}
			else
				resultVO.setStatus("Failure");
		}catch(Exception e)
		{
			LOG.error("Exception Occured in loginAttendanceTabUser() Method - ",e);
			resultVO.setStatus("Failure");
		}
		return resultVO;
	}
	
	public UserAttendanceDetailsVO getAttendanceMeetingAndCamps(AttendanceTabUserVO inputVo)
	{
		UserAttendanceDetailsVO resultVO = new UserAttendanceDetailsVO();
		try{
			resultVO.setPartyMeetings(getAttendanceTabUsersMeetings(inputVo.getUserId()));
			resultVO.setTrainingSchedules(getAttendanceTabUserTrainingCampSchedules(inputVo.getUserId()));
		}catch(Exception e)
		{
			LOG.error("Exception occured in getAttendanceMeetingAndCamps() Method - ",e);
		}
		return resultVO;
	}
	
	public List<UserPartyMeetingVO> getAttendanceTabUsersMeetings(Long userId)
	{
		List<UserPartyMeetingVO> partyMeetings = null;
		try{
			List<PartyMeeting> meetingsList = partyMeetingAttendanceTabUserDAO.getPartyMeetingsOfAttendanceTabUser(userId);
			
			if(meetingsList != null && meetingsList.size() > 0)
			{
				partyMeetings = new ArrayList<UserPartyMeetingVO>(0);
				UserPartyMeetingVO userPartyMeetingVO = null;
				
				for(PartyMeeting partyMeeting : meetingsList)
				{
					try{
						userPartyMeetingVO = new UserPartyMeetingVO();
						userPartyMeetingVO.setPartyMeetingId(partyMeeting.getPartyMeetingId());
						userPartyMeetingVO.setMeetingName(partyMeeting.getMeetingName());
						userPartyMeetingVO.setMeetingLevelId(partyMeeting.getPartyMeetingLevel().getPartyMeetingLevelId());
						userPartyMeetingVO.setMeetingLevelValue(partyMeeting.getLocationValue());
						userPartyMeetingVO.setStartDate(dateUtilService.getDateInStringFormatByDate(partyMeeting.getStartDate(),"yyyy-MM-dd"));
						userPartyMeetingVO.setEndDate(dateUtilService.getDateInStringFormatByDate(partyMeeting.getEndDate(),"yyyy-MM-dd"));
						userPartyMeetingVO.setDailyStartTime(partyMeeting.getDailyStartTime());
						userPartyMeetingVO.setDailyEndTime(partyMeeting.getDailyEndTime());
						userPartyMeetingVO.setUserId(userId);
						userPartyMeetingVO.setPartyMeetingTypeId(partyMeeting.getPartyMeetingType().getPartyMeetingTypeId());
						userPartyMeetingVO.setPartyMeetingStatusId(1l);
						
						if(partyMeeting.getMeetingAddress() != null)
						{
							PartyMeetingLocationVO locationVO = setLocationDetailsByUserAddress(partyMeeting.getMeetingAddress());
							userPartyMeetingVO.setPartyMeetingLocation(locationVO);
							
							if(userPartyMeetingVO.getMeetingLevelId() != null)
							{
								switch(userPartyMeetingVO.getMeetingLevelId().intValue()) 
								{
									case 1 : userPartyMeetingVO.setMeetingLevelName(locationVO.getStateName());							
										break;
									case 2 : userPartyMeetingVO.setMeetingLevelName(locationVO.getDistrictName());
										break;
									case 3 : userPartyMeetingVO.setMeetingLevelName(locationVO.getConstituencyName());
										break;
									case 4 : userPartyMeetingVO.setMeetingLevelName(locationVO.getTehsilName());
										break;
									case 5 : userPartyMeetingVO.setMeetingLevelName(locationVO.getLocalElectionBodyName());
										break;
									case 6 : userPartyMeetingVO.setMeetingLevelName(locationVO.getDivisionName());
										break;
									case 7 : userPartyMeetingVO.setMeetingLevelName(locationVO.getPanchayatName());
										break;
									case 8 : userPartyMeetingVO.setMeetingLevelName(locationVO.getWardName());
										break;
								}
							}
						}
						partyMeetings.add(userPartyMeetingVO);
					}catch(Exception e)
					{
						LOG.error(e);
					}
				}
			}
		}catch(Exception e)
		{
			Log.error("Exception occured in getAttendanceTabUsersMeetings() Details - ",e);
		}
		return partyMeetings;
	}
	
	public List<UserTrainingCampScheduleVO> getAttendanceTabUserTrainingCampSchedules(Long userId)
	{
		List<UserTrainingCampScheduleVO> result = null;
		try{
			List<TrainingCampSchedule> list = trainingCampAttendanceTabUserDAO.getTrainingCampSchedulesOfAttendanceTabUser(userId);
			
			if(list != null && list.size() > 0)
			{
				result = new ArrayList<UserTrainingCampScheduleVO>(0);
				UserTrainingCampScheduleVO scheduleVO = null;
				
				for(TrainingCampSchedule trainingCampSchedule : list)
				{
					scheduleVO = new UserTrainingCampScheduleVO();
					try{
						scheduleVO.setUserId(userId);
						scheduleVO.setTrainingCampScheduleId(trainingCampSchedule.getTrainingCampScheduleId());
						scheduleVO.setTrainingCampScheduleCode(trainingCampSchedule.getTrainingCampScheduleCode());
						scheduleVO.setTrainingCampId(trainingCampSchedule.getTrainingCamp().getTrainingCampId());
						scheduleVO.setTrainingCampName(trainingCampSchedule.getTrainingCamp().getCampName());
						scheduleVO.setTrainingCampProgramId(trainingCampSchedule.getTrainingCampProgram().getTrainingCampProgramId());
						scheduleVO.setTrainingCampProgramName(trainingCampSchedule.getTrainingCampProgram().getProgramName());
						scheduleVO.setScheduleStartDate(dateUtilService.getDateInStringFormatByDate(trainingCampSchedule.getFromDate(),"yyyy-MM-dd"));
						scheduleVO.setScheduleEndDate(dateUtilService.getDateInStringFormatByDate(trainingCampSchedule.getToDate(),"yyyy-MM-dd"));
						scheduleVO.setScheduleStatusId(1l);
						scheduleVO.setBatchList(getTrainingCampBatchesOfScheduleForTabUser(trainingCampSchedule.getTrainingCampScheduleId(),userId));
					}catch(Exception e)
					{
						LOG.error(e);
					}
					result.add(scheduleVO);
				}
			}
		}catch(Exception e)
		{
			LOG.error("exception occured in getAttendanceTabUsersTrainingCampSchedules() Method - ",e);
		}
		return result;
	}
	
	public List<UserTrainingCampBatchVO> getTrainingCampBatchesOfSchedule(Long trainingCampScheduleId)
	{
		List<UserTrainingCampBatchVO> result = null;
		try{
			List<Object[]> list = trainingCampBatchDAO.getTrainingCampBatchesOfSchedule(trainingCampScheduleId);
			
			if(list != null && list.size() > 0)
			{
				result = new ArrayList<UserTrainingCampBatchVO>(0);
				UserTrainingCampBatchVO batchVO = null;
				
				for(Object[] params : list)
				{
					batchVO = new UserTrainingCampBatchVO();
					try{
						batchVO.setTrainingCampBatchId((Long)params[0]);
						batchVO.setTrainingCampBatchName(params[1] != null ? params[1].toString() : "");
						batchVO.setTrainingCampBatchCode(params[2] != null ? params[2].toString() : "");
						batchVO.setBatchStartDate(dateUtilService.getDateInStringFormatByDate((Date)params[3],"yyyy-MM-dd"));
						batchVO.setBatchEndDate(dateUtilService.getDateInStringFormatByDate((Date)params[4],"yyyy-MM-dd"));
					}catch(Exception e)
					{
						LOG.error(e);
					}
					result.add(batchVO);
				}
			}
		}catch(Exception e)
		{
			LOG.error("Exception occured in getTrainingCampBatchesOfSchedule() Method - ",e);
		}
		return result;
	}
	
	public List<UserTrainingCampBatchVO> getTrainingCampBatchesOfScheduleForTabUser(Long trainingCampScheduleId,Long attendanceTabUserId)
	{
		List<UserTrainingCampBatchVO> result = null;
		try{
			List<Object[]> list = trainingCampAttendanceTabUserDAO.getTrainingCampBatchesOfScheduleForTabUser(trainingCampScheduleId,attendanceTabUserId);
			
			if(list != null && list.size() > 0)
			{
				result = new ArrayList<UserTrainingCampBatchVO>(0);
				UserTrainingCampBatchVO batchVO = null;
				
				for(Object[] params : list)
				{
					batchVO = new UserTrainingCampBatchVO();
					try{
						batchVO.setTrainingCampBatchId((Long)params[0]);
						batchVO.setTrainingCampBatchName(params[1] != null ? params[1].toString() : "");
						batchVO.setTrainingCampBatchCode(params[2] != null ? params[2].toString() : "");
						batchVO.setBatchStartDate(dateUtilService.getDateInStringFormatByDate((Date)params[3],"yyyy-MM-dd"));
						batchVO.setBatchEndDate(dateUtilService.getDateInStringFormatByDate((Date)params[4],"yyyy-MM-dd"));
					}catch(Exception e)
					{
						LOG.error(e);
					}
					result.add(batchVO);
				}
			}
		}catch(Exception e)
		{
			LOG.error("Exception occured in getTrainingCampBatchesOfSchedule() Method - ",e);
		}
		return result;
	}
	
	public PartyMeetingLocationVO setLocationDetailsByUserAddress(UserAddress userAddress)
	{
		PartyMeetingLocationVO locationVO = new PartyMeetingLocationVO();
		
			locationVO.setStateId(userAddress.getState() != null ? userAddress.getState().getStateId() : null);
			locationVO.setStateName(userAddress.getState() != null ? userAddress.getState().getStateName() : "");
			locationVO.setDistrictId(userAddress.getDistrict() != null ? userAddress.getDistrict().getDistrictId() : null);
			locationVO.setDistrictName(userAddress.getDistrict() != null ? userAddress.getDistrict().getDistrictName() : "");
			locationVO.setConstituencyId(userAddress.getConstituency() != null ? userAddress.getConstituency().getConstituencyId() : null);
			locationVO.setConstituencyName(userAddress.getConstituency() != null ? userAddress.getConstituency().getName() : "");
			locationVO.setTehsilId(userAddress.getTehsil() != null ? userAddress.getTehsil().getTehsilId() : null);
			locationVO.setTehsilName(userAddress.getTehsil() != null ? userAddress.getTehsil().getTehsilName() : "");
			locationVO.setLocalElectionBodyId(userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getLocalElectionBodyId() : null);
			locationVO.setLocalElectionBodyName(userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getName() : "");
			locationVO.setPanchayatId(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatId() : null);
			locationVO.setPanchayatName(userAddress.getPanchayat() != null ? userAddress.getPanchayat().getPanchayatName() : "");
			
			if(userAddress.getWard() != null && userAddress.getLocalElectionBody() != null)
			{
				if(userAddress.getLocalElectionBody().getElectionType().getElectionType().equalsIgnoreCase("GMC"))
				{
					locationVO.setDivisionId(userAddress.getWard().getConstituencyId());
					locationVO.setDivisionName(userAddress.getWard().getName());
				}
				else
				{
					locationVO.setWardId(userAddress.getWard().getConstituencyId());
					locationVO.setWardName(userAddress.getWard().getName());
				}
			}
		try{
			
		}catch(Exception e)
		{
			LOG.error("Exception Occured in setPartyMeetingLocationVODetailsByPartyMeeting() Method - ",e);
		}
		return locationVO;
	}
	
	public PartyMeetingInviteeVO getPartyMeetingInvittees(Long partyMeetingId)
	{
		PartyMeetingInviteeVO result = new PartyMeetingInviteeVO();
		try{
			List<String> list = partyMeetingInviteeDAO.getPartyMeetingInvittees(partyMeetingId);
			result.setPartyMeetingId(partyMeetingId);
			result.setInviteeList(list);
			result.setResult("Success");
		}catch(Exception e)
		{
			LOG.error("Exception occured in getPartyMeetingInvittees Method() - ",e);
			result.setResult("Failure");
		}
		return result;
	}
	
	public PartyMeetingInviteeVO getTrainingCampBatchInvittees(Long trainingCampBatchId)
	{
		PartyMeetingInviteeVO result = new PartyMeetingInviteeVO();
		try{
			List<String> list = trainingCampBatchAttendeeDAO.getAttendeesForATrainingCampBatch(trainingCampBatchId);
			result.setInviteeList(list);
			result.setPartyMeetingId(trainingCampBatchId);
			result.setResult("Success");
		}catch(Exception e)
		{
			LOG.error("Exception occured in getTrainingCampBatchInvittees Method() - ",e);
			result.setResult("Failure");
		}
		return result;
	}
	
	public ResultStatus saveCadreActivityAttendance(final ActivityAttendanceVO inputVO,final Long userId)
	{
		
		 ResultStatus result = new ResultStatus();
		
		 try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
			Date attendedeTime = null;
			 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			if(inputVO.getActivityDate() != null && !inputVO.getActivityDate().isEmpty())
			{
				try {
					attendedeTime = format.parse(inputVO.getActivityDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		DateUtilService date = new DateUtilService();
		Attendance attendance = new Attendance();
		attendance.setTdpCadreId(inputVO.getTdpCadreId());
		if(attendedeTime != null)
		attendance.setAttendedTime(attendedeTime);
		attendance.setInsertedById(userId);
		attendance.setInsertedTime(date.getCurrentDateAndTime());
		if(inputVO.getSyncType() != null && inputVO.getSyncType().equalsIgnoreCase("WS"))
		{
			attendance.setImei(inputVO.getImei());
			attendance.setRfid(inputVO.getRfid());
			attendance.setUniqueKey(inputVO.getUnqueKey());
			attendance.setLatitude(inputVO.getLatitude());
			attendance.setLongitude(inputVO.getLongitude());
			attendance.setTabUserId(inputVO.getTabUserId());
			attendance.setCurrentTabUserId(inputVO.getCurrentTabUserId());
			attendance.setTabPrimaryKey(inputVO.getTabPrimaryKey());
		}
		else
		attendance.setSyncSource("WEB");
		
		attendance = attendanceDAO.save(attendance);
		ActivityLocationAttendance activityLocationAttendance = new ActivityLocationAttendance();
		activityLocationAttendance.setActivityLocationInfoId(inputVO.getActivityLocationInfoId());
		activityLocationAttendance.setAttendanceId(attendance.getAttendanceId());
		activityLocationAttendanceDAO.save(activityLocationAttendance);
		
		}});
			  result.setResultCode(0);
		}
		catch (Exception e) {
			LOG.error("Exception occured in saveAttendanceInfo Method() - ",e);
			result.setResultCode(1);
		}
		return result;
	}
	
	public ResultStatus savePublicActivityAttendance(final ActivityAttendanceVO inputVO,final Long userId)
	{
		
		 ResultStatus result = new ResultStatus();
		
		 try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
						Date attendedeTime = null;
						 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						if(inputVO.getActivityDate() != null && !inputVO.getActivityDate().isEmpty())
						{
							try {
								attendedeTime = format.parse(inputVO.getActivityDate());
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
		DateUtilService date = new DateUtilService();
		ActivityLocationPublicAttendance activityLocationPublicAttendance = new ActivityLocationPublicAttendance();
		activityLocationPublicAttendance.setName(inputVO.getName());
		activityLocationPublicAttendance.setVoterCard(inputVO.getVoterCard());
		activityLocationPublicAttendance.setMobile(inputVO.getMobileNumber());
		activityLocationPublicAttendance.setBloodGroupId(inputVO.getBloodGroup());
		activityLocationPublicAttendance.setInsertedTime(date.getCurrentDateAndTime());
		activityLocationPublicAttendance = activityLocationPublicAttendanceDAO.save(activityLocationPublicAttendance);
		Attendance attendance = new Attendance();
		if(attendedeTime != null)
			attendance.setAttendedTime(attendedeTime);
		attendance.setInsertedById(userId);
		attendance.setInsertedTime(date.getCurrentDateAndTime());
		if(inputVO.getSyncType() != null && inputVO.getSyncType().equalsIgnoreCase("WS"))
		{
			attendance.setImei(inputVO.getImei());
			attendance.setRfid(inputVO.getRfid());
			attendance.setUniqueKey(inputVO.getUnqueKey());
			attendance.setLatitude(inputVO.getLatitude());
			attendance.setLongitude(inputVO.getLongitude());
			attendance.setTabUserId(inputVO.getTabUserId());
			attendance.setCurrentTabUserId(inputVO.getCurrentTabUserId());
			attendance.setTabPrimaryKey(inputVO.getTabPrimaryKey());	
		}
		else
		attendance.setSyncSource("WEB");
		attendance.setActivityLocationPublicAttendanceId(activityLocationPublicAttendance.getActivityLocationPublicAttendanceId());
		
		attendance = attendanceDAO.save(attendance);
		ActivityLocationAttendance activityLocationAttendance = new ActivityLocationAttendance();
		activityLocationAttendance.setActivityLocationInfoId(inputVO.getActivityLocationInfoId());
		activityLocationAttendance.setAttendanceId(attendance.getAttendanceId());
		activityLocationAttendanceDAO.save(activityLocationAttendance);
					}});
			  result.setResultCode(0);
		}
		catch (Exception e) {
			LOG.error("Exception occured in savePublicActivityAttendance Method() - ",e);
			result.setResultCode(1);
		}
		return result;
	}
}
