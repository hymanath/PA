package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentUserDAO;
import com.itgrids.partyanalyst.model.AppointmentUser;

public class AppointmentUserDAO extends GenericDaoHibernate<AppointmentUser, Long> implements IAppointmentUserDAO {

	public AppointmentUserDAO() {
		super(AppointmentUser.class);
	}
}
