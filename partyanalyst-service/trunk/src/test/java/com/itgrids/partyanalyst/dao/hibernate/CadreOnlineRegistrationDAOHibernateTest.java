package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.ICadreOnlineRegistrationDAO;

public class CadreOnlineRegistrationDAOHibernateTest extends BaseDaoTestCase{

	public ICadreOnlineRegistrationDAO cadreOnlineRegistrationDAO;

	public void setCadreOnlineRegistrationDAO(
			ICadreOnlineRegistrationDAO cadreOnlineRegistrationDAO) {
		this.cadreOnlineRegistrationDAO = cadreOnlineRegistrationDAO;
	}
	
	public void test()
	{
		cadreOnlineRegistrationDAO.getAll();
	}
}
