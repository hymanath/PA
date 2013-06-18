/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
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
