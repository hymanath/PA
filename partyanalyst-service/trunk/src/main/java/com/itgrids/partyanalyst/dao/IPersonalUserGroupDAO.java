/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February, 2010
 * Author Saikrishna.g
 */

package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PersonalUserGroup;

public interface IPersonalUserGroupDAO extends GenericDao<PersonalUserGroup, Long> {

	
	public List<PersonalUserGroup> getAllMyGroupsByUserId(Long userId);
	
	@SuppressWarnings("unchecked")
	public List getSubGroupsCountInMyGroupsByUserId(Long userId, Long myGroupId);
	
	@SuppressWarnings("unchecked")
	public List findSubGroupsCountInSystemGroupsByUserId(Long userId);	
}
