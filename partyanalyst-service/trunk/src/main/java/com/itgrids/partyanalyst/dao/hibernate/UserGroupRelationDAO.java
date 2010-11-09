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

import com.itgrids.partyanalyst.dao.IUserGroupRelationDAO;
import com.itgrids.partyanalyst.model.UserGroupRelation;
import com.itgrids.partyanalyst.utils.IConstants;

public class UserGroupRelationDAO extends GenericDaoHibernate<UserGroupRelation, Long> implements
		IUserGroupRelationDAO {

	public UserGroupRelationDAO() {
		super(UserGroupRelation.class);
	}

	@SuppressWarnings("unchecked")
	public List<UserGroupRelation> findByUserId(Long userId) {
		return getHibernateTemplate().find("from UserGroupRelation model where model.user.registrationId = ?",userId);
	}
	
	@SuppressWarnings("unchecked")
	public List checkTheRelationBetweenUserAndGroup(Long userId) {
		
		StringBuilder query = new StringBuilder();
		query.append(" select model.userGroup.userGroupId,model.user.registrationId from UserGroupRelation model ");
		query.append(" where model.user.registrationId = ? ");
		Query queryObject = getSession().createQuery(query.toString());
		
		queryObject.setLong(0,userId);		
		return queryObject.list();
		
	}

}
