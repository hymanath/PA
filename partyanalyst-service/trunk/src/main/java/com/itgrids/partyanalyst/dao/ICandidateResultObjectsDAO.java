/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 17, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateResult;


public interface ICandidateResultObjectsDAO extends GenericDao<CandidateResult, Long> {

	
	public List<CandidateResult> findCandidateResultObjects(Long electionId);
	
	public List<CandidateResult> findCandidateResultObjects(Long candidateId,Long rank);
	
	public List<CandidateResult> findCandidateResults(Long candidateId);
	
	public List<CandidateResult> findCandidateResults(long candidateId,Long electionId,Long constituencyId);
}

