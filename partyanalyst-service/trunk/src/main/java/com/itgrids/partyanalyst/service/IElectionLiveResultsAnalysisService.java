package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ElectionLiveResultVO;

public interface IElectionLiveResultsAnalysisService {

	public ElectionLiveResultVO getCountOfConstituenciesForAElection(Long electionId);
	
	public List<ElectionLiveResultVO> getLeadingOrWinningContituenciesForAParty(Long electionId);
}
