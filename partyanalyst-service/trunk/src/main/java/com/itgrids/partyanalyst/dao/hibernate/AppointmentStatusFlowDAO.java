package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentStatusFlowDAO;
import com.itgrids.partyanalyst.model.AppointmentStatusFlow;

public class AppointmentStatusFlowDAO extends GenericDaoHibernate<AppointmentStatusFlow, Long> implements IAppointmentStatusFlowDAO {

	public AppointmentStatusFlowDAO() {
		super(AppointmentStatusFlow.class);
	}

}
