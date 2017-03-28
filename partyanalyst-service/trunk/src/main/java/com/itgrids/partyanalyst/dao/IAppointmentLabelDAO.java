package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentLabel;

public interface IAppointmentLabelDAO extends GenericDao<AppointmentLabel, Long> {
	
	public Integer deleteAppointmentLabel(Long appointmentLabelId,String remarks);
	public List<Object[]> getAllLabels(Date date,Long userID,Long statusId);
	public List<Object[]> getAppointmentLabels(Long aptUserId);
	public Integer updateAppointmentsLabelStatus(Long labelId,Long labelstatusId);
	public Integer updateMemberAppointmentsStatus(Long memberAppntId,Long updateAppntStatusId);
}
