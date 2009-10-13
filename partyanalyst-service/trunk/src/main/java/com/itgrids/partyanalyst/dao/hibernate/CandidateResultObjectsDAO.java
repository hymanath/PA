/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on September 17, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateResultObjectsDAO;
import com.itgrids.partyanalyst.model.CandidateResult;


public class CandidateResultObjectsDAO extends GenericDaoHibernate<CandidateResult, Long>
		implements ICandidateResultObjectsDAO {

	
	/*  Returns The List Candidate Result Objects 
	 * 
	 */
	
	public CandidateResultObjectsDAO() {
		super(CandidateResult.class);
		
	}

	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResultObjects(Long electionId) {
		
		Query queryObject = getSession().createQuery("from CandidateResult as model where model.nomination.constituencyElection.election.electionId = ?");
		 queryObject.setParameter(0, electionId);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResultObjects(Long candidateId,Long rank){
		
		Query queryObject = getSession().createQuery("from CandidateResult as model where model.rank = ? and model.nomination.candidate.candidateId = ?");
		 queryObject.setParameter(0, rank);
		 queryObject.setParameter(1, candidateId);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResults(Long candidateId){
		Query queryObject = getSession().createQuery("from CandidateResult as model where model.nomination.candidate.candidateId = ?");
		  queryObject.setParameter(0, candidateId);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResults(long candidateId,Long electionId,Long constituencyId){
		Query queryObject = getSession().createQuery("from CandidateResult as model where model.nomination.candidate.candidateId != ? and model.nomination.constituencyElection.election.electionId = ? and model.nomination.constituencyElection.constituency.constituencyId = ?");
		  queryObject.setParameter(0, candidateId);
		  queryObject.setParameter(1, electionId);
		  queryObject.setParameter(2, constituencyId);
		return queryObject.list();
	}

	
}
