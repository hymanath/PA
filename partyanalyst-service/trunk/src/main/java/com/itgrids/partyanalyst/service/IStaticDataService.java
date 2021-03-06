
package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.dto.AlliancePartiesInElection;
import com.itgrids.partyanalyst.dto.AlliancePartyResultsVO;
import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyLiveResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyResultsInElectionVO;
import com.itgrids.partyanalyst.dto.DelimitationUploadValidationVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.dto.ElectionBasicInfoVO;
import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
import com.itgrids.partyanalyst.dto.ElectionTrendzReportVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.PartyWiseMarginCountsVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.RevenueVillageElectionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.dto.TownshipBoothDetailsVO;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.model.PartyElectionStateResult;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.utils.GenericException;

public interface IStaticDataService {

	//>>>>>Election Related Methods Start
		public List<SelectOptionVO> getAllElectionYearsBasedOnElectionType(String electionType);
	
		public List<SelectOptionVO> getElectionScopesByElectionType(Long electionTypeId);
		
		public List<SelectOptionVO> getElectionIdsAndYearsByElectionScope(Long electionScopeId,Long partyId);
			
		public List<SelectOptionVO> getAllElectionScopes();
		
		public List getAllElectionsInDistrict(Long districtId);
		
		public List getAllAssemblyElectionsInDistrict(Long districtId,String type);
	
		public List<SelectOptionVO> getElectionIdsAndYearsForConstituency(Long constituencyId);
	
		public List<SelectOptionVO> getAllElectionTypes();
		
		public List<ElectionScope> getElectionScope(Long electionType);
		
		public List<SelectOptionVO> getElectionIdsAndYears(Long electionTypeId);
		
		public List<SelectOptionVO> getElectionIdsAndYearsInfo(Long elecType,Long stateId);
		
		public List<String> getElectionYears(Long electionType, Boolean incByeElection);
	//>>>>>Election Related Methods End
	
	//>>>>>State Related Methods Start
		public List<SelectOptionVO> getStates(Long electionType);
		
		public List<State> getAllStates();
		
		public CandidateDetailsVO getAllStatesInCountry();
	//>>>>>State Related Methods End
	
	//>>>>>District Related Methods Start
		public DistrictWisePartyResultVO getDistrictWiseElectionReport(Long electionScopeId, Long districtId);
		
		public List<SelectOptionVO> getDistricts(Long stateId);
		
		public CandidateDetailsVO getElectionResultsForADistrictForAllYears(Long districtId);
		
		public List<SelectOptionVO> getLatestAssemblyConstituenciesInDistrict(Long districtId);
	//>>>>>District Related Methods End
	
	//>>>>>Constituency Related Methods Start
		public TeshilPartyInfoVO getLocalElectionDetailsForAConstituency(Long constituencyId,String electionType);
		
		public ElectionTrendzReportVO getConstituencyOverview(Long constituencyId,String constituencyName);
		
		public ConstituencyInfoVO getLatestAssemblyConstituenciesForParliament(Long parliamentConstituencyId);
		  
		public List<ElectionBasicInfoVO> getAssemblyElectionsInfoForAConstituency(String presentYear,Long constituencyId);
		
		public List<ElectionBasicInfoVO> getParliamentElectionsInfoForAConstituency(Long constituencyId);
		
		public List<SelectOptionVO> getConstituencies(Long stateId,Long stateTypeId);
		
		public ConstituenciesStatusVO getConstituenciesForDistrict(Long districtId,Long electionYear, String electionType);
		
		public CandidateDetailsVO getCompleteElectionResultsForAConstituency(Long constituencyId,Long electionId,Long partyId);
		
		public CandidateDetailsVO getLatestConstituenciesForAssemblyAndParliamentForAllElectionYears(Long electionType,Long stateId);
	
		public ConstituencyInfoVO getConstituenciesByElectionTypeAndStateId(Long electionTypeId , Long stateID);	
	//>>>>>Constituency Related Methods End
	
	//>>>>>Party Related Methods Start
		public List<PartyResultVO> getPartyVotesPercentageInAConstituency(Long constituencyId,String all,String[] choices);
		
		public List<PartyResultVO> getPartyVotesShareInConstituency(Long constituencyId,int flag,List<ElectionResultPartyVO> partyResult);
		
		public DistrictWisePartyResultVO getAllPartiesPositionsInDistrictElection(Long electionId, Long districtId);
		
		public MandalAllElectionDetailsVO getAllPartiesForAParticularElection(Long districtId,String electionType,String electionYear);
		
