package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CandidateBoothResult;
import com.itgrids.partyanalyst.model.Party;

public interface ICandidateBoothResultDAO extends GenericDao<CandidateBoothResult, Long>{

	public List<CandidateBoothResult> findByProperty(Object value);
	
	public List<CandidateBoothResult> findByNominationAndBoothConstituencyElection(Long nominationId, Long boothConstituencyElectionId);
	
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
	

	public List findTownshipElectionResult(Long townshipId, Long electionId);
	
	public List findPartyResultsForBooths(String boothConstituencyElectionIds);
	
	public List findAllElectionsInfoInRevenueVillage(Long townshipId);
	
	public List findAllElectionsInfoOfRevenueVillagesInTehsil(Long tehsilId, Long electionId);
	
	public List findBoothResultsForTownshipAndElection(Long townshipId, Long electionId);

	public List getAllACPCPartiesInMandal(Long tehsilId);

	public List getAllACPCPartiesInRevenueVillage(Long townshipId);
	
	public List getCandidatesResultsForElectionAndConstituencyByMandal(Long constituencyId, String electionYear);
	
	public List findAssemblyWiseParliamentResultsForParties(Long acId, Long pcId, String electionYear);
	
	public List getCandidatesResultsForElectionAndConstituencyByMandalByPaliamentWise(Long constituencyId,String mandalIds,String electionYear);
	
	public List getMandalsForAConstituencyForAGivenYear(Long constituencyId, String electionYear);
	
	public List findAllPartiesElectionResultsInDistrictForElectionType(Long districtId, String electionType);

	public List getcandidatesResultsByBoothConstiIds(String boothConstiElecIds);
	
	@SuppressWarnings("unchecked")
	public List getElectionResultsInAMandalForAllParties(Long mandalId,String electionType,String electionYear);
	
}
