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

import com.itgrids.partyanalyst.dao.IUserGroupsDAO;
import com.itgrids.partyanalyst.model.Entitlement;
import com.itgrids.partyanalyst.model.UserGroups;

public class UserGroupsDAO extends GenericDaoHibernate<UserGroups, Long> implements
		IUserGroupsDAO {

	public UserGroupsDAO() {
		super(UserGroups.class);
	}

	public List<UserGroups> findByNotes(String notes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserGroups> getAllUserGroups(){		
		return getHibernateTemplate().find(" select model.userGroupId,model.notes from UserGroups model order by model.notes asc");
	}

	@SuppressWarnings("unchecked")
	public List<Entitlement> checkForUserAvailability(String name){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.userGroupId,model.notes from UserGroups model");
		sb.append(" where model.notes = ?");
		return getHibernateTemplate().find(sb.toString(),name);
		
	}
}