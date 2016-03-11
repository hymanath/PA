package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentLableStatusDAO;
import com.itgrids.partyanalyst.model.AppointmentLableStatus;

public class AppointmentLableStatusDAO extends GenericDaoHibernate<AppointmentLableStatus, Long> implements IAppointmentLableStatusDAO {
	
	public AppointmentLableStatusDAO() {
		super(AppointmentLableStatus.class);
	}

}
