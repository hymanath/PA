package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IMobileAppUserDAO;

public class MobileAppUserDAOHibernateTest extends BaseDaoTestCase{
	private IMobileAppUserDAO mobileAppUserDAO;

	
	public void setMobileAppUserDAO(IMobileAppUserDAO mobileAppUserDAO) {
		this.mobileAppUserDAO = mobileAppUserDAO;
	}
	
	public void test()
	{
		List<Object[]> list = mobileAppUserDAO.getSuperAdminList();
		for(Object[] params : list)
		{
			System.out.println(params[0]);
		}
	}
}
