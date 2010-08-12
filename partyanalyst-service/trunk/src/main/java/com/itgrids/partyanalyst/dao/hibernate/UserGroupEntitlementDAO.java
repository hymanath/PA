/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 10,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserGroupEntitlementDAO;
import com.itgrids.partyanalyst.model.UserGroupEntitlement;

/**
 * @author ITGrids
 *
 */
public class UserGroupEntitlementDAO extends GenericDaoHibernate<UserGroupEntitlement, Long>
		implements IUserGroupEntitlementDAO {

	public UserGroupEntitlementDAO() {
		super(UserGroupEntitlement.class);
	}

	/* (non-Javadoc)
	 * @see com.itgrids.partyanalyst.dao.IUserGroupEntitlementDAO#findByUserGroupId(java.lang.Long)
	 */
	public List<UserGroupEntitlement> findByUserGroupId(Long userGroupId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
