/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateResult;

/**
 * Interface for CandidateResultDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface ICandidateResultDAO extends GenericDao<CandidateResult, Long>{

	/**
	 * Find all CandidateResult entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the CandidateResult property to query
	 * @param value
	 *            the property value to match
	 * @return List<CandidateResult> found by query
	 */
	//public List<CandidateResult> findByProperty(String propertyName,
			//Object value);

	public List<CandidateResult> findByVotesEarned(Object votesEarned);

	public List<CandidateResult> findByRank(Object rank);
	
	//public List<CandidateResult> findByNominationIDs(String nominationIDs, Long compitetorsSize);
	
    public List<Long> findCandidateResultsCount(Long electionScopeId,Long partyId,String year);
	
	public List<CandidateResult> findCandidateResults(Long electionScopeId,Long partyId,String year);
	
	public List<CandidateResult> findCandidateResults(Long electionScopeId,List<Long> partyIds,String year);
	
	public List getMPTCElectionResultForMandal(Long mandalID);
	
	@SuppressWarnings("unchecked")
	public List getVotesPercentOfACandidateInAnElection(Long electionId,Long constituencyId,Long rank);
	
	@SuppressWarnings("unchecked")
	public List getElectionResultsForAllPartiesInAMandal(Long mandalId,String electionType,String electionYear);
	
	public List<CandidateResult> findCandidateResultObjects(Long electionId);
	
	public List<CandidateResult> findCandidateResultObjects(Long candidateId,Long rank);
	
	public List<CandidateResult> findCandidateResults(Long candidateId);
	
	public List<CandidateResult> findCandidateResults(Long candidateId,Long electionId,Long constituencyId);
	
	public int updateMarginVotesAndPercentage(String marginPercentage,Double marginVotes,String electionYear,String electionType,
			Long constituencyId,Long candidateId);
}