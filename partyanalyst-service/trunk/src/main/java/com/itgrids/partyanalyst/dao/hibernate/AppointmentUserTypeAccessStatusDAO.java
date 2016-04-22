package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentUserTypeAccessStatusDAO;
import com.itgrids.partyanalyst.model.AppointmentUserTypeAccessStatus;

public class AppointmentUserTypeAccessStatusDAO extends GenericDaoHibernate<AppointmentUserTypeAccessStatus, Long> implements IAppointmentUserTypeAccessStatusDAO {

	public AppointmentUserTypeAccessStatusDAO() {
		super(AppointmentUserTypeAccessStatus.class);
	}

}
