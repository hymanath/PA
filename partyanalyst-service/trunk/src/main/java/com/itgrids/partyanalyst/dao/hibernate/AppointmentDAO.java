package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentDAO;
import com.itgrids.partyanalyst.model.Appointment;
import com.itgrids.partyanalyst.model.Cadre;



public class AppointmentDAO extends GenericDaoHibernate<Appointment, Long> implements IAppointmentDAO{

	public AppointmentDAO() {
		super(Appointment.class);
	}

}