		public Set<SelectOptionVO> getAllPartiesParticipatedInMandal(Long tehsilId);
		
		public Set<SelectOptionVO> getAllPartiesParticipatedInRevenueVillage(Long townshipId);
		
		public List<SelectOptionVO> getStaticPartiesForCandidateDeatailsReport(Long stateId);
		
		public ElectionResultPartyVO getElectionResultForAPartyInAnElection(Long electionId,Long partyId,Long rank,Long stateId);
		
		public TeshilPartyInfoVO getAllPartyTrendsForAllMuncipalitiesInADistrict(String electionType,Long districtId);
		
		public List<SelectOptionVO> getAllPartiesForAnElectionYear(String electionYear,String electionType);
		
		public List<SelectOptionVO> getPartiesForConstituency(Long constituencyId, String electionYear);
		
		public PartyElectionResult getPartyElectionResultsForAParty(Long electionId,Long partyId);
		
		public PartyElectionStateResult getPartyElectionResultsForAPartyStateLevelInParliamentElection(Long electionId,Long partyId,Long stateId);
		
		public PartyElectionStateResult savePartyElectionResultForAPartyForAParliamentElectionStateLevel(Long electionId,Long partyId,Long stateId);
		
		public PartyElectionResult savePartyElectionResultForAPartyForAElection(Long electionId,Long partyId);
			
		public PartyElectionDistrictResult savePartyElectionResultForAPartyForAElectionDistrictLevel(Long electionId,Long partyId, Long districtId);
		
		public List<DistrictWisePartyResultVO> getDistrictWisePartyElectionResults(String electionType,	Long electionId, String partyIds,Boolean hasAlliance);
		
		public PartyElectionDistrictResult getPartyElectionResultsForAPartyDistrictLevel(Long electionId, Long partyId, Long districtId);
	
		//public List<SelectOptionVO> getParties();
		public List<SelectOptionVO> getStaticParties();
		
		public List<SelectOptionVO> getAllNationalParties();
		
		public List<SelectOptionVO> getStaticNationalParties();
		
		public List<SelectOptionVO> getStaticStateParties(Long stateId);
		
		public List<SelectOptionVO> getStaticPartiesListForAState(Long stateId);
		
		public List<SelectOptionVO> getStaticPartiesListFromElectionScope(Long scopeId);
		
		public List<SelectOptionVO> getAllElectionScopes(Long districtId);
		
		public List<SelectOptionVO> getAllElectionScopesForAState(Long stateID);
		
		public List<TeshilPartyInfoVO> getMandalWisePartyReport(String electionType,String electionYear,Long districtId);
	//>>>>>Party Related Methods End

	//>>>>>ConstituencyElection Related Methods Start
		public List<ConstituencyElectionResultsVO> findAssemblyConstituenciesResultsByConstituencyIds(String electionYear, List<Long> constituencyIds, List constituenciesResults); 

		public MandalAllElectionDetailsVO getAllMuncipalElectionDetails(Long muncipalityId,String candidateDetailsType,Long partyId,String electionType);
		
		public CandidateDetailsVO getMuncipalAndCorporationCandidateDetails(Long stateId,String electionType,String electionYear,Long rank,Long partyId,Long districtId,String resultsCategory);
		
		public MandalAllElectionDetailsVO getAllZptcsMptcsForADistrictForAPartyForSelectedYear(Long districtId,
				String electionYear,Long partyId,int flag,int lostFlag, String electionType);
		
		public CandidateDetailsVO getElectionResultsForAConstituencyForAllYears(Long constituencyId);
		
		public List<ConstituencyElection> getConstituencyElections(Long electionID,Long stateId,Long  districtID);
		
		public List<ConstituencyElection> getConstituencyElectionsFromNomination(Long electionID,Long stateId,Long districtID,Long rank,Long partyId,String reportLevel);
		
		public List<ConstituencyElection> getConstituencyElectionsFromNominationWithAlliances(Long electionID,Long stateId,Long districtID,Long rank,List<SelectOptionVO> parties);
		
		public List<ConstituencyElection> getConstituencyElectionsFromNominationForCountry(Long electionID,Long stateId,Long countryId,Long rank,Long partyId);
	//>>>>>ConstituencyElection Related Methods End
	
	//>>>>>Tehsil Related Methods Start
		public StringBuilder getAllLatestMandalsForAConstituency(Long constituencyId);
		
		public List<ElectionResultPartyVO> getAllMandalElectionInformationForAConstituency(Long constituencyId,int flag);
		
		public MandalVO findListOfElectionsAndPartiesInMandal(Long tehsilId);	
		
