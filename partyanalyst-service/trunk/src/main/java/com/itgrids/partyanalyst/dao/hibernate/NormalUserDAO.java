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

import com.itgrids.partyanalyst.dao.INormalUserDAO;
import com.itgrids.partyanalyst.model.NormalUser;

public class NormalUserDAO extends GenericDaoHibernate<NormalUser, Long> implements
		INormalUserDAO {

	public NormalUserDAO() {
		super(NormalUser.class);
	}

	@SuppressWarnings("unchecked")
	public List<NormalUser> findByUserId(Long userId) {
		return getHibernateTemplate().find("from NormalUser model where model.user.userId = ?", userId);
	}

	
}
