package com.itgrids.partyanalyst.service;


import java.util.List;

import com.itgrids.partyanalyst.dto.OptionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ISuggestiveModelService {
	
	public OptionVO getPartyPerformanceForSelectedLocation(Long constituencyId,Long electionId,Long partyId,Long locationId,String type);

	public List<SelectOptionVO> getConstituenciesForUserAccessByStateId(Long electionScope,Long stateId,Long userId);
	
	public List<SelectOptionVO> getPartyDetailsByMandal(Long mandalId);
	
	public List<SelectOptionVO> getElectionIdsAndYearsBytehsilId(List<Long> electionScope,Long partyId,Long tehsilId);
}
