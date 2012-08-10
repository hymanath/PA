package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserCadreOnlineRegistrationDAO;
import com.itgrids.partyanalyst.model.UserCadreOnlineRegistration;

public class UserCadreOnlineRegistrationDAO extends GenericDaoHibernate<UserCadreOnlineRegistration, Long> implements IUserCadreOnlineRegistrationDAO{

	public UserCadreOnlineRegistrationDAO()
	{
		super(UserCadreOnlineRegistration.class);
	}
}
