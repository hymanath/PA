package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BasicVO;

public interface IAcPcWiseElectionResultService 
{

	public List<BasicVO> getPartyWiseComperassionResult(Long stateId,Long electionId,List<Long> partyIds,Long electionScopeId);
}
