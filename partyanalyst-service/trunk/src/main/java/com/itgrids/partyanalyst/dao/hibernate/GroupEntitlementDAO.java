/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGroupEntitlementDAO;
import com.itgrids.partyanalyst.model.GroupEntitlement;
import com.itgrids.partyanalyst.model.GroupEntitlementRelation;

public class GroupEntitlementDAO extends GenericDaoHibernate<GroupEntitlement, Long> implements
		IGroupEntitlementDAO {

	public GroupEntitlementDAO() {
		super(GroupEntitlement.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<GroupEntitlementRelation> getfindGroupEntitlementsByGroupName(String groupName){
		return getHibernateTemplate().find("select model.groupEntitlementRelations from GroupEntitlement model where model.description = ?", groupName);
	}

}
