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
	
}
