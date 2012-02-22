package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ElectionLiveResultVO;
import com.itgrids.partyanalyst.dto.PositionManagementVO;

public interface IElectionLiveResultsAnalysisService {

	public ElectionLiveResultVO getCountOfConstituenciesForAElection(Long electionId , String electionIsPartial);
	
	public List<ElectionLiveResultVO> getLeadingOrWinningContituenciesForAParty(Long electionId);
	
	public List<ElectionLiveResultVO> getPartiesGainAndLossInfo(Long electionId);
	
	 public List<PositionManagementVO> getCurrentMinistersDetailsForCurrentAndPrevEle(Long type,Long stateId,String year,Long elecId);
}
