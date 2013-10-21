package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;

public class MobileAppUserDAOHibernateTest extends BaseDaoTestCase{
	private IMobileAppUserDAO mobileAppUserDAO;

	
	public void setMobileAppUserDAO(IMobileAppUserDAO mobileAppUserDAO) {
		this.mobileAppUserDAO = mobileAppUserDAO;
	}
	
	public void test()
	{
		mobileAppUserDAO.getAll();
	}
}
