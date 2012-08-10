package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserCadreOnlineRegistrationDAO;

public class UserCadreOnlineRegistrationDAOHibernateTest extends BaseDaoTestCase{
	private IUserCadreOnlineRegistrationDAO userCadreOnlineRegistrationDAO;

	public void setUserCadreOnlineRegistrationDAO(
			IUserCadreOnlineRegistrationDAO userCadreOnlineRegistrationDAO) {
		this.userCadreOnlineRegistrationDAO = userCadreOnlineRegistrationDAO;
	}
	
	public void test()
	{
		userCadreOnlineRegistrationDAO.getAll();
	}
}
