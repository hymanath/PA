package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserAccessIpAddressDAO;

public class UserAccessIpAddressDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserAccessIpAddressDAO userAccessIpAddressDAO;

	public void setUserAccessIpAddressDAO(
			IUserAccessIpAddressDAO userAccessIpAddressDAO) {
		this.userAccessIpAddressDAO = userAccessIpAddressDAO;
	}

	/*public void test()
	{
		userAccessIpAddressDAO.getAll();
	}*/
	
	public void testCheckForUserAccessIPAddress()
	{
		System.out.println(userAccessIpAddressDAO.checkForUserAccessIPAddress(1l,"127.0.0.2"));
	}
}
