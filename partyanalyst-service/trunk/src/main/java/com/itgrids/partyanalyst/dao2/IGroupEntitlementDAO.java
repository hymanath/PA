/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.GroupEntitlement;
import com.itgrids.partyanalyst.model.GroupEntitlementRelation;

public interface IGroupEntitlementDAO extends
		GenericDao<GroupEntitlement, Long> {

	public List<GroupEntitlementRelation> getfindGroupEntitlementsByGroupName(String groupName);
	
	public List<GroupEntitlement> getAllGroups();
	
	public List<GroupEntitlement> checkForGroupNameAvailability(String name);
}
