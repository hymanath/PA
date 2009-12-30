/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 24, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstituencyElectionResult;


public interface IConstituencyElectionResultObjectsDAO extends GenericDao<ConstituencyElectionResult, Long> {

	/*
	 * 
	 */
	 public List<ConstituencyElectionResult> findConstituencyElectionResultObjects(Long constituencyId);
}
