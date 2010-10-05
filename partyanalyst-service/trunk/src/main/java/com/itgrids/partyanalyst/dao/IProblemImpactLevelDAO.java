/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 29, 2010
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemImpactLevel;

public interface IProblemImpactLevelDAO extends GenericDao<ProblemImpactLevel, Long> {
	
	@SuppressWarnings("unchecked")
	public List getProblemImpactLevelByName(String name);

}
