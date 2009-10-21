package com.itgrids.partyanalyst.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.appfuse.service.GenericManager;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.State;

public interface IStaticDataService {

	public List<SelectOptionVO> getStates(Long electionType);
	public List<ElectionScope> getElectionScope(Long electionType);
	public Set<String> getElectionYears(Long electionType);
	public List<SelectOptionVO> getParties();
	public List<SelectOptionVO> getDistricts(Long stateId);
	public List<State> getAllStates();
	public List<SelectOptionVO> getAllianceParties(String electionYear, Long partyId);
	public List<Party> getAllianceParties(String electionYear);
	public boolean hasAlliances(String year, Long partyId);
}