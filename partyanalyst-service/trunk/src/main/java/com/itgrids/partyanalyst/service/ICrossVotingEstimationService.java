package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CrossVotingConsolidateVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface ICrossVotingEstimationService {
	
	public List<SelectOptionVO> getConstituenciesForElectionYearAndScopeForBoothData(String electionYear, Long electionScopeId);
	
	public List<SelectOptionVO> getAssembliesForParliament(Long parliamentId, Long electionYear);
	
	public List<SelectOptionVO> getPartiesForConstituencyAndElectionYearForBoothData(Long constituencyId, String electionYear);
	
	public CrossVotingConsolidateVO getConsolidatedCrossVotingDetails(String electionYear, Long partyId, Long acId, Long pcId, String includeAliance);
	
	public List<SelectOptionVO> getConstituenciesForElectionYearAndTypeWithUserAccess(Long userId,Long electionYear,Long electionTypeId);
	
	public List<String> getElectionYearsForBoothResult();
	
	public List<SelectOptionVO> getConstituenciesForBoothResultWithUserAccess(Long userId,Long electionYear,Long electionTypeId);
		
}
