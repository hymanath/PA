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

import com.itgrids.partyanalyst.model.ConstituencyElectionResult;

/**
 * Interface for ConstituencyElectionResultDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IConstituencyElectionResultDAO extends GenericDao<ConstituencyElectionResult, Long>{

	/**
	 * Find all ConstituencyElectionResult entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the ConstituencyElectionResult property to query
	 * @param value
	 *            the property value to match
	 * @return List<ConstituencyElectionResult> found by query
	 */
	
	public List<ConstituencyElectionResult> findByTotalVotes(Object totalVotes);

	public List<ConstituencyElectionResult> findByValidVotes(Object validVotes);

	public List<ConstituencyElectionResult> findByRejectedVotes(
			Object rejectedVotes);

	public List<ConstituencyElectionResult> findByMissingVotes(
			Object missingVotes);

	public List<ConstituencyElectionResult> findByTotalVotesPolled(
			Object totalVotesPolled);

	public List<ConstituencyElectionResult> findByTenderedVotes(
			Object tenderedVotes);

	public List<ConstituencyElectionResult> findByConstituencyElections(String constituencyElectionIDs);
	
	public List<ConstituencyElectionResult> findByElectionTypeId_Year_StateId(Long electionTypeId, String electionYear, Long stateId);
	
	public List<ConstituencyElectionResult> findByElectionTypeId_Year_StateId_DistrictId(Long electionTypeId, String electionYear, Long stateId, Long districtId);
	
	public List<ConstituencyElectionResult> findByElectionTypeAndYearAndCountry(Long electionTypeId,String year,Long countryId);

	public List getTotalVotesAndValidVotesForMPTCZPTC(Long tehsilId,
			String electionType, String electionYear);
	
	public List<ConstituencyElectionResult> findByConstituency(Long constituencyId);
	
	public List<Object[]> findTotalVotersAndValidVotesByYearAndConstituencyIds(List<Long> constituencyIds, String year);
	
	public List<ConstituencyElectionResult> getConstituencyElectionResultByElection(Long electionId);

	public List<Object[]> findTotalVotesAndValidVotesAndPolledVotesAndVotesPercentage(Long electionId );
	
	public Object getResultKnownConstituenciesCountInAElection(Long electionId);

}