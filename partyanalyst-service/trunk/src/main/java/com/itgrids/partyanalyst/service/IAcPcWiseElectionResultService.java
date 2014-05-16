package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.BasicVO;
import com.itgrids.partyanalyst.dto.GenericVO;

public interface IAcPcWiseElectionResultService 
{

	public List<BasicVO> getPartyWiseComperassionResult(Long stateId,Long electionId,List<Long> partyIds,Long electionScopeId,String scope);
	
	public List<GenericVO> cbnOrModiEffect(Long electionId,Long stateid,Long partyId,Long electionScopeId);
}
