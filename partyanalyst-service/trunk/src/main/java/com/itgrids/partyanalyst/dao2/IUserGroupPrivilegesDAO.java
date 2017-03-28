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

import com.itgrids.partyanalyst.model.UserGroupPrivileges;

public interface IUserGroupPrivilegesDAO extends GenericDao<UserGroupPrivileges, Long> {
public List<UserGroupPrivileges> findByUserGroupPrivilegeId(Long userGroupPrivilegeId);
}

