package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMinistryDAO;

public class MinistryDAOHibernateTest extends BaseDaoTestCase{
	
	private IMinistryDAO ministryDAO;

	public void setMinistryDAO(IMinistryDAO ministryDAO) {
		this.ministryDAO = ministryDAO;
	}
	
	public void test()
	{
		ministryDAO.getAll();
	}

}
