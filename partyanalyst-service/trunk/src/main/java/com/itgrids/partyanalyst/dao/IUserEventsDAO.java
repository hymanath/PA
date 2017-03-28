/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 11, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserEvents;



public interface IUserEventsDAO extends GenericDao<UserEvents,Long> {

	public List<UserEvents> findEventsByUserId(Long userId);
	public List<UserEvents> findEventsByUserIdAndStartDate(Long userId, Date startDate);
	public List<Long> checkEventBelongsToUser(Long userId,Long eventId);
}
