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

import com.itgrids.partyanalyst.dao.ICandidateUserDAO;
import com.itgrids.partyanalyst.model.CandidateUser;

public class CandidateUserDAO extends GenericDaoHibernate<CandidateUser, Long> implements
		ICandidateUserDAO {

	public CandidateUserDAO() {
		super(CandidateUser.class);
	}

	@SuppressWarnings("unchecked")
	public List<CandidateUser> findByUserId(Long userId) {
		return getHibernateTemplate().find("from CandidateUser model where model.user.userId = ?", userId);
	}

}
