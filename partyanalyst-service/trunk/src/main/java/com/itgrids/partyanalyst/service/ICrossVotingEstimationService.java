package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ICrossVotingEstimationService {
	
	public CrossVotingConsolidateVO getConsolidatedCrossVotingDetails(String electionYear, Long partyId, Long acId, Long pcId, String includeAliance);
	
	public List<SelectOptionVO> getAssembliesForParliament(Long parliamentId, Long electionYear);
	
	public List<SelectOptionVO> getPartiesForConstituency(Long assemblyId, String electionYear);
}
