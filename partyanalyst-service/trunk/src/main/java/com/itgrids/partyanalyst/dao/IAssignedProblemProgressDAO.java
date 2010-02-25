/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AssignedProblemProgress;

public interface IAssignedProblemProgressDAO extends GenericDao<AssignedProblemProgress, Long> {

	public List findProblemsForAHamletByHistoryId(Long historyId);
	
	public List<AssignedProblemProgress> findByRegistrationIdAndStatusId(Long registrationId, Long statusId);	
}
