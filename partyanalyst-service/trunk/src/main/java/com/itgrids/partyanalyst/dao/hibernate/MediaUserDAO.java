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

import com.itgrids.partyanalyst.dao.IMediaUserDAO;
import com.itgrids.partyanalyst.model.MediaUser;

public class MediaUserDAO extends GenericDaoHibernate<MediaUser, Long> implements
		IMediaUserDAO {

	public MediaUserDAO() {
		super(MediaUser.class);
	}

	@SuppressWarnings("unchecked")
	public List<MediaUser> findByUserId(Long userId) {
		return getHibernateTemplate().find("from MediaUser model where model.user.userId = ?", userId);
	}

	
}
