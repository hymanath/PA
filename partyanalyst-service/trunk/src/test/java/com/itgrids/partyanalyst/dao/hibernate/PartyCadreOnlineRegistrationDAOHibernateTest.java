package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyCadreOnlineRegistrationDAO;

public class PartyCadreOnlineRegistrationDAOHibernateTest extends BaseDaoTestCase{
	
	private IPartyCadreOnlineRegistrationDAO partyCadreOnlineRegistrationDAO;

	public void setPartyCadreOnlineRegistrationDAO(
			IPartyCadreOnlineRegistrationDAO partyCadreOnlineRegistrationDAO) {
		this.partyCadreOnlineRegistrationDAO = partyCadreOnlineRegistrationDAO;
	}
	
	public void test()
	{
		partyCadreOnlineRegistrationDAO.getAll();
	}

}
