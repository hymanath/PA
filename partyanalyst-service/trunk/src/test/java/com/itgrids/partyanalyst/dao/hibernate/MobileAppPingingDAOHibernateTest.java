package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileAppPingingDAO;

public class MobileAppPingingDAOHibernateTest extends BaseDaoTestCase{
	
	private IMobileAppPingingDAO mobileAppPingingDAO;

	public void setMobileAppPingingDAO(IMobileAppPingingDAO mobileAppPingingDAO) {
		this.mobileAppPingingDAO = mobileAppPingingDAO;
	}
	public void test()
	{
		mobileAppPingingDAO.getAll();
	}

}
