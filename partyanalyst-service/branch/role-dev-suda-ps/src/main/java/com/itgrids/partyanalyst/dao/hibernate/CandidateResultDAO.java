/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 11, 2009
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICandidateResultDAO;
import com.itgrids.partyanalyst.model.CandidateResult;


import com.itgrids.partyanalyst.dao.columns.enums.CandidateResultColumnNames;



public class CandidateResultDAO extends GenericDaoHibernate<CandidateResult, Long> implements
		ICandidateResultDAO {
  
	public CandidateResultDAO() {
		super(CandidateResult.class);
	}	
	

	public List<CandidateResult> findByRank(Object rank) {
		
		return findByProperty(CandidateResultColumnNames.RANK, rank);
	}

	public List<CandidateResult> findByVotesEarned(Object votesEarned) {
		
		return findByProperty(CandidateResultColumnNames.VOTES_EARNED, votesEarned);
	}
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findByProperty(CandidateResultColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from CandidateResult where " + propertyName.getValue() + "=?", value);		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> findCandidateResultsCount(Long electionScopeId,Long partyId,String year){
		
		Query queryObject = getSession().createQuery("select count(model.candidateResultId) from CandidateResult as model where model.nomination.nominationId in ( select model.nomination.nominationId from model where model.nomination.constituencyElection.election.electionScope.electionScopeId = ? and model.nomination.constituencyElection.election.electionYear = ?) and model.nomination.party.partyId = ?" );
		  queryObject.setParameter(0, electionScopeId);
		  queryObject.setParameter(1, year);
		  queryObject.setParameter(2, partyId);
		return queryObject.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResults(Long electionScopeId,Long partyId,String year){
		
		Query queryObject = getSession().createQuery("from CandidateResult as model where model.nomination.party.partyId = ? and model.nomination.nominationId in ( select model.nomination.nominationId from model where model.nomination.constituencyElection.election.electionScope.electionScopeId = ? and model.nomination.constituencyElection.election.electionYear = ?)" );
		  queryObject.setParameter(0, partyId);
		  queryObject.setParameter(1, electionScopeId);
		  queryObject.setParameter(2, year);
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<CandidateResult> findCandidateResults(Long electionScopeId,List<Long> partyIds,String year){
		
		StringBuffer queryBuffer = new StringBuffer("from CandidateResult as model where model.nomination.nominationId in ( select model.nomination.nominationId from model where model.nomination.constituencyElection.election.electionScope.electionScopeId = " + electionScopeId + " and model.nomination.constituencyElection.election.electionYear = " + year + " ) and model.nomination.party.partyId in(");
		for(int i=0;i<partyIds.size();i++){
			queryBuffer.append(partyIds.get(i) + ",");
		}
		String query = queryBuffer.toString().substring(0, queryBuffer.toString().length()-1);
		query = query + "))";
		
		return getHibernateTemplate().find(query);
		
	}
	
}
