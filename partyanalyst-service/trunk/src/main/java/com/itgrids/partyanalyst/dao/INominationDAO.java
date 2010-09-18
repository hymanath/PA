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
import com.itgrids.partyanalyst.model.Election;
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

	public List findMPTCInfoByElectionTypeTehsilAndParty(Long tehsilID, Long partyId);
	
	public List<ConstituencyElection> findByElectionAndPartyAndRank(Long electionID,Long rank,Long partyId);
	
	public List<ConstituencyElection> findByElectionAndPartyAndNthRank(Long electionID,Long rank,Long partyId);

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
	
	public List<Nomination> findByElectionIdAndPartyIdStateIdAndDistrictId(Long electionId,Long partyId, Long districtId);
	
	public List getCandidateNPartyInfo(String constituencyIds,String electionType,Long rank, String electionSubtype,Long stateId);
	
	public List getCandidateNPartyInfoForParliament(String constituencyIds,String electionType,Long rank, String electionSubtype);
	
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

	public List getAllPartiesOfElectionTypeInMandal(Long tehsilId);

	public List getAllConstiteunciesInfoForPartyInTehsil(Long tehsilId,	Long partyId, Long electionId);

	public List findAllMptcAndZptcElectionsInfoInMandal(Long tehsilId);
	
	public List findAllCandidatesForAnElectionByElectionYear(Long constituencyId);	
			
	public List findAllCandidatesForAnElectionByElectionYearByDistrictId(Long districtId,String electionType);
		
	public List getPartysInfoForAParticularElectionYear(String electionType,String electionYear,Long districtId);
	
	public List getPartysWinningCandidateInfoForAParticularElectionYear(String electionType,String electionYear,Long rank,Long districtId);
	    
    public List getAllCandidatesByElectionTypes(String electionTypes);
   	
	public List findAllZPTCsInaDistrict(Long districtId,String electionTypes,Long rank,String electionYear);
	
	public List findAllMPTCsInaDistrict(Long districtId,String electionType,Long rank,String electionYear);
	
	
	public List findAllZptcCandidatesInaDistrict(Long districtId,String electionTypes,String electionYear);
	
	public List findAllMptcCandidatesInaDistrict(Long districtId,String electionType,String electionYear);
	
	
	public List findAllZptcPartysInaDistrict(Long districtId,String electionTypes,String electionYear,Long partyId,Long rank);
	
	public List findAllMptcPartysInaDistrict(Long districtId,String electionType,String electionYear,Long partyId,Long rank);
	
	
	public List findAllZptcPartysWinnerInaDistrict(Long districtId,String electionTypes, String electionYear,Long partyId,Long rank);
	
	public List findAllMptcPartysWinnerInaDistrict(Long districtId,String electionType, String electionYear,Long partyId,Long rank);
	
	public List getAllPartysForAParticularElectionYear(Long districtId,String electionType,String electionYear);	
	
	@SuppressWarnings("unchecked")
	public List getPartyIdAndShortNameForThatParticipatedInAElection(Long electionId);
		
	public List getCandidatesInfoForTheGivenConstituencyBasedOnRank(String constituencyId,String electionYear,String electionType,Long rank);
	
	public List getCandidatesInfoForTheGivenConstituency(String constituencyId,String electionYear,String electionType);
	
	public List getCandidatesInfoForTheGivenConstituencyBasedOnRankAndPartyId(String constituencyId,String electionYear,String electionType,Long rank,Long partyId);
	
	public List getCandidatesInfoForTheGivenConstituencyByPartyId(String constituencyId,String electionYear,String electionType,Long partyId);
	
	public List findAllCandidatesForAnElectionBytheElectionYearByRank(String electionYear,Long stateId,String electionType,Long rank);
	
	public List findAllCandidatesForAnElectionBytheElectionYear(String electionYear,Long stateId,String electionType);
	
	public List findAllCandidatesForAnElectionBytheElectionYearByRankForAParty(String electionYear,Long stateId,String electionType,Long rank,Long partyId);
	
	public List findAllAssemblyCandidatesForAnElectionBytheElectionYear(String electionYear,Long stateId,String electionType,Long partyId);

	public List findAllCandidatesForAnElectionBytheElectionYearByCountryId(String electionYear,Long countryId,String electionType);
	
	public List findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRank(String electionYear,Long countryId,String electionType,Long rank);
	
	public List findAllCandidatesForAnElectionBytheElectionYearByCountryIdForAParty(String electionYear,Long countryId,String electionType,Long partyId);

	public List findAllCandidatesForAnElectionBytheElectionYearByCountryIdByRankForAParty(String electionYear,Long countryId,String electionType,Long rank,Long partyId);

	public List getAllPartiesForAnElectionYear(String electionYear,String electionType);
	
	public List findAllZPTCsOrMPTCsInaState(Long stateId,String electionType,String electionYear);
	
	public List findAllZPTCsOrMPTCsInaStateForAParty(Long stateId,String electionType,String electionYear,Long partyId);
	
	public List findAllZPTCsOrMPTCsInaStateForAPartyByRank(Long stateId,String electionType,String electionYear,Long partyId,Long rank);
	
	public List findAllZPTCsOrMPTCsInaStateByRank(Long stateId,String electionType,String electionYear,Long rank);
	
	public List getAllPartyDetailsForAllElectionYearsInADistrict(Long districtId);
	
	public List getTotalVotesPolledInADistrictForAllElectionYears(Long districtId);

	public List getAllElectionsInDistrict(Long districtId);
	
	public List findSeatsWonByAPartyInMandalForAnElectionYear(String tehsilId,String electionYear,String electionType,Long rank);
	
	public List getPartysInfoForATehsilForAParticularElectionYear(String electionType,String tehsilIds,String electionYear);
	
	public List getTehsilLevelElectionDetailsForAGivenConstituency(String query,Object[] parms);
	
	public List<Nomination> getNominationOfACandidateInAnElection(String electionType,String electionYear,Long constituencyId,Long candidateId);
	
	public List<Nomination> getNominationOfACandidateInAnElection(Long electionId,Long constituencyId,Long candidateId);
	
	public List<Nomination> findByElectionIdAndPartyIdStateId(Long electionId,Long partyId,Long stateId);
	
	public List<Nomination> findNominationsByConstituencyElectionPartyAndCandidate(Long constiElecId, Long partyId, Long candidateId);
	
	public List<Nomination> findByElectionIdAndPartyIdStateIdForWon(Long electionId,Long partyId,Long rank);
	
	public List<Nomination> findByElectionIdAndPartyIdStateIdForWon(Long electionId, Long partyId, Long rank,Long stateId);
	
	public List<Nomination> findByElectionIdAndPartyIdDistrictIdForWon(Long electionId, Long partyId, Long rank,Long districtId);
	
	public List<Nomination> findByElectionIdAndPartyIdStateIdForLost(Long electionId,Long partyId,Long rank);
	
	public List<Nomination> findByElectionIdAndPartyIdStateIdForLost(Long electionId, Long partyId, Long rank,Long stateId);
	
	public List<Nomination> findByElectionIdAndPartyIdDistrictIdForLost(Long electionId, Long partyId, Long rank,Long districtId);
	
	public List<Nomination> findByElectionIdAndRank(Long electionId,Long rank,List<Long> constituencyIds);
	
	@SuppressWarnings("unchecked")
	public List findElectionResultsByElectionIdAndPartyIdAndRank(Long electionId,Long partyId,Long rank);
	
	@SuppressWarnings("unchecked")
	public List findElectionResultsByElectionIdAndPartyIdAndLostRank(Long electionId,Long partyId,Long rank);
	
	public List getMuncipalityCandidateDetails(String electionType,Long districtId);
	
	public List findSeatsWonByAPartyInMuncipalityForAnElectionYear(String muncipalityIds,String electionYear,String electionType,Long rank);
	
	public List getPartysInfoForAMuncipalityForAnElectionYear(String electionType,String muncipalityIds,String electionYear);
	
	public List getAllPartiesForAMuncipality(String electionType,Long muncipalityId,String electionYear);
	
	public List getCandidatesInfoForTheGivenMuncipalityOrCorporationConstituency(String constituencyId,String electionYear,String electionType);
	
	public List getAllElectionYearsForAConstituency(Long constituencyId,String electionType);
	
	public List<Nomination> getNominationsForANominationIdsSet(List<Long> nominationIds);

	public List findPartiesInfoByElectionAndPartyGroupByDistrict(Long electionId, String partyIds);

	public List findPartiesInfoByElectionAndPartyGroupByState(Long electionId, String partyIds);
	
	public List findPartyWonConstituenciesInfoByElectionAndPartyGroupByState(Long electionId, String partyIds, Long rank);
	
	public List findPartyWonConstituenciesInfoByElectionAndPartyGroupByDistrict(Long electionId, String partyIds, Long rank);

	public List<ConstituencyElection> findConstituencyElectionsByElectionPartyAndPosition(Long electionId, Long partyId, Long rank);

	public List<ConstituencyElection> findConstituencyElectionByElectionIdAndStateAndPartys(Long electionId, Long stateId, List<Long> partyIds);

	public List findPCElectionResultsForACandidateForAnElectionInAConstituency(
			Long constituencyId, Long electionId, Long partyId);

	public List findPCElectionResultsForAnElectionInAConstituencyWithoutSelectedParty(
			Long constituencyId, Long electionId, Long partyId);
	public List findElectionResultsForAllPartiesInAssemblyConstituencies(String electionYear, List<Long> constituencyIds); 
	public List findWinningCandidatesDetailsInContituencies(String electionYear, List<Long> constituencyIds);
	public List findOppositionCandidateVotesPercentageInConstituencies(String electionYear, List<Long> constituencyIds);
	
	public List getCandidateRankInAConstituencyElection(Long constituencyId,String electionYear,String electionType,Long partyId);
	
	public List getPartyVotesShareInAConstituency(Long constituencyId, String electionIds, String partyIds);
	
	public List getLocalElectionsCandidateDetailsInAConstituency(String electionType,String tehsilIds);
	
	public List getMptcZptcPartiesResultsInMandals(String mandalIds);
	
	public List getMunicipalitiesAndCorporationsResultsInMandals(String mandalIds);
	
	@SuppressWarnings("unchecked")
	public List getWonAndOppCandidateInAnElection(Long constiId,String elecYear);
	
	@SuppressWarnings("unchecked")
	public List getResultsForElectionInConstituency(Long constituencyId,String electionYear);
	
	public List<Election> getElectionsInState(Long stateId);
	
	public List<Election> findByElectionScopeIdAndPartyId(Long electionScopeId,String electionSubtypes,Long partyId);
	
	public List<ConstituencyElection> getAllAllianceConstituenciesForAPartyInAStateForAnElection(Long electionId,String alliancePartyIds,Long partyId,Long stateId);
	
	public List<ConstituencyElection> getAllAllianceConstituenciesForAPartyInADistrict(Long electionId,String alliancePartyIds,Long partyId,Long districtId);
	
	public List getConstituenciesCountByDistrictForElectionStateAndParties(Long electionId, Long stateId, String alliancePartyIds);
	
	public List getConstituenciesCountByStateForElectionCountryAndParties(Long electionId, Long countryId, String alliancePartyIds);

}