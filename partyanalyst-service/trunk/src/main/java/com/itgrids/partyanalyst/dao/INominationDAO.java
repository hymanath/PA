/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on July 09, 2009
 */
package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.Nomination;
import com.itgrids.partyanalyst.model.Party;

/**
 * Interface for NominationDAO.
 * 
 * @author <a href="mailto:shan.javaee@gmail.com">Shan Nagarajan</a>
 */

public interface INominationDAO extends GenericDao<Nomination, Long>{

	/**
	 * Find all Nomination entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Nomination property to query
	 * @param value
	 *            the property value to match
	 * @return List<Nomination> found by query
	 */
	//public List<Nomination> findByProperty(String propertyName, Object value);

	public List<Nomination> findByNominationStatus(Object nominationStatus);

	public List<Nomination> findByAssets(Object assets);

	public List<Nomination> findByLiabilities(Object liabilities);

	public List<Nomination> findByCriminalCharges(Object criminalCharges);

	public List<Nomination> findByConstituencyElection(Long constituencyElectionID);
	
	public List<Nomination> findByConstituencyElectionAndCandidate(String candidateName, Long constituencyElectionID);
	
	public List<Constituency> findConstitueniesByPartyAndElectionType(Long partyId, Long electionTypeId, String electionYear);
	
	public List<Nomination> findByConstituencyPartyAndElectionYear(Long partyId, Long constituencyId, String electionYear);
	
	public List findCandidatesInfoByConstituencyAndElectionYear(Long constituencyId, String electionYear);
	
	 public List<Nomination> findByStatePartyAndElectionId(Long stateId, Long electionId, Long partyId);
	 
	 public List<Nomination> findByElectionIdAndPartys(Long electionId,List<Long> partys);
	 
	 public List<Party> findPartiesByConstituencyAndElection(Long constituencyId, String electionYear);

	public List<Nomination> findByConstituencyPartyAndElectionYearIncludingAliance(List<Long> aliancePartyIds, Long pcId, String electionYear);
	
	public List findCandidateNamePartyByConstituencyAndElection(String constituencyIds, String electionYear);

	public List findMPTCInfoByElectionTypeTehsilAndParty(String mptcElectionType, Long tehsilID, Long partyId);
	
    public List<ConstituencyElection> findByElectionAndStateAndRank(Long electionID,Long stateId,Long rank,Long partyId);
	
	public List<ConstituencyElection> findByElectionAndStateAndNthRank(Long electionID,Long stateId,Long rank,Long partyId);
	
	public List<ConstituencyElection> findByElectionAndStateAndRank(Long electionID,Long stateId,Long rank,List<Long> partyIds);
	
	public List<ConstituencyElection> findByElectionAndStateAndNthRank(Long electionID,Long stateId,Long rank,List<Long> partyIds);
	
	public List<ConstituencyElection> findByElectionAndStateAndDistrictAndRank(Long electionID,Long stateId,Long districtID,Long rank,Long partyId);
	
	public List<ConstituencyElection> findByElectionAndStateAndDistrictAndNthRank(Long electionID,Long stateId,Long districtID,Long rank,Long partyId);
	
	public List<ConstituencyElection> findByElectionAndStateAndDistrictAndRank(Long electionID,Long stateId,Long districtID,Long rank,List<Long> partyIds);
	
	public List<ConstituencyElection> findByElectionAndStateAndDistrictAndNthRank(Long electionID,Long stateId,Long districtID,Long rank,List<Long> partyIds);
	
	public List<ConstituencyElection> findByElectionIdAndStateIdAndCountryIdAndRank(Long electionID,Long stateId,Long countryId,Long rank,Long partyId);
	
	public List<ConstituencyElection> findByElectionIdAndStateIdAndCountryIdAndNthRank(Long electionID,Long stateId,Long countryId,Long rank,Long partyId);
 
	@SuppressWarnings("unchecked")
	public List findValidVotesOfAllCandiatesOfAMandalByElectionTypeMandalAndYear(String electionType, String electionYear, Long tehsilId);
	
	public List findLocalLeadersOfMandal(Long tehsilId);
	
	public List<Nomination> findByElectionIdAndPartyId(Long electionId,Long partyId);
	
	public List<Nomination> findByElectionIdAndPartyIdStateIdAndDistrictId(Long electionId,Long partyId,Long stateId,Long districtId);
	
	public List getCandidateNPartyInfo(String constituencyIds,String electionType,Long rank);
	
    public List<ConstituencyElection> findConstituencyElectionByElectionIdAndPartyId(Long electionId,Long partyId);
	
	public List<ConstituencyElection> findConstituencyElectionByElectionIdAndPartys(Long electionId,List<Long> partyIds);
	
	public List<ConstituencyElection> findConstituencyElectionByElectionIdAndDistrictIdAndPartys(Long electionId,Long districtId,List<Long> partyIds);
	
	@SuppressWarnings("unchecked")
	public List findElectionDataByElectionId(Long electionId);
	
	public List getParliamentCandidateNPartyInfo(Long constituencyId,String electionType,Long rank);

	
	@SuppressWarnings("unchecked")
	public List findElectionResultsForACandidateForAnElectionInAConstituency(Long constituencyId,Long electionId,Long partyId);
	
	@SuppressWarnings("unchecked")
	public List findElectionResultsForAnElectionInAConstituencyWithoutSelectedParty(Long constituencyId,Long electionId,Long partyId);
	
	
}