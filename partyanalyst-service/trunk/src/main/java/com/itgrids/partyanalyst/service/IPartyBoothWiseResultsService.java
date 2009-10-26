package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.excel.PartyBoothPerformanceVO;

public interface IPartyBoothWiseResultsService {

	public List<SelectOptionVO> getParties();
	
	public List<SelectOptionVO> getConstituenciesForParty(Long partyId, Long electionTypeId, String electionYear);
	
	public List<PartyBoothPerformanceVO> getBoothWiseResultsForParty(Long partyId, Long constituencyId, String electionYear);
}