		public List<SelectOptionVO> getAllElectionYearsForATeshil(Long electionType);
		
		public List<MandalVO> getMandalsForDistrict(Long districtId);
	//>>>>>Tehsil Related Methods End
		
	//>>>>> Alliance Parties Related Methods Start
		public List<CandidateElectionResultVO> getProcessedAlliancePartiesResults(List<CandidateElectionResultVO> elecResultVO,String elecType,String elecYear);
		
		public DistrictWisePartyResultVO getAllianceGroupsForElections(Long districtId);
		
		public List<SelectOptionVO> getAlliancePartiesAsVO(String electionYear, Long electionType, Long partyId, Long stateId);
		
		public AlliancePartyResultsVO getAlliancePartiesByElectionAndParty(Long electionId, Long partyId);
		
		public List<Party> getAllianceParties(String electionYear, Long electionType, Long partyId, Long stateId);
		
		public Long getGroupIdIfPartyHasAlliances(Long partyId, String electionYear, Long electionType,Long stateId);
		
		public boolean hasAlliances(String year, Long electionType, Long partyId);
		
		public List<AlliancePartiesInElection> getAlliancGroupAndPartiesInAnElection(String electionType,String electionYear);
	//>>>>> Alliance Parties Related Methods End	
	
	//>>>>> Township Related Methods Start
		public List<TownshipBoothDetailsVO> getRevenueVillageVotingTrendsByMandalAndElectionIds(Long tehsilId,String electionIds);
		
		public List<SelectOptionVO> findTownshipsByTehsilID(Long mandalID);
	//>>>>> Township Related Methods End
	
	//>>>>> Hamlet Related Methods Start
		public List<SelectOptionVO> getHamletsForTownship(Long townshipId);
	//>>>>> Hamlet Related Methods End

	//>>>>> Candidate Related Methods Start
		public PartyElectionResultsVO getWonAndOppositionCandidateDetailsInAConstituencyWithMargin(Long constituencyId,String electionYear);
		
		public List<CandidateElectionResultVO> getWinningCandidatesInConstituencies(String electionYear, List<Long> constituencyIds);
		
		
		
		public MandalAllElectionDetailsVO getAllZptcOrMptcWinnerForADistrictForLatestYear(Long districtId,String electionYear,String electionType);
				
		public ConstituencyElectionResultsVO getAllCandidatesDetailsForConstituency(Long constituencyId,String electionYear,String electionType);
		
		public ConstituencyResultsInElectionVO getAllCandidatesResultsInConstituency(final Long constituencyId,final String electionYear,final String electionType);
		
		public CandidateDetailsVO getAllParliamentWinningCandidatesForADistrict(Long districtId, Set<Long> parliamentConstituencies);
		
		public MandalAllElectionDetailsVO getAllZptcsOrMptcsCandidatesForADistrictForSelectedYear(Long districtId,String electionYear,int lostFlag,String electionType);

		public ConstituenciesStatusVO getConstituenciesWinnerInfo(Long districtId);
	//>>>>> Candidate Related Methods End
		
	//>>>>> Booth Related Methods Start
		public List<ConstituencyBoothInfoVO> getBoothPartNosForMandalAndElection(Long tehsilId, String electionYear);
	//>>>>> Booth Related Methods End

		@SuppressWarnings("unchecked")
		public List<MandalAllElectionDetailsVO> populateElectionsData(List winningCandidate,List successorCandidate,int flag,int reservationZone,String module);
		
		@SuppressWarnings("unchecked")
		public List<MandalAllElectionDetailsVO> populateElectionsDataForAllCandidates(List winningCandidate,List allCandidates,int reservationZone,String module);
	
		public NavigationVO findHirarchiForNavigation(Long locationId, String locationType);
		
		public List<SelectOptionVO> getAllSocialCategories();
		public List<SelectOptionVO> getAllEducationalQualifications();
		public List<SelectOptionVO> getAllOccupations();
		public List<SelectOptionVO> getAllLanguages();
		
		public List<SelectOptionVO> getParticipatedStatesForAnElectionType(Long electionType);
	
		public Long getElectionScopeForAElection(Long stateId,String electionType,Long countryId);
		
		public SelectOptionVO getStateOfADistrict(Long districtId);
		
		public List<SelectOptionVO> getLocalBodyElectionTypesInAState(Long stateId);
		
		public List<SelectOptionVO> getLocalBodysInAStateByType(Long stateId,Long typeId);
		
		public List<SelectOptionVO> getAllInformationSources();
		
