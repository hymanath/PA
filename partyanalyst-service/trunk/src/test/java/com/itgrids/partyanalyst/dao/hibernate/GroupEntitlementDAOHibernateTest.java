package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IGroupEntitlementDAO;
import com.itgrids.partyanalyst.model.GroupEntitlement;

public class GroupEntitlementDAOHibernateTest extends BaseDaoTestCase{

	private IGroupEntitlementDAO groupEntitlementDAO;

	public IGroupEntitlementDAO getGroupEntitlementDAO() {
		return groupEntitlementDAO;
	}

	public void setGroupEntitlementDAO(IGroupEntitlementDAO groupEntitlementDAO) {
		this.groupEntitlementDAO = groupEntitlementDAO;
	}
	
	public void testGetAll(){
		List<GroupEntitlement> list = groupEntitlementDAO.getAll();
		assertEquals(true, list.size() >= 0);
	}
}
