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
}
