package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionScope;
import com.itgrids.partyanalyst.utils.ElectionScopeLevelEnum;
/**
 * 
 * @author Narender Akula
 *
 */
public interface IBasePartyResultsService {
	public ElectionScope getElectionScope(Long typeId, Long countryID, Long stateID);
	public List<Election> getElections(ElectionScope scope);
	public List<PartyInfoVO> getPartyAndCompetetorsInfo(Election election,
			String partyShortName,Long stateID, Long districtID, Long constituencyID, int competetorSize, ElectionScopeLevelEnum level,Boolean hasAlliance,List<SelectOptionVO> allianceParties);
}
