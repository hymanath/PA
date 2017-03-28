package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentPreferableTimeDAO;
import com.itgrids.partyanalyst.model.AppointmentPreferableTime;

public class AppointmentPreferableTimeDAO extends GenericDaoHibernate<AppointmentPreferableTime, Long> implements IAppointmentPreferableTimeDAO {

	public AppointmentPreferableTimeDAO() {
		super(AppointmentPreferableTime.class);
	}

}
