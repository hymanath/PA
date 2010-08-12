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

import com.itgrids.partyanalyst.dao.IUserGroupRelationDAO;
import com.itgrids.partyanalyst.model.UserGroupRelation;

public class UserGroupRelationDAO extends GenericDaoHibernate<UserGroupRelation, Long> implements
		IUserGroupRelationDAO {

	public UserGroupRelationDAO() {
		super(UserGroupRelation.class);
	}

	@SuppressWarnings("unchecked")
	public List<UserGroupRelation> findByUserId(Long userId) {
		return getHibernateTemplate().find("from UserGroupRelation model where model.user.userId = ?",userId);
	}

}
