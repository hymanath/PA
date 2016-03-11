package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentPriorityDAO;
import com.itgrids.partyanalyst.model.AppointmentPriority;

public class AppointmentPriorityDAO extends GenericDaoHibernate<AppointmentPriority, Long> implements IAppointmentPriorityDAO {
	
	public AppointmentPriorityDAO() {
		super(AppointmentPriority.class);
	}

}
