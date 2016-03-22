package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentTimeSlotDAO;
import com.itgrids.partyanalyst.model.AppointmentTimeSlot;

public class AppointmentTimeSlotDAO extends GenericDaoHibernate<AppointmentTimeSlot,Long> implements IAppointmentTimeSlotDAO{
	
	public AppointmentTimeSlotDAO() {
		super(AppointmentTimeSlot.class);
	}
}
