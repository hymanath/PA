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
import org.hibernate.Query;

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
	
	public List getAllGroupsForAUser(Long userGroupId) {	
		StringBuilder query = new StringBuilder();		
		query.append(" select model.userGroup.userGroupId,model.groupEntitlement.groupEntitlementId from UserGroupEntitlement model");		
		query.append(" where model.userGroup.userGroupId = ? ");		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong(0, userGroupId);
		return queryObject.list();
	}
	
	public List getAllEntitlementGroupsBasedOnUserGroupId(Long userGroupId) {	
		StringBuilder query = new StringBuilder();		
		query.append(" select model.groupEntitlement.groupEntitlementId,model.groupEntitlement.description from UserGroupEntitlement model");		
		query.append(" where model.userGroup.userGroupId = ? ");		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setLong(0, userGroupId);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public Integer deleteAllRelations(Long groupId) {		
		StringBuilder query = new StringBuilder();
		query.append(" delete from UserGroupEntitlement model ");
		query.append(" where model.userGroup.userGroupId = ? ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, groupId);
		return queryObject.executeUpdate();		
	}
}
