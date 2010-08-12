/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IGroupEntitlementDAO;
import com.itgrids.partyanalyst.model.GroupEntitlement;

public class GroupEntitlementDAO extends GenericDaoHibernate<GroupEntitlement, Long> implements
		IGroupEntitlementDAO {

	public GroupEntitlementDAO() {
		super(GroupEntitlement.class);
	}

	

}
