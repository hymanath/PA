package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileAppUserAccessDAO;

public class MobileAppUserAccessDAOHibernateTest extends BaseDaoTestCase{
	
	private IMobileAppUserAccessDAO mobileAppUserAccessDAO;


	public void setMobileAppUserAccessDAO(
			IMobileAppUserAccessDAO mobileAppUserAccessDAO) {
		this.mobileAppUserAccessDAO = mobileAppUserAccessDAO;
	}
	
	public void test()
	{
		mobileAppUserAccessDAO.getAll();
	}
}