		@SuppressWarnings("unchecked")
		public List getListOfElectionIdsForGivenElectionTypeIdAndListOfElectionYears(Long electinTypeId,Long electionYear1,Long electionYear2,Long stateId,String electionSubType);
		
		public List<SelectOptionVO> getLocationsHirarchyByType(String type,Long id);
		
		public List<TeshilPartyInfoVO> getLocalElectionPartyDetails(List result,String latestMuncipalElectionYear,String electionType) throws GenericException;
		
		public String removeSpecialCharectersFromString(String formatString);
		
		public DistrictWisePartyResultVO getElectionResultsForDistrict(Long electionScopeId, Long districtId);
		
		public Long getRecentAssemblyMainElectionIdInAState(Long stateId);
		
        public Long getStateIdForUserByAccessValue(Long userId);
		
		public Long getStateIdByUserAccessTypeAndValue(String userAccessType,String userAccessValue) throws Exception;
		
		public List<SelectOptionVO> getAllProblemStatus();
		
		public List<SelectOptionVO> getDefaultProblemStatus(String statusValues);
		
		public List<Long> getStaticPartiesAsList(Long stateId);

		public List<SelectOptionVO> getLatestConstituenciesByStateId(Long stateId);
		
		public List<SelectOptionVO> getElectionYearByPartyId(Long stateId,Long partyId,Long electionTypeId);
		
		public List<SelectOptionVO> electionYearsForstateAndElectionType(Long stateId, String electionType);
		
		public List<TownshipBoothDetailsVO> getPanchayatVotingTrendsByMandalAndElectionIds(Long tehsilId, String electionIds);
		
		public List<SelectOptionVO> getElectionYearsBasedOnStateIdAndElecTypeId(Long stateId,Long electionTypeId);
		
		public List<SelectOptionVO> getElectionYearsBasedOnElectionType(Long electionTypeId);
		
		public List<SelectOptionVO> getStatesAndCountryBasedOnElectionType(Long electionTypeId);
		
		public List<SelectOptionVO> getPartiesForAGivenElectionYear(Long electionId);
		
		public List<SelectOptionVO> getPartiesWithAtleatOneWinningSeatForAElection(Long electionId);
		
		public String getDistrictNameByDistrictId(Long districtId);
		
		public List<TownshipBoothDetailsVO> getPanchayatVotingTrendsByMandal(Long tehsilId, String electionIds,List<RevenueVillageElectionVO> panchayats);
		public List<SelectOptionVO> getPanchayathiesByTehsilId(Long mandalID);
		
		public List<SelectOptionVO> getHamletsForPanchayath(Long panchayathId);
		
		public List<SelectOptionVO> getPanchayatiesByConstituencyId(Long constituencyId);
		
		public List<SelectOptionVO> getBoothsByConstituencyId(Long constituencyId);
		
		public List<SelectOptionVO> getPanchayatiesByMandalId(Long mandalId);
		
		public List<SelectOptionVO> getBoothsByMandalId(Long mandalId, Long constituencyId);
		
		public List<SelectOptionVO> getBoothsInAMandalIdByPublication(Long mandalId, Long publicationId, Long constituencyId);

		public List<SelectOptionVO> getUserAccessStates(Long userId);
		
		public List<SelectOptionVO> getParticipatedStatesForMinisters(
				Long electionType);
		
		public ResultStatus checkForLocalElctionBody(Long constituencyId,List<Long> localElectionBodyIds);
		
		public List<SelectOptionVO> getElectionIdsAndYearsInTehsil(Long electionType,Long tehsilId);
		
		public String getLatestAssemblyElectionId();
		
		public List<SelectOptionVO> getPartyNamesInAState(Long stateId , Long electionTypeId);
		
		//public List<SelectOptionVO> getAllPartiesParticapatedInMainElectionsService(Long stateId);
		
		public List<SelectOptionVO> getDistrictsByPartyNomination(Long stateId,Long partyId,Long year);
		
		public String getTehsilNameByTehsilId(Long tehsilId);
		
		public MandalVO findListOfElectionsAndPartiesInPanchayat(Long panchayatId);
		
		public List<SelectOptionVO> getLatestConstituenciesByStateIdAndType(Long stateId,String locationType);
		
		public MandalVO getElectionYearsAndPartiesForConstituency(Long constituencyId);
		
		public Long getParliamentIdByAssembly(Long constituencyId);
		
		public List<SelectOptionVO> getSelectedLevelDetails(String type , Long level, long id,Long publicationId);
		
		public List<SelectOptionVO> getPanchayatsOrBoothsForSelectedLevel(String type , Long level ,List<Long> ids,Long publicationId,Long constituencyId);
		
