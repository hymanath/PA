package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Party;

public interface ICandidateBoothResultDAO extends GenericDao<CandidateBoothResult, Long>{

	public List<CandidateBoothResult> findByProperty(Object value);
	
	public List<CandidateBoothResult> findByBoothElectionScopeAndParty(Long boothId, String electionYear, Long electionScopeId, Long partyId);
	
	public List<CandidateBoothResult> findByConstituencyElection(Long constituencyElectionId);
	
	public List<Party> findPartiesByConstituencyAndElectionYear(Long constituencyId, String electionYear);
	
	public List findPartyElectionResultForMandal(Long tehsilID,String electionTypeIDs, String electionYears);
	
	public List findPartyElectionResultForRevenueVillage(Long revenueVillageID,String electionTypeIDs, String electionYears);
	
	public List findBoothWisePartiesAllElectionResultsByBooth(Long boothId);
	
	public List getPartyGenderWiseBoothVotesForMandal(Long tehsilID);
	
	public List getElectionPartyResultsForTownship(Long electionID, Long townshipID);

	public List findMandalWisePartiesResultsForElection(Long tehsilId, Long electionId);
	
	@SuppressWarnings("unchecked")
	public List findMandalWisePartiesResultForMaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long trendValue);
	
	@SuppressWarnings("unchecked")
	public List findMandalWisePartiesResultForFemaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long trendValue);
	
	@SuppressWarnings("unchecked")
	public List findMandalWisePartiesResultForMaleAndFemaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long maleTrend,Long femaleTrend);
	
	@SuppressWarnings("unchecked")
	public List findMandalWisePartiesResultForAllBoothsInAnElection(Long electionId,Long tehsilId);
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseVotingTrendz(Long electionId,Long constituencyId);
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseMaleVotingTrendz(Long electionId,Long constituencyId,Long trendzValue);
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseFemaleVotingTrendz(Long electionId,Long constituencyId,Long trendzValue);
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseMaleAndFemaleVotingTrendz(Long electionId,Long constituencyId,Long maleTrendz,Long femaleTrendz);
	
	public List getAllPartiesResultsInAllElectionsByRevenueVillgesInMandal(String condition, Long tehsilId);

	public List findTownshipElectionResult(Long townshipId, Long electionId);
	
	public List findPartyResultsForBooths(String boothConstituencyElectionIds);
	
	public List findAllElectionsInfoInRevenueVillage(Long townshipId);
	
	public List findAllElectionsInfoOfRevenueVillagesInTehsil(Long tehsilId, Long electionId);
	
	public List findBoothResultsForTownshipAndElection(Long townshipId, Long electionId);

	public List getAllACPCPartiesInMandal(Long tehsilId);

	public List getAllACPCPartiesInRevenueVillage(Long townshipId);
	
	public List getCandidatesResultsForElectionAndConstituencyByMandal(Long constituencyId, String electionYear, String electionType);
	
	public List findAssemblyRegionResultsForPartiesForAConstituency(Long acId, Long pcId, String electionYear);
		
	public List findAllPartiesElectionResultsInDistrictForElectionType(Long districtId, String electionType);

	public List getcandidatesResultsByBoothConstiIds(String boothConstiElecIds);
	
	public List getResultsForElectionForAllMandalsAndParties(String mandalIds, String electionYear,String electionType);
	
	public List getResultsForElectionAndConstituencyByMandal(String mandalIds, String electionYear,String electionType);
	
	@SuppressWarnings("unchecked")
	public List getElectionResultsInAMandalForAllParties(Long mandalId,String electionType,String electionYear);
	
	@SuppressWarnings("unchecked")
	public List getAllPartiesPariticipatedInAConstituencyElection(Long constituencyId);
	
	@SuppressWarnings("unchecked")
	public List getBoothWisePartyResultsInAMandal(Long tehsilId,Long partyId,String electionYear);
	
	@SuppressWarnings("unchecked")
	public List getBoothWisePartyResultsInAMandalByPartyRank(Long tehsilId,Long constituencyId,String electionYear,String electionType,Long rank);
	
	@SuppressWarnings("unchecked")
	public List getPartyResultsInAMandalForAnElection(Long tehsilId,Long constituencyId,Long partyId,String electionYear);
	
	@SuppressWarnings("unchecked")
	public List getPartyResultsInAMandalForAnElection(Long tehsilId,Long constituencyId,String electionYear,Long rank);
	
	@SuppressWarnings("unchecked")
	public List getBoothWisePartyResultsInAMandalByConstituencyId(Long tehsilId, Long partyId, Long constituencyId, String electionYear, String electionType);
	
	public List findPartyResultsInAllElectionsByRevenueVillagesInMandal(Long tehsilId, Long partyId);
	
	@SuppressWarnings("unchecked")
	public List findTownshipWiseAllPartyResultsInAMandal(String electionType,String electionYear,Long mandalId);
	
	@SuppressWarnings("unchecked")
	public List getValidVotesInAMandal(Long tehsilId,String electionType,String electionYear);
	
	public List findAssemblyWiseParliamentResultsForPartiesInAssembly(Long acId, String electionYear);

	public List findElectionResultsForAMappedConstituencyByElectionType(Long constituencyId, String electionYear);
	
	public List findElectionResultsForAConstituencyByElectionYear(Long constituencyId, String electionYear);
	
	public List getAllPartiesResultsByMandalsMappedConstituency(Long constituencyId, String elecYear, String elecType);
	
	public List getBoothwiseCandidateResultsForGivenPartNosInAnElectionYear(List partNos, String elecYear, Long constiId);

	public List getPartNosOfAnElectionForAConstituency(Long constituencyId,	String electionYear);
	
	public List getCandidatesResultsForElectionAndConstituencyByLocalElectionBody(Long constituencyId, String electionYear, String electionType, 
			String localBodyTypes);
	
	public List getCandidatesResultsForElectionAndConstituencyByLocalElectionBodyWard(Long constituencyId, String electionYear, String electionType,
			String localBodyType);
	
	public List getBoothwisePartyResultsOfNominationInMandalWithInConstituency(Long nominationId, 
			Long assemblyId, Long tehsilId);
	
	public List getBoothwisePartyResultsOfNominationInLocalBodyWithInConstituency(Long nominationId, 
			Long assemblyId, Long localBodyId);
	
	public List getBoothwisePartyResultsOfNominationInLocalBodyWardWithInConstituency(Long nominationId, 
			Long assemblyId, Long localBodyWardId);
	
	public List getBoothwisePartyResultsOfNominationInUnMappedBoothsWithInConstituency(Long nominationId, 
			Long assemblyId, String electionYear);
	public List getCandidatesResultsForElectionAndConstituencyByMandalWise(Long constituencyId, String electionYear, String electionType);
	
	public List<Object[]> findBoothResultsForBoothsAndElection(List<Long> boothslist, Long electionId);
	
	public List<Object[]> findPachayatWisePartiesResultsForElectionInATehsil(Long tehsilId, Long electionId);
	
	public List<Object[]> findBoothResultsForBoothsAndElectionWithParty(List<Long> boothslist, Long electionId);
}
