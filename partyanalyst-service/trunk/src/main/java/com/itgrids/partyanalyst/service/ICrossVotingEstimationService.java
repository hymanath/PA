package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ICrossVotingEstimationService {
	
	public List<SelectOptionVO> getParliamentConstituenciesForElectionYear(Long electionYear);
	
	public List<SelectOptionVO> getAssembliesForParliament(Long delimitationConstituencyId);
	
	public CrossVotingConsolidateVO getConsolidatedCrossVotingDetails(String electionYear, Long partyId, Long acId, Long pcId, String includeAliance);
	
}
