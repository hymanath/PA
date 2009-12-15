/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 11, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IUserEventsDAO;
import com.itgrids.partyanalyst.model.UserEvents;

public class UserEventsDAO extends GenericDaoHibernate<UserEvents, Long> implements IUserEventsDAO {

	public UserEventsDAO() {
		super(UserEvents.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<UserEvents> findEventsByUserId(Long userId) {
		
		return getHibernateTemplate().find("from UserEvents model where model.registration.registrationId = ?", userId) ;
	}
}
