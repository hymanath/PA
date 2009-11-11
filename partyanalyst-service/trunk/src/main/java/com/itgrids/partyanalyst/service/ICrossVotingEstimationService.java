package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;

public interface ICrossVotingEstimationService {
	
	public CrossVotingConsolidateVO getConsolidatedCrossVotingDetails(String electionYear, Long partyId, Long acId, Long pcId);
}
