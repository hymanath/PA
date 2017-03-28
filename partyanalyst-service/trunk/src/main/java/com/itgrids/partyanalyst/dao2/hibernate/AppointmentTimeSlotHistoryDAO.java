package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentTimeSlotHistoryDAO;
import com.itgrids.partyanalyst.model.AppointmentTimeSlotHistory;

public class AppointmentTimeSlotHistoryDAO extends GenericDaoHibernate<AppointmentTimeSlotHistory, Long> implements IAppointmentTimeSlotHistoryDAO{

	public AppointmentTimeSlotHistoryDAO() {
		super(AppointmentTimeSlotHistory.class);
		
	}

}
