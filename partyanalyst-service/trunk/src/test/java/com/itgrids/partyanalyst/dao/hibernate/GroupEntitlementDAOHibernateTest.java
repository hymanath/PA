package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IGroupEntitlementDAO;
import com.itgrids.partyanalyst.model.GroupEntitlement;
import com.itgrids.partyanalyst.model.GroupEntitlementRelation;
import com.itgrids.partyanalyst.utils.IConstants;

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
	
	public void testGetfindGroupEntitlementsByGroupName(){
		List<GroupEntitlementRelation> list = groupEntitlementDAO.getfindGroupEntitlementsByGroupName(IConstants.DEFAULT_ENTITLEMENTS_GROUP);
		for(GroupEntitlementRelation set:list){
			System.out.println(set.getEntitlement().getEntitlementType());
		}
		System.out.println(list.size());
	}
}
