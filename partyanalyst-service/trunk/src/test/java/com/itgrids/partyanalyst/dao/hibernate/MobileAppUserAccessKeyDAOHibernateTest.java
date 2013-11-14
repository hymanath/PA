package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileAppUserAccessKeyDAO;

public class MobileAppUserAccessKeyDAOHibernateTest extends BaseDaoTestCase{
	private IMobileAppUserAccessKeyDAO mobileAppUserAccessKeyDAO;

	public void setMobileAppUserAccessKeyDAO(
			IMobileAppUserAccessKeyDAO mobileAppUserAccessKeyDAO) {
		this.mobileAppUserAccessKeyDAO = mobileAppUserAccessKeyDAO;
	}
	public void test()
	{
		List<Object[]> list= mobileAppUserAccessKeyDAO.checkUniqueCodeByAccesskey("uc","abc");
		System.out.println(list.size());
	}

}
