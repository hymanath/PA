package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserGroupEntitlementDAO;
import com.itgrids.partyanalyst.model.UserGroupEntitlement;

public class UserGroupEntitlementDAOHibernateTest extends BaseDaoTestCase{

	private IUserGroupEntitlementDAO userGroupEntitlementDAO;

	public IUserGroupEntitlementDAO getUserGroupEntitlementDAO() {
		return userGroupEntitlementDAO;
	}

	public void setUserGroupEntitlementDAO(
			IUserGroupEntitlementDAO userGroupEntitlementDAO) {
		this.userGroupEntitlementDAO = userGroupEntitlementDAO;
	}
	
	public void testGetAll(){
		List<UserGroupEntitlement> list = userGroupEntitlementDAO.getAll();
		assertEquals(true, list.size() >= 0);
	}
}
