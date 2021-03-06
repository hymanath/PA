package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.AssignKeyCandidateVO;
import com.itgrids.partyanalyst.dto.ConstituencyElectionResultVO;
import com.itgrids.partyanalyst.dto.ElectionGoverningBodyVO;
import com.itgrids.partyanalyst.dto.ElectionLiveResultVO;
import com.itgrids.partyanalyst.dto.PartyElectionResultVO;
import com.itgrids.partyanalyst.dto.PositionManagementVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IElectionLiveResultsAnalysisService {

	public ElectionLiveResultVO getCountOfConstituenciesForAElection(Long electionId , String electionIsPartial);
	
	public List<ElectionLiveResultVO> getLeadingOrWinningContituenciesForAParty(Long electionId);
	
	public List<ElectionLiveResultVO> getPartiesGainAndLossInfo(Long electionId);
	
	public List<PositionManagementVO> getCurrentMinistersDetailsForCurrentAndPrevEle(Long type,Long stateId,String year,Long elecId,String reqtype);
	
	public List<ConstituencyElectionResultVO> getConstituencyWiseCandidatesStates(Long electionId);
	
	public List<SelectOptionVO> getCandidatesBasedOnSelection(String candidateName ,Long stateId , Long partyId);
	
	public ResultStatus saveKeyCandidates(AssignKeyCandidateVO assAssignKeyCandidateVO);
	
	public ElectionLiveResultVO getOverViewCount(Long electionId);
	
	public List<PositionManagementVO> getDistrictWisePartyPerfDetails(Long electionId,Long stateId);
	
	public List<PositionManagementVO> getWinCountForGraphs(Long electionId,Long stateId,Long districtId);
	
	public List<PositionManagementVO> getAllPartiesForPartialElection(Long electionId,Long stateId);
	
	public  List<PositionManagementVO> getAllPartiesPerfoDistWiseForPartialEle(Long electionId,List<Long> partiesList);
	
	public List<PartyElectionResultVO> getGenderAnalysisInElection(Long electionId);
	
	public List<PositionManagementVO> getDistrictWisePartyPerfDetailsNew(Long electionId,Long stateId,List<Long> partyIds);
	
	public List<PositionManagementVO> getAllPartiesForAnElec(Long electionId,Long stateId);
	
	public List<PositionManagementVO> getAllPartyCountsDistrictWise(Long electionId,List<Long> partyIds,boolean includeAlliances,String type);
	
	public List<ElectionGoverningBodyVO> getAllCandidateDetailsForMinisterProfile(Long candidateId);
	
	public ElectionLiveResultVO getLiveResultsDetails(Long electionId);
	
	public List<PartyElectionResultVO> getCandidatesInfoDistrictWise(Long electionId,Long districtId);
	
	public List<PartyElectionResultVO> getWonLeadCandidatesInfo(Long electionId);
}
