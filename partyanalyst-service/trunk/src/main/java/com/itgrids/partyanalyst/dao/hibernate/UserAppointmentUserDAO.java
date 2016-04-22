package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserAppointmentUserDAO;
import com.itgrids.partyanalyst.model.UserAppointmentUser;

public class UserAppointmentUserDAO extends GenericDaoHibernate<UserAppointmentUser, Long> implements IUserAppointmentUserDAO{

	public UserAppointmentUserDAO() {
		super(UserAppointmentUser.class);
		
	}

}
