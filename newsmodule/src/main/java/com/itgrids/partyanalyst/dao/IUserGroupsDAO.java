/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Entitlement;
import com.itgrids.partyanalyst.model.UserGroups;

public interface IUserGroupsDAO extends GenericDao<UserGroups, Long> {

	public List<UserGroups> findByNotes(String notes);
	
	public List<UserGroups> getAllUserGroups();
	
	public List<Entitlement> checkForUserAvailability(String name);
}
