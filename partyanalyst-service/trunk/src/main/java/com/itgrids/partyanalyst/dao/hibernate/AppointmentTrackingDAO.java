package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentTrackingDAO;
import com.itgrids.partyanalyst.model.AppointmentTracking;

public class AppointmentTrackingDAO extends GenericDaoHibernate<AppointmentTracking, Long> implements IAppointmentTrackingDAO {

	public AppointmentTrackingDAO() {
		super(AppointmentTracking.class);
	}
}
