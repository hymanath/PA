package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserTrackingDAO;

public class UserTrackingDAOHibernateTest extends BaseDaoTestCase{
	private IUserTrackingDAO userTrackingDAO;

	public void setUserTrackingDAO(IUserTrackingDAO userTrackingDAO) {
		this.userTrackingDAO = userTrackingDAO;
	}
	
	public void test()
	{
		userTrackingDAO.getAll();
	}

}
