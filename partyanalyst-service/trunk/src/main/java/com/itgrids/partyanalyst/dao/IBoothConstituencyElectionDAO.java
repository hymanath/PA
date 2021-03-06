package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothConstituencyElection;
import com.itgrids.partyanalyst.model.Constituency;

public interface IBoothConstituencyElectionDAO extends GenericDao<BoothConstituencyElection, Long>{

	public List<Booth> findBoothsByConstituencyElection(Long constiElecId);
	
	public List<BoothConstituencyElection> findByConstituencyElectionTehsilAndPartNo(Long constituencyElectionId, Long tehsilId, String partNo);

	public List<BoothConstituencyElection> findByBoothAndConstiuencyElection(String partNo, Long constiElecId);
	
	public List getAllElectionBoothVotersForMandal(Long tehsilID);
	
	public List getPartyVotesByMandal(Long tehsilID, String partyIDs, Long electionID);
	
	public List<Constituency> findConstituencyByElectionYearAndElectionScope(String electionYear, Long electionScopeId);
	
	public List<String> findElectionYearsForBoothData();
	
	public List getStatesByCountryFromBooth(Long countryID);
	public List getDistrictsByStateIDFromBooth(Long stateID);
	public List getConstituenciesByDistrictIDFromBooth(Long districtID);
	public List getMandalsByConstituencyIDFromBooth(Long constituencyID);
	
	public List findByConstituencyIdAndElectionYear(Long constituencyId, Long parliamentId, String electionYear);
	
	public List<BoothConstituencyElection> findByElectionElectionTypeConstituencyAndPartNo(
			Long stateId, Long districtId, String constituencyName,
			Long electionTypeId, String electionYear, String partNo);

	public List findPartNoConstituencyNameForTehsil(Long tehsilId,
			String assemblyElectionType, String electionYear);
	
	public List<BoothConstituencyElection> findByConstituencyDistrictAndPartNo(String constituencyName, Long districtId, String partNo, String year);
	
	public List<BoothConstituencyElection> findByBoothIds(String boothIds);
	
