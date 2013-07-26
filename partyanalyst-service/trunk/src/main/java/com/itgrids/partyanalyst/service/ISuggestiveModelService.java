package com.itgrids.partyanalyst.service;


import java.util.List;

import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ISuggestiveModelService {
	
	public List<SelectOptionVO> getConstituenciesForUserAccessByStateId(Long electionScope,Long stateId,Long userId);
	
	public List<SelectOptionVO> getPartyDetailsByMandal(Long mandalId);
	
	public List<SelectOptionVO> getElectionIdsAndYearsBytehsilId(List<Long> electionScope,Long partyId,Long tehsilId);
	
	public List<PartyPositionVO> getPartyPerformenceReport(Long constituencyId,Long partyId,Long locationId,String locationType,Long electionId,String tempVar);

}
