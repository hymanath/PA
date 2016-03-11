package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentManageUserDAO;
import com.itgrids.partyanalyst.model.AppointmentManageUser;

public class AppointmentManageUserDAO extends GenericDaoHibernate<AppointmentManageUser, Long> implements IAppointmentManageUserDAO{
	
	public AppointmentManageUserDAO() {
		super(AppointmentManageUser.class);
	}
	

}
