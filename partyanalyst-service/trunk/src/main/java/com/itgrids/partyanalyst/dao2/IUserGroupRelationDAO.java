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

import com.itgrids.partyanalyst.model.UserGroupRelation;

public interface IUserGroupRelationDAO extends
		GenericDao<UserGroupRelation, Long> {

	public List<UserGroupRelation> findByUserId(Long userId);
	
	public List checkTheRelationBetweenUserAndGroup(Long userId);
	
	public Integer deleteAllUser(Long userId);
}
