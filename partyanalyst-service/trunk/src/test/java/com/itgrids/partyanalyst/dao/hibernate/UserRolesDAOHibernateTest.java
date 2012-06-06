package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserRolesDAO;

public class UserRolesDAOHibernateTest extends BaseDaoTestCase{

	private IUserRolesDAO userRolesDAO;

	public void setUserRolesDAO(IUserRolesDAO userRolesDAO) {
		this.userRolesDAO = userRolesDAO;
	}
	
	public void test()
	{
		userRolesDAO.getAll();
	}
}
