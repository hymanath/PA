package com.itgrids.partyanalyst.service;

import java.util.List;
import java.util.Set;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultsVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.dto.ElectionBasicInfoVO;
import com.itgrids.partyanalyst.dto.ElectionResultPartyVO;
import com.itgrids.partyanalyst.dto.MandalAllElectionDetailsVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.NavigationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TeshilPartyInfoVO;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.model.PartyElectionStateResult;
import com.itgrids.partyanalyst.model.State;

public interface IStaticDataService {

	public List<SelectOptionVO> getStates(Long electionType);
	public List<ElectionScope> getElectionScope(Long electionType);
	public List<SelectOptionVO> getElectionIdsAndYears(Long electionType);
	public List<SelectOptionVO> getElectionIdsAndYearsInfo(Long elecType,Long stateId);
	public List<String> getElectionYears(Long electionType);
	//public List<SelectOptionVO> getParties();
	public List<SelectOptionVO> getDistricts(Long stateId);
	public List<State> getAllStates();
	public List<SelectOptionVO> getAlliancePartiesAsVO(String electionYear, Long electionType, Long partyId);
	public List<Party> getAllianceParties(String electionYear, Long electionType, Long partyId);
	public Long getGroupIdIfPartyHasAlliances(Long partyId, String electionYear, Long electionType);
	public boolean hasAlliances(String year, Long electionType, Long partyId);
	public List<SelectOptionVO> getConstituencies(Long stateId);
	public List<ConstituencyElection> getConstituencyElections(Long electionID,Long stateId,Long  districtID);
	public List<ConstituencyElection> getConstituencyElectionsFromNomination(Long electionID,Long stateId,Long districtID,Long rank,Long partyId);
	public List<ConstituencyElection> getConstituencyElectionsFromNominationWithAlliances(Long electionID,Long stateId,Long districtID,Long rank,List<SelectOptionVO> parties);
	public List<ConstituencyElection> getConstituencyElectionsFromNominationForCountry(Long electionID,Long stateId,Long countryId,Long rank,Long partyId);
	public List<SelectOptionVO> getStaticParties();
	public List<SelectOptionVO> getPartiesForConstituency(Long constituencyId, String electionYear);
	public List<SelectOptionVO> findTownshipsByTehsilID(Long mandalID);
	
	public List<SelectOptionVO> getHamletsForTownship(Long townshipId);
	
	public ConstituenciesStatusVO getConstituenciesForDistrict(Long districtId,Long electionYear, String electionType);
	
	public List<MandalVO>	getMandalsForDistrict(Long districtId);
	
	public ConstituenciesStatusVO getConstituenciesWinnerInfo(Long districtId);
	
	public List<ConstituencyBoothInfoVO> getBoothPartNosForMandalAndElection(Long tehsilId, String electionYear);
	
	public PartyElectionResult getPartyElectionResultsForAParty(Long electionId,Long partyId);
	
	public PartyElectionStateResult getPartyElectionResultsForAPartyStateLevelInParliamentElection(Long electionId,Long partyId,Long stateId);
	
	public PartyElectionStateResult savePartyElectionResultForAPartyForAParliamentElectionStateLevel(Long electionId,Long partyId,Long stateId);
	
	public PartyElectionResult savePartyElectionResultForAPartyForAElection(Long electionId,Long partyId);
	
	
	
	//Modified By Siva Start
	//public List<DistrictWisePartyResultVO> getDistrictWisePartyElectionResults(String electionYear, Long electionType,Long electionId,Long partyId,Boolean hasAlliances);
	//public PartyElectionDistrictResult getPartyElectionResultsForAPartyDistrictLevel(Long electionId,Long partyId,Long stateId,Long districtId);
	//public PartyElectionDistrictResult savePartyElectionResultForAPartyForAElectionDistrictLevel(Long electionId,Long partyId,Long stateId,Long districtId);
	
	public PartyElectionDistrictResult savePartyElectionResultForAPartyForAElectionDistrictLevel(Long electionId,Long partyId, Long districtId);
	
	public List<DistrictWisePartyResultVO> getDistrictWisePartyElectionResults(String electionType,	Long electionId, String partyIds);
	
	public PartyElectionDistrictResult getPartyElectionResultsForAPartyDistrictLevel(Long electionId, Long partyId, Long districtId);
	//Modified By Siva End	
	
	public CandidateDetailsVO getCompleteElectionResultsForAConstituency(Long constituencyId,Long electionId,Long partyId);
	
	public Set<SelectOptionVO> getAllPartiesParticipatedInMandal(Long tehsilId);
	
	public Set<SelectOptionVO> getAllPartiesParticipatedInRevenueVillage(Long townshipId);
	
	public CandidateDetailsVO getElectionResultsForAConstituencyForAllYears(Long constituencyId);
	
	public CandidateDetailsVO getLatestConstituenciesForAssemblyAndParliamentForAllElectionYears(Long electionType,Long stateId);
	
