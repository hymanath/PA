package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.booth.PartyBoothPerformanceVO;

public interface IPartyBoothWiseResultsService {

	public List<SelectOptionVO> getConstituenciesForElectionScopeAndYear(Long electionScopeId, Long electionYear);
	
	public List<PartyBoothPerformanceVO> getBoothWiseResultsForParty(Long partyId, Long constituencyId, String electionYear);
}
