package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateDetailsVO;
import com.itgrids.partyanalyst.dto.ConstituenciesStatusVO;
import com.itgrids.partyanalyst.dto.DistrictWisePartyResultVO;
import com.itgrids.partyanalyst.dto.ConstituencyBoothInfoVO;
import com.itgrids.partyanalyst.dto.MandalVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionDistrictResult;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.model.State;

public interface IStaticDataService {

	public List<SelectOptionVO> getStates(Long electionType);
	public List<ElectionScope> getElectionScope(Long electionType);
	public List<SelectOptionVO> getElectionIdsAndYears(Long electionType);
	public List<SelectOptionVO> getElectionIdsAndYears(Long elecType,Long stateId);
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
	
	public PartyElectionDistrictResult getPartyElectionResultsForAPartyDistrictLevel(Long electionId,Long partyId,Long stateId,Long districtId);
	
	public PartyElectionResult savePartyElectionResultForAPartyForAElection(Long electionId,Long partyId);
	
	public PartyElectionDistrictResult savePartyElectionResultForAPartyForAElectionDistrictLevel(Long electionId,Long partyId,Long stateId,Long districtId);
	
	public List<DistrictWisePartyResultVO> getDistrictWisePartyElectionResults(String electionYear, Long electionType,Long electionId,Long partyId,Boolean hasAlliances);
	
	public CandidateDetailsVO getCompleteElectionResultsForAConstituency(Long constituencyId,Long electionId,Long partyId);
	
}