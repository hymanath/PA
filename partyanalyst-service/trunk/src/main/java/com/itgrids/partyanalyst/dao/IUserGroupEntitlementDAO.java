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

import com.itgrids.partyanalyst.model.UserGroupEntitlement;

public interface IUserGroupEntitlementDAO extends GenericDao<UserGroupEntitlement, Long> {

	public List<UserGroupEntitlement> findByUserGroupId(Long userGroupId);
	
	public List getAllGroupsForAUser(Long userGroupId);
	
	public List getAllEntitlementGroupsBasedOnUserGroupId(Long userGroupId);
	
	public Integer deleteAllRelations(Long groupId);
}
