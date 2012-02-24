package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AssignKeyCandidateVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionLiveResultVO;
import com.itgrids.partyanalyst.dto.PositionManagementVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.AssignKeyCandidateVO;

public interface IElectionLiveResultsAnalysisService {

	public ElectionLiveResultVO getCountOfConstituenciesForAElection(Long electionId , String electionIsPartial);
	
	public List<ElectionLiveResultVO> getLeadingOrWinningContituenciesForAParty(Long electionId);
	
	public List<ElectionLiveResultVO> getPartiesGainAndLossInfo(Long electionId);
	
	public List<PositionManagementVO> getCurrentMinistersDetailsForCurrentAndPrevEle(Long type,Long stateId,String year,Long elecId,String reqtype);
	
	public List<ConstituencyElectionResultVO> getConstituencyWiseCandidatesStates(Long electionId);
	
	public List<SelectOptionVO> getCandidatesBasedOnSelection(String candidateName ,Long stateId , Long partyId);
	
	public ResultStatus saveKeyCandidates(AssignKeyCandidateVO assAssignKeyCandidateVO);
}
