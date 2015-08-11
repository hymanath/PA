package com.itgrids.partyanalyst.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAttendanceDAO;
import com.itgrids.partyanalyst.dao.IAttendanceErrorDAO;
import com.itgrids.partyanalyst.dao.IPartyMeetingAttendanceDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITrainingCampAttendanceDAO;
import com.itgrids.partyanalyst.dto.AttendanceVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.Attendance;
import com.itgrids.partyanalyst.model.AttendanceError;
import com.itgrids.partyanalyst.model.PartyMeetingAttendance;
import com.itgrids.partyanalyst.model.TrainingCampAttendance;
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
		try{
			
			String membershipId = attendanceVO.getMembershipId();
			Long tdpCadreId = null;
			
			if(attendanceVO.getSyncSource() == null)
				attendanceVO.setSyncSource("WS");
			
			if(membershipId == null || membershipId.trim().length() < 8)
			{
				attendanceVO.setErrorDescription("Invalid Membership Id");
				saveAttendanceError(attendanceVO);
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
				result.setStatus("Success");
				return result;
			}
			else if(list.size() > 1)
			{
				attendanceVO.setErrorDescription("Multiple Membership Ids Exist");
				saveAttendanceError(attendanceVO);
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
			
			attendance = attendanceDAO.save(attendance);
			
			if(attendanceVO.getType().equalsIgnoreCase("Meeting"))
			{
				PartyMeetingAttendance partyMeetingAttendance = new PartyMeetingAttendance();
				partyMeetingAttendance.setAttendance(attendance);
				partyMeetingAttendance.setPartyMeetingId(attendanceVO.getPartyMeetingId());
				partyMeetingAttendance.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				partyMeetingAttendanceDAO.save(partyMeetingAttendance);
			}
			else if(attendanceVO.getType().equalsIgnoreCase("Training"))
			{
				TrainingCampAttendance trainingCampAttendance = new TrainingCampAttendance();
				trainingCampAttendance.setAttendance(attendance);
				trainingCampAttendance.setTrainingCampScheduleId(attendanceVO.getTrainingCampScheduleId());
				trainingCampAttendance.setTrainingCampBatchId(attendanceVO.getTrainingCampBatchId());
				trainingCampAttendance.setInsertedTime(dateUtilService.getCurrentDateAndTime());
				trainingCampAttendanceDAO.save(trainingCampAttendance);
			}
			result.setStatus("Success");
			
		}catch(Exception e)
		{
			LOG.error("Exception occured in saveAttendance Method- ",e);
			attendanceVO.setErrorDescription(e.toString());
			saveAttendanceError(attendanceVO);
			result.setStatus("Success");
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
					"\tSyncSource - "+attendanceVO.getSyncSource());
			LOG.fatal("----------------------------------------------------");
		}catch(Exception e)
		{
			LOG.error("Exception occured in saveAttendanceInFatalLog() - ",e);
		}
	}
}
