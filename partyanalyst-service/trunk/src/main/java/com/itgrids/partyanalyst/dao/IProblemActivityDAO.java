/* 
 * Copyright (c) 2011 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on February 19, 2011
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemActivity;

/**
 * @author Sai Krishna
 *
 */
public interface IProblemActivityDAO extends GenericDao<ProblemActivity, Long> {

	public List<ProblemActivity> getProblemActivityByName(String activityName);
}
