package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentUserTypeDAO;
import com.itgrids.partyanalyst.model.AppointmentUserType;

public class AppointmentUserTypeDAO extends GenericDaoHibernate<AppointmentUserType, Long> implements IAppointmentUserTypeDAO {

	public AppointmentUserTypeDAO() {
		super(AppointmentUserType.class);
	}

}
