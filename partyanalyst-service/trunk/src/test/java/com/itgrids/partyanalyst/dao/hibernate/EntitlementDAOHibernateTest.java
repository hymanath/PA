package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IEntitlementDAO;
import com.itgrids.partyanalyst.model.Entitlement;

public class EntitlementDAOHibernateTest extends BaseDaoTestCase{

	private IEntitlementDAO entitlementDAO;

	public IEntitlementDAO getEntitlementDAO() {
		return entitlementDAO;
	}

	public void setEntitlementDAO(IEntitlementDAO entitlementDAO) {
		this.entitlementDAO = entitlementDAO;
	}
	
	public void testGetAll(){
		List<Entitlement> list = entitlementDAO.getAll();
		assertEquals(true, list.size() >= 0);
	}
}
