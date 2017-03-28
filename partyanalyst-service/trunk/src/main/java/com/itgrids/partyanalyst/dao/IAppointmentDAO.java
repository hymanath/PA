package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Appointment;

public interface IAppointmentDAO extends GenericDao<Appointment, Long>{
	public List<Object[]> getTotalAppointmentStatus();
	public List<Object[]> getTotalAppointmentStatusForToday(Date today);
	public Integer updateUniquesIdForAppointment(String uniqueCode,Long appointmentId);
	public List<Object[]> getAppointmentCreatedUsers();
	public Integer updateAppntmntStatusById(Long appointmentId, Date updatedTime);
	public Integer updateLabelingStatusToAppts(List<Long> appointmentIds,String labelStatus);
	public Long getAppointmentStatusId(Long appointmentId);
	public Integer updateApptStatusbyApptIds(List<Long> appointmemtIds, Date updatedTime,Long statusId,Long userId);
	public Integer updatedAppointmentStatus(List<Long> appointmentIds,Long apptStatusId,Long userId,Date date);
	
	public List<Long> getAppointmentIdsByDateByStatus(Date date,Long apptStatusId);
	public Long getCurrentAppointmentStatus(Long appointmentId);
	public List<Object[]> eachStatusApptCountByDateAndApptUser(Long apptUserId,List<Long> statusIds,Date date);
	public Integer updateApptStatusbyApptId(Long appointmentId, Date updatedTime,Long statusId,Long userID);
	public Integer updateAppointmentReason(Long appointmentId,String reason,Date presentDate,Long userId);
	public List<Object[]> eachStatusApptCountByDateAndApptUserNew(Long apptUserId,List<Long> statusIds,Date date);
}
