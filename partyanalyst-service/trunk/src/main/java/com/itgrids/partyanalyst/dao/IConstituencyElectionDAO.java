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

import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;

/**
 * Interface for ConstituencyElectionDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface IConstituencyElectionDAO extends GenericDao<ConstituencyElection, Long>{

	/**
	 * Find all ConstituencyElection entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the ConstituencyElection property to query
	 * @param value
	 *            the property value to match
	 * @return List<ConstituencyElection> found by query
	 */
	public List<ConstituencyElection> findByProperty(String propertyName,
			Object value);
	public List<ConstituencyElection> findByElection(Long electionID);
	public List findByElectionAndDistrict(Long electionID, Long districtID);
	public List<ConstituencyElection> findByElectionAndConstituency(Long electionID, Long constituencyID);
	
	public List findAllElectionsByConstituencyId(Long constituencyId);
	public List<ConstituencyElection> findByConstituencyElectionAndDistrict(String electionYear, String constituencyName, Long electionScopeId, Long districtId);
	public List<ConstituencyElection> findByConstituencyElectionAndState(String electionYear, String parliamentConstituencyName, Long electionScopeId, Long stateId);
	
	public List<ConstituencyElection> findByElectionScopeAndYear(Long electionScopeId,String year);
	
	public List<ConstituencyElection> findByElectionAndState(Long electionID,Long stateId);
	
	public List<ConstituencyElection> findByElectionAndStateAndDistrict(Long electionID,Long stateId,Long districtID);
	
	public List<Constituency> findConstituencyByElectionAndDistrict(Long electionId,Long districtId);
	
	@SuppressWarnings("unchecked")
	public List findTotalValidVotesForAnElectionForAState(Long electionId);
	
	@SuppressWarnings("unchecked")
	public List findTotalValidVotesForAnElectionForAnState(Long electionId,Long stateId);
	
	@SuppressWarnings("unchecked")
	public List findTotalValidVotesForAnElectionForAStateAndDistrict(Long electionId,Long districtId);
	
	@SuppressWarnings("unchecked")
	public List findTotalValidVotesForAnElectionForAStateAndDistrictForLocalElectionBody(Long electionId,Long districtId);
	
    @SuppressWarnings("unchecked")
	public List findTotalConstituenciesCountInADistrictForAnElection(Long electionId,Long districtId);
    
    public List findTotalAssemblyConstituencies(Long electionId,Long stateId);
    
    public List getTotalValidVotesParticularElectionYear(String electionType,String electionYear,Long districtId);

	public List findByDistrictElectionConstituency(Long electionId,
			Long districtId, String constituencyName);

	public List<Long> findConstituencyByDistrictAndStateIds(Long districtId,Long stateId,String electionType);
	
	public List getTotalValidVotesForATehsilForAParticularElectionYear(String electionType,String tehsilIds,String electionYear);
	
	@SuppressWarnings("unchecked")
	public List findElectionIdForAParticularElectionTypeAndYearAndConstituency(String electionType,String electionYear,Long constituencyId);
	
	@SuppressWarnings("unchecked")
	public List findConstituenciesCountInAnElection(Long electionId);
	
	public List findTotalValidVotesInConstituencyElection(Long constituencyId,
			String electionYear);
	public List findConstituenciesByElectionGroupByDistrict(Long electionId);
	public List findConstituenciesByElectionGroupByState(Long electionId);
	
	public List getVotesDataForAConstituency(Long constituencyId,Long electionId);
	
	@SuppressWarnings("unchecked")
	public List getValidVotesForAConstituency(Long constId,String elecYear);
	
	public List getValidVotesForMptcZptcElectionsInMandals(String mandalIds);
	
	public List getValidVotesForMunicipalitiesAndCorporationsInMandals(String mandalIds);
	
	public List getTotalVotersForATehsilForAParticularElectionYear(String electionType,String tehsilIds,String electionYear);
	
	@SuppressWarnings("unchecked")
	public List getParticipatedStateDetailsForAnElectionType(Long electionType);
	
	public List findPartyvalidVotesInfoByElectionAndPartyGroupByDistrictId(Long electionId, String partyIds);
	
	public List findPartyvalidVotesInfoByElectionAndPartyGroupByStateId(Long electionId, String partyIds); 
	
	public List getConstituenciesCountByDistrictForStateAndElection(Long stateId, Long electionId);
	
	public List getConstituenciesCountByStateForCountryAndElection(Long countryId, Long electionId);
	
	@SuppressWarnings("unchecked")
	public List getConstituencyValidVotesForLocalBodyElection(Long localBodyId,String electionYear);
	
	@SuppressWarnings("unchecked")
	public List getConstituencyVotesInfoForLocalBodyElection(Long localBodyId,Long electionId);
	
	@SuppressWarnings("unchecked")
	public List getConstituencyVotesInfoForLocalBodyElection(Long localBodyId,Long electionId,Long wardId);
	
	@SuppressWarnings("unchecked")
	public List getAllWardsDetailsParticipatedInALocalBodyElection(Long localBodyId,Long electionId);
	
	@SuppressWarnings("unchecked")
	public List getLocalBodyElectionsInAState(Long localBodyId,Long stateId);
	
	@SuppressWarnings("unchecked")
	public List getLatestReservationZone(List<Long> constituencyIds,Long electionYear);
	
	@SuppressWarnings("unchecked")
	public List findConstituencyByDistrictAndStateIds(Long districtId,Long stateId,String electionYear,String electionType);
	
	@SuppressWarnings("unchecked")
	public List findConstituencyByDistrictAndStateIdsForLocalBodys(Long districtId,Long stateId,String electionYear,String electionType);
	
	@SuppressWarnings("unchecked")
	public List getConstituenciesHavingMaxSpan(String electionSubType,String electionType,Long stateId,List<Long> elecIds,String type);
	
	public List<Long> getLatestConstituencies(List<Long> constIds,Long latestElecId);
	
	public List<ConstituencyElection> findConstituencyElectionsByElection(Long electionId);
	
	public List getLatestResultsElectionYearInAConstituency(Long constituencyId);
	
	public List findAllAssetsAndLiabilitiesElectionsByConstituencyId(Long constituencyId);
	
	public List getCountOfOldConstituencies(Long electionId);
	
	public List getCountOfDelimitedConstituencies(Long electionId);
	
	public List<Object[]> getPartyWinningConstituenciesCount(Long electionId);
	
}