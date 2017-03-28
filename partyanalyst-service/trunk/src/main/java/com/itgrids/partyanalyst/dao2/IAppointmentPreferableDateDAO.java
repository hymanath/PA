package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentPreferableDate;

public interface IAppointmentPreferableDateDAO extends GenericDao<AppointmentPreferableDate, Long> {
	
	public List<Object[]> getMultipleDatesforAppointments(List<Long> appointmentIds);
	public List<Object[]> getPreferableDatesforAppointments(List<Long> appointmentIds);
}
