package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IVerifiedDataResponseDAO;

public class VerifiedDataResponseDAOHibernateTest extends BaseDaoTestCase{
	
	private IVerifiedDataResponseDAO verifiedDataResponseDAO;

	public void setVerifiedDataResponseDAO(
			IVerifiedDataResponseDAO verifiedDataResponseDAO) {
		this.verifiedDataResponseDAO = verifiedDataResponseDAO;
	}
	
	public void test()
	{
		verifiedDataResponseDAO.getAll();
	}
}
