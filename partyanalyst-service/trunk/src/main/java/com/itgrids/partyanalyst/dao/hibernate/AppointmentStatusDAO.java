package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentStatusDAO;
import com.itgrids.partyanalyst.model.AppointmentStatus;

public class AppointmentStatusDAO extends GenericDaoHibernate<AppointmentStatus, Long> implements IAppointmentStatusDAO {

	public AppointmentStatusDAO() {
		super(AppointmentStatus.class);
	}
 }
