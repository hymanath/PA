/* 
 * Copyright (c) 2009 IT Grids Limited.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on December 23, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ProblemClassification;

public interface IProblemClassificationDAO extends GenericDao<ProblemClassification, Long> {

	public List<ProblemClassification> findByClassification(String problemClassification);
	public List<Long> getProblemClassificationId(String problemClassification);

}
