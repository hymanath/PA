package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IAppointmentLableDAO;
import com.itgrids.partyanalyst.model.AppointmentLable;

public class AppointmentLableDAO extends GenericDaoHibernate<AppointmentLable, Long> implements IAppointmentLableDAO {

	public AppointmentLableDAO() {
		super(AppointmentLable.class);
	}
}
