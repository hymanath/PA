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
		/*Query query = getSession().createQuery("SELECT  ue.user_event_id, ue.registration.registarion_id, ue.description, ue.location_type, ue.location_id, ue.start_date, ue.end_date, ue.title, ue.is_deleted, " +
												"CASE " +
												"WHEN ue.location_type='MANDAL' " +
												"THEN (select tehsil_name from tehsil where tehsil_id = ue.location_id) " +
												"WHEN ue.location_type ='DISTRICT' " +
												"THEN select district_name from district where district_id = ue.location_id) " +
												"END location " +
												"FROM user_event ue " +
												"WHERE ue.user_id = r.registration_id " +
												"AND r.registration_id = :userId " +
												"AND is_deleted = 'NO' " +
												"AND DATEDIFF(ue.startDate, :date) = 0");
		query.setLong("userId", userId);
		query.setString("date", new SimpleDateFormat("yyyy-MM-dd").format(startDate));
		return (List<UserEvents>) query.list();*/
										
	}
}