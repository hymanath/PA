package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentActionDAO;
import com.itgrids.partyanalyst.model.AppointmentAction;

public class AppointmentActionDAO extends GenericDaoHibernate<AppointmentAction, Long> implements IAppointmentActionDAO {

	public AppointmentActionDAO() {
		super(AppointmentAction.class);
	}
}
