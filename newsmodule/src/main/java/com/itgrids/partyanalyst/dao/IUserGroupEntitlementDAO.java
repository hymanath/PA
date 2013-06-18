/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserGroupEntitlement;

public interface IUserGroupEntitlementDAO extends GenericDao<UserGroupEntitlement, Long> {

	public List<UserGroupEntitlement> findByUserGroupId(Long userGroupId);
	
	public List getAllGroupsForAUser(Long userGroupId);
	
	public List getAllEntitlementGroupsBasedOnUserGroupId(Long userGroupId);
	
	public Integer deleteAllRelations(Long groupId);
}