	@SuppressWarnings("unchecked")
	public List findMandalWiseMaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long trendValue);
	
	@SuppressWarnings("unchecked")
	public List findMandalWiseFemaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long trendValue);
	
	@SuppressWarnings("unchecked")
	public List findMandalWiseMaleAndFemaleBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId,Long maleTrend,Long femaleTrend);
	
	@SuppressWarnings("unchecked")
	public List findMandalWiseAllBoothsVotingTrendsInAnElection(Long electionId,Long tehsilId);
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseAllBoothsVotingTrendsInAnElection(Long electionId,Long constituencyId);
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseMaleBoothsVotingTrendsInAnElection(Long electionId,Long constituencyId,Long trendValue);
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseFemaleBoothsVotingTrendsInAnElection(Long electionId,Long constituencyId,Long trendValue);
	
	@SuppressWarnings("unchecked")
	public List findConstituencyWiseMaleAndFemaleBoothsVotingTrendsInAnElection(Long electionId,Long constituencyId,Long maleTrend,Long femaleTrend);	
	
	@SuppressWarnings("unchecked")
	public List findMandalDetailsForAParticularElectionYearForAParticularConstituency(Long constituencyId,Long electionId);
	
	@SuppressWarnings("unchecked")
	public List findAllElectionsForAConstituency(Long constituencyId);
	 
	@SuppressWarnings("unchecked")
	public List findBoothwiseResultsConstituency(Long constituencyId);
    
	public List getBoothsInRevenueVillageForElection(Long townshipId, Long electionId);
	
	@SuppressWarnings("unchecked")
	public List getPreviousElectionYearsDetails(String presentYear,Long constituencyId);
	
	@SuppressWarnings("unchecked")
	public List getElectionIdForAElectionTypeAndYear(Long elecTypeId,String elecYear);
	
	public List findElectionsHappendInConstituency(Long constituencyId);

	public List findTotalBoothWiseValidVotesForConstituencyElection(
			Long constituencyId, String electionYear);
	
	public List<BoothConstituencyElection> findByBoothIdAndConstiuencyElection(Long boothId, Long constiElecId);
	
	public List getTotalVotesInAMandal(Long tehsilId,String electionIds);
	
	@SuppressWarnings("unchecked")
	public List getValidVotesInAnElectionInMandal(Long mandalId,String electionType,String elecYear);
	
	@SuppressWarnings("unchecked")
	public List getTotalVotersInAnElectionInMandal(Long mandalId,String electionType,String elecYear);
	
	public List<BoothConstituencyElection> findByConstituencyIdPartNosAndYear(Long constituencyId, Long year, String partNos);
	
	public List getElectionYears();
	
	public Object getElectionIdInMandal(Long tehsilId, String electionType, String year);
	
	public List<Object[]> getBoothResultsContainStates();
	
	public List<Object[]> getElectionYears(Long stateId,Long electionType);
	
	public List<Object[]> getConstituencies(Long electionId);
	
	public List<Object[]> getVotersCountInAConstituency(Long electionId,Long constituencyId);

	public List<Object[]> getVotersCountInAMandalBooth(Long electionId,Long id,String type,String partNo,Long constituencyId,List<Long> constituencyIds);
	
	public List<Long> getBoothIdsByLocalEleBodyId(Long localEleBodyId, Long electionId,Long constituencyId);
	
	public List<Long> getBoothIdsByConstituencyId(Long constituencyId, Long electionId);
	
	public List getElectionYearsByMandalId(String type, Long id);
	 
	public List<Object[]> getConstituencyIdAndElectionYearByElectionType(String type,Long id, String year, String electionType);
	
	public List<Object[]> findAllElectionsHappenedInABooth(Long boothId);
	
	public List<Object[]> findAllElectionsHappenedInABooth1(Long boothId , List<Long> constElectionIds);
	 
	public List<Long> getBoothIdsByConstituencyIdPartNo(Long constituencyId, Long electionId,String partNo);
	
	public List<Long> getBoothIdsByElectionIdWardIds(Long electionId,List<Long> wardIds);
	
	public List<Long> getBoothIdsByWardId(Long wardId, Long electionId,Long constituencyId);
	
	public List<Object[]> getVotersCountInAConstituencyByParliamentConsId(Long electionId,Long assemblyConstituencyId, Long parliamentConstituencyId);
	
	public List<Object[]> getBoothIdsByWardIds(List<Long> wardIds, List<Long> electionIds,Long constituencyId);
	
	public List<Object[]> getBoothResultsBasedOnTotVotesIsNullByElectionId(Long electionId);
	
	public List<Object[]> getBoothResultsBasedOnValidVotesIsNullByElectionId(Long electionId);
	
	public List<Object[]> getBoothResultsBasedOnTotVotesGreaterValidVotesByElectionId(Long electionId);
	
	public List<Object[]> getBoothResultsBasedOnMaleAndFemaleVotesByElectionId(Long electionId);
	
	public List<Object[]> getEleYears();
	
	public List<Object[]> getConstituenciesByEleId(Long electionId);
	
	public List<Long> getBoothIdsByElectionId(Long electionId);
	
	public List<String> getAllElectionYearsByConstiIds(List<Long> constiIds);
	
	public List<Object[]> getParliamentConstisByElectionYear(List<Long> constiIds,String year);
	
	public List<Long> getAllAssemblyConstisByYearsAndConstiIds(String electionYear,List<Long> constiIds);
	
	public List<Object[]> getAllConstisByYearsAndConstiIds(String electionYear,List<Long> constiIds);
	
	public List<Object[]> getBoothsByConstituency(Long constituencyId,Long electionId);
	
	public List<Long> getElectionsByMandals(List<Long> mandalIds,Long partyId,Long electionTypeId);
	
	public List<Object[]> getTotalValidVotesAndTotalVotersByBoothIdsList(List<Long> boothIdsList,Long electionId,Long constituencyId);
	
	public List<Object[]> getTotalValidVotesAndTotalVotersByConstituency(Long electionId,Long constituencyId);
	public List getPartyVotesByMandalWithRankDetails(Long tehsilID, String partyIDs, Long electionID);
	
	public List<Long> getElectionsByUrbanConsti(Long constiId,Long electionTypeId);
	
	public List<Object[]> getBoothIdsByConstituencyIdAndEleId(Long electionId,Long constituencyId);
	
	public List<Object[]> getElectionYearsByConstituencyIdsList(Long electionTypeId,Long stateId,List<Long> constituencyIdsList);
	
	public List<Object[]> getConstituencyListByElectionId(Long electionId,List<Long> constituencyIdsList);
	
	public List<Long> getBoothIdsByLocalEleBody(Long localEleBodyId, Long electionId);

	public Long getTotalVotesByLocationId(Long locationId,String locationType,Long electionId,Long constituencyId,List<Long> constituencyIdsList);
	
	public Long getTotalVotersByBoothIdsList(List<Long> boothIdsList,Long electionId);
	
	public Long getValidVotesforBoothByElectionId(Long boothId,Long electionId);
	
	public List<Object[]> getDistrictLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO);
	public List<Object[]> getAssemblyLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO);
	public List<Object[]> getMandalLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO);
	public List<Object[]> getMunciORUrbanLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO);
	public List<Object[]> getPanchayatLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO);
	public List<Object[]> getPanchayatBoothLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO);
	public List<Object[]> getLocalBodyBoothLevelElectionResultsForGISVisualization(GISVisualizationParameterVO inputVO);
	
	public List<Object[]> getTotalVotersByLocationId(List<Long> locationIdsList,String locationType,Long electionId,Long constituencyId,List<Long> constituencyIdsList,Long publicationDateId);
	public List<Object[]> getLocationWiseAssemblyElectionPolledVotes(List<Long> electionYrs,List<Long> parliamentIds,List<Long> assemlyIds ,Long locationTypeId,List<Long> locationValues,List<String> subtypes,Long scopeId);
	public List<Object[]> getLocationwiseAssemblyEarnedVotes(List<Long> electionYrs,List<Long> parliamentIds,List<Long> assemlyIds ,List<Long> partyids,Long levelId,List<Long> locationVals,List<String> subtypes,Long scopeId);
	public List<Object[]> getLocationWisePolledVotesForVotingDetails(List<Long> electionYrs,Long locationTypeId,List<Long> locationValues,List<String> subtypes,String searchLevel,String clickType,List<Long> partyIds);
	public List<Object[]> getLocationWiseErnedVotesForVotingDetails(List<Long> electionYrs,Long locationTypeId,List<Long> locationValues,List<String> subtypes,String searchLevel,Long electionScopeId,List<Long> partyIds);
}
