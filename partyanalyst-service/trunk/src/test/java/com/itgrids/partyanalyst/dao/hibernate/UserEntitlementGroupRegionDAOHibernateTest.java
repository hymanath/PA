package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserEntitlementGroupRegionDAO;

public class UserEntitlementGroupRegionDAOHibernateTest extends BaseDaoTestCase{
	
	private IUserEntitlementGroupRegionDAO userEntitlementGroupRegionDAO;

	public void setUserEntitlementGroupRegionDAO(
			IUserEntitlementGroupRegionDAO userEntitlementGroupRegionDAO) {
		this.userEntitlementGroupRegionDAO = userEntitlementGroupRegionDAO;
	}
	
	public void test()
	{
		userEntitlementGroupRegionDAO.getAll();
	}

}