	public CandidateDetailsVO getAllStatesInCountry();
	
	public List<SelectOptionVO> getConstituenciesByElectionTypeAndStateId(Long electionTypeId , Long stateID);	
	
	public ConstituencyElectionResultsVO getAllCandidatesDetailsForConstituency(Long constituencyId,String electionYear,String electionType);
	
	public CandidateDetailsVO getAllParliamentWinningCandidatesForADistrict(Long districtId);
	
	public CandidateDetailsVO getElectionResultsForADistrictForAllYears(Long districtId);
	
	public List<SelectOptionVO> getAllElectionYearsForATeshil(Long electionType);
	
	public List<SelectOptionVO> getAllElectionTypes();
	
	public List<TeshilPartyInfoVO> getMandalWisePartyReport(String electionType,String electionYear,Long districtId);
	
	public List<ElectionBasicInfoVO> getAssemblyElectionsInfoForAConstituency(String presentYear,Long constituencyId);
	
	public List<ElectionBasicInfoVO> getParliamentElectionsInfoForAConstituency(Long constituencyId);
	
	public MandalAllElectionDetailsVO getAllZptcWinnerForADistrictForLatestYear(Long districtId,String electionYear);
	
	public MandalAllElectionDetailsVO getAllMptcWinnerForADistrictForLatestYear(Long districtId,String electionYear);
	
	public MandalAllElectionDetailsVO getAllMptcsForADistrictForAPartyForSelectedYear(Long districtId,String electionYear,Long partyId,int flag,int lostFlag);
	
	public MandalAllElectionDetailsVO getAllZptcsForADistrictForAPartyForSelectedYear(Long districtId,String electionYear,Long partyId,int flag,int lostFlag);
	
	public MandalAllElectionDetailsVO getAllMptcsCandidatesForADistrictForSelectedYear(Long districtId,String electionYear,int lostFlag);
	
	public MandalAllElectionDetailsVO getAllZptcsCandidatesForADistrictForSelectedYear(Long districtId,String electionYear,int lostFlag);
	
	public MandalAllElectionDetailsVO getAllPartiesForAParticularElection(Long districtId,String electionType,String electionYear);
	
	public List<SelectOptionVO> getElectionIdsAndYearsForConstituency(Long constituencyId);
	
	public List<SelectOptionVO> getAllPartiesForAnElectionYear(String electionYear,String electionType);
	
	public CandidateDetailsVO getCandidatesPartyInfoForAnElectionType(String electionType,String electionYear,String resultsCategory,String electionLevel,Long locationId,Long partyId,Long stateId);
	
	public DistrictWisePartyResultVO getDistrictWiseElectionReport(Long electionScopeId, Long districtId);
	
	public List<MandalAllElectionDetailsVO> populateElectionsData(List winningCandidate,List successorCandidate,int flag,int reservationZone,String module);
	
	public List<MandalAllElectionDetailsVO> populateElectionsDataForAllCandidates(List winningCandidate,List allCandidates,int reservationZone,String module);
	
	public List<SelectOptionVO> getAllElectionsInDistrict(Long districtId);
	
	public DistrictWisePartyResultVO getAllPartiesPositionsInDistrictElection(Long electionId, Long districtId);
	
	public DistrictWisePartyResultVO getAllianceGroupsForElections(Long districtId);
	
	public List<SelectOptionVO> getAllElectionScopes();
	
	public ElectionResultPartyVO getElectionResultForAPartyInAnElection(Long electionId,Long partyId,Long rank);
	
	public TeshilPartyInfoVO getAllPartyTrendsForAllMuncipalitiesInADistrict(String electionType,Long districtId);
	
	public MandalAllElectionDetailsVO getAllMuncipalElectionDetails(Long muncipalityId,String candidateDetailsType,Long partyId,String electionType);
	
	public CandidateDetailsVO getMuncipalAndCorporationCandidateDetails(Long stateId,String electionType,String electionYear,Long rank,Long partyId,Long districtId,String resultsCategory);
	
	public List<SelectOptionVO> getElectionScopesByElectionType(Long electionTypeId);
	
	public List<SelectOptionVO> getElectionIdsAndYearsByElectionScope(Long electionScopeId);
		
	public List<SelectOptionVO> getStaticPartiesForCandidateDeatailsReport();
	
	public ConstituencyInfoVO getLatestAssemblyConstituenciesForParliament(Long parliamentConstituencyId);
	  
	public NavigationVO findHirarchiForNavigation(Long locationId, String locationType);
	
	public List<ConstituencyElectionResultsVO> findAssemblyConstituenciesResultsByConstituencyIds(String electionYear, List<Long> constituencyIds); 

	public List<ElectionResultPartyVO> getAllMandalElectionInformationForAConstituency(Long constituencyId);
	
	public List<CandidateElectionResultVO> getWinningCandidatesInConstituencies(String electionYear, List<Long> constituencyIds);

}
