package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVerifiedDataRequestDAO;

public class VerifiedDataRequestDAOHibernateTest extends BaseDaoTestCase{
	
	private IVerifiedDataRequestDAO verifiedDataRequestDAO;

	public void setVerifiedDataRequestDAO(
			IVerifiedDataRequestDAO verifiedDataRequestDAO) {
		this.verifiedDataRequestDAO = verifiedDataRequestDAO;
	}
	
	public void test()
	{
		verifiedDataRequestDAO.getAll();
	}
}
