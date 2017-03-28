/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 10, 2009
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
