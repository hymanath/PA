package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentSmsHistoryDAO;
import com.itgrids.partyanalyst.model.AppointmentSmsHistory;

public class AppointmentSmsHistoryDAO extends GenericDaoHibernate<AppointmentSmsHistory,Long> implements IAppointmentSmsHistoryDAO {
	
	public AppointmentSmsHistoryDAO() {
		super(AppointmentSmsHistory.class);
	}

}
