package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileAppUserProfileDAO;

public class MobileAppUserProfileDAOHibernateTest extends BaseDaoTestCase{
	private IMobileAppUserProfileDAO mobileAppUserProfileDAO;

	public IMobileAppUserProfileDAO getMobileAppUserProfileDAO() {
		return mobileAppUserProfileDAO;
	}

	public void setMobileAppUserProfileDAO(
			IMobileAppUserProfileDAO mobileAppUserProfileDAO) {
		this.mobileAppUserProfileDAO = mobileAppUserProfileDAO;
	}
	public void test()
	{
		mobileAppUserProfileDAO.getAll();
	}

}