		public List<SelectOptionVO> getConstituencyType(Long constituencyId,Long publicationId);
		
		//public List<SelectOptionVO> getAllWardsInUrbanConstituency(Long constituencyId);
		
		public MandalVO getElectionYearsAndPartiesForSelectedConstituency(Long constituencyId);
		
		public List<SelectOptionVO> getBoothsInMuncipality(Long id,Long publicationId);
		
		public List<DelimitationUploadValidationVO> getConstituencyMandalDetailsForAllDelimitations(Long constituencyId);
		public DelimitationUploadValidationVO checkDelimitationYearsAndMandalsForConstituency(Long constituencyId);
		public List<Object[]> getVotersCountInAPanchayatForAnElection(Long electionId, Long panchayatId);
	
		public List<SelectOptionVO> getPanchayatiesByMandalIdAndConstId(Long constituencyId,Long id,Long publicationDateId);
		public List<SelectOptionVO> getAllAreaTypes();
		
		public List<SelectOptionVO> getPanchayatiesByMandalIdsListAndConstituencyId(List<Long> mandalsList,Long constituencyId,Long publicationDateId);
		
		public SelectOptionVO getSelectOptionVOFromResultList(List<SelectOptionVO> list, Long id);
		
		public List<SelectOptionVO> getHamletsByPanchayatIdsList(List<Long> panchayatIdsList);
		
		public List<SelectOptionVO> getBoothsByPanchayatIdsListAndConstituencyIdInAPublication(List<Long> panchayatIdsList,Long constituencyId,Long publicationDateId);
		
		public List<SelectOptionVO> getElectionYearsForBooths(Long stateId,Long electionType);
		
		public List<SelectOptionVO> getPartiesForBooths(String electionYear,Long constituencyId);

		public List<SelectOptionVO> getStatesByConstituency(List<Long> constituencyId);
		
		public List<SelectOptionVO> getDistrictsByConstituency(List<Long> constituencyId,Long stateId);
		
		public List<SelectOptionVO> getConstituencyForSelDistrict(List<Long> constituencyId,Long districtId);
		public Long getdistrictForAConstituency(Long constituencyId);
		public List<SelectOptionVO> getElectionYearsByConstituencyIds(List<Long> constituencyIds);
		public List<SelectOptionVO> getConstitunciesByElectionIdAndConstituenciyIds(List<Long> assemblyIds  , Long electionId);
		public List<SelectOptionVO> getStaticPartiesListByStateAndElection(Long stateId,String electionType);
		
		public boolean removeCadreImage(Long cadreId,Long userId);
		
		public List<SelectOptionVO> getPartitesList(Long stateId,String electionType);
			
		public List<SelectOptionVO> getElectionIdsAndYearsByElectionScopeId(Long electionScopeId, Long partyId,Long constituencyId,String constiType);
		
		public List<SelectOptionVO> getElecIdsAndYearsInTehsilToGetForPanchResults(Long electionType,Long tehsilId);
		
		public MandalVO getElectionYearsAndPartiesForSelectedConstituencyInSuggestive(Long constituencyId);
		
		public String getPartyVotesPercentage(Long electionId, Long partyId);
		
		public List<SelectOptionVO> getConstituenciesFordistricts(Long districtId);
		
		public List<SelectOptionVO> getMuncipalAssemblyConstiList(Long userId,Long stateId);
		
		public List<SelectOptionVO> getAssemblyConstiElectionYears(Long userId,Long cosntId);
		
		public List<SelectOptionVO> getPartyDEtailsByElectionId(Long userId,List<Long> electionID,Long constituencyId);
		
		public ConstituencyLiveResultsVO getStateWideParliamentLiveResults();
		
		public ConstituencyLiveResultsVO getConsituencyWiseElectionResultsForLive();
		
		public PartyWiseMarginCountsVO getMarginAnalysisOnLiveResultsForAssemblies(Long electionId,Long type,List<Long> locationIds);
		
		public List<SelectOptionVO> getRegionsByStateId(Long stateId);
		
		public ConstituencyLiveResultsVO getPartyWiseWonLeadCountInLive();

		public List<SelectOptionVO> getAllParliaments();
		
		public List<SelectOptionVO> getLatestConstituenciesByStateIdAndTypeForRegion(Long stateId,String locationType,String region);
		public List<SelectOptionVO> getRegionsByStateIdForRegion(Long stateId,String region);
		public ConstituencyInfoVO getConstituenciesByElectionTypeAndStateId1(
				Long electionTypeId, String stateID);
}
