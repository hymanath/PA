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

import com.itgrids.partyanalyst.model.Entitlement;
import com.itgrids.partyanalyst.model.UserGroups;

public interface IUserGroupsDAO extends GenericDao<UserGroups, Long> {

	public List<UserGroups> findByNotes(String notes);
	
	public List<UserGroups> getAllUserGroups();
	
	public List<Entitlement> checkForUserAvailability(String name);
}
