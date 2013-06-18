/* 
 * Copyright (c) 2013 TDP PARTY .
 * All Rights Reserved.
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserEventActionPlan;

public interface IUserEventActionPlanDAO extends GenericDao<UserEventActionPlan, Long> {

	public List<UserEventActionPlan> findByUserEventsId(Long userEventsId);
	
	public void removeEventActionPlans(Long eventId);
	
	public void removeDeletedEventActionPlans(Long eventId,List<Long> eventActionPlanIds);
	
	public List<Long> getEventActionPlanIds(Long eventId);
	
	public List<Long> getEventActionPlanIds(Long eventId,List<Long> eventActionPlanIds);
	
}
