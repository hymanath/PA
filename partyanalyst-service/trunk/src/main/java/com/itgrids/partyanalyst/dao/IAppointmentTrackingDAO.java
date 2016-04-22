package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppointmentTracking;

public interface IAppointmentTrackingDAO extends GenericDao<AppointmentTracking, Long> {
	public List<Object[]> getAppointmentTrackingDetails(Long appointmentId);

}
