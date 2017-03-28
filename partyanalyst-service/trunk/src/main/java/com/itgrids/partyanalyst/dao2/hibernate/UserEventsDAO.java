/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 11, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserEventsDAO;
import com.itgrids.partyanalyst.model.UserEvents;

public class UserEventsDAO extends GenericDaoHibernate<UserEvents, Long> implements IUserEventsDAO {

	public UserEventsDAO() {
		super(UserEvents.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<UserEvents> findEventsByUserId(Long userId) {
		Object[] params = {"NO",userId};
		return getHibernateTemplate().find("from UserEvents model where model.isDeleted=? and model.user.userId = ?", params) ;
	}

	@SuppressWarnings("unchecked")
	public List<UserEvents> findEventsByUserIdAndStartDate(Long userId,
			Date startDate) {
		
		Object[] params = {"NO",userId, new SimpleDateFormat("yyyy-MM-dd").format(startDate)};
		return getHibernateTemplate().find("from UserEvents model where model.isDeleted=? and model.user.userId = ? and DATEDIFF(model.startDate, ?) = 0", params) ;
											
	}
	public List<Long> checkEventBelongsToUser(Long userId,Long eventId){
		Object[] params = {userId,eventId};
		return getHibernateTemplate().find("select count(*) from UserEvents model where model.user.userId = ? and model.userEventsId = ?", params) ;	
		
	}
}