package com.itgrids.partyanalyst.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.appfuse.service.GenericManager;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.AllianceGroup;
import com.itgrids.partyanalyst.model.ConstituencyElection;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.ElectionAlliance;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;

public interface IStaticDataService {

	public List<SelectOptionVO> getStates(Long electionType);
	public List<ElectionScope> getElectionScope(Long electionType);
	public List<SelectOptionVO> getElectionIdsAndYears(Long electionType);
	public Set<String> getElectionYears(Long electionType);
	public List<SelectOptionVO> getParties();
	public List<SelectOptionVO> getDistricts(Long stateId);
	public List<State> getAllStates();
	public List<SelectOptionVO> getAlliancePartiesAsVO(String electionYear, Long electionType, Long partyId);
	public List<Party> getAllianceParties(String electionYear, Long electionType, Long partyId);
	public Long getGroupIdIfPartyHasAlliances(Long partyId, String electionYear, Long electionType);
	public boolean hasAlliances(String year, Long electionType, Long partyId);
	public List<SelectOptionVO> getConstituencies(Long stateId);
	public List<ConstituencyElection> getConstituencyElections(Long electionID, Long  districtID);
	public List<SelectOptionVO> getStaticParties();
}