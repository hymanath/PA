package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentPreferableDateDAO;
import com.itgrids.partyanalyst.model.AppointmentPreferableDate;

public class AppointmentPreferableDateDAO extends GenericDaoHibernate<AppointmentPreferableDate, Long> implements  IAppointmentPreferableDateDAO {

	public AppointmentPreferableDateDAO( ) {
		super(AppointmentPreferableDate.class);
	}

}
