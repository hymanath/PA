package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateVO;
import com.itgrids.partyanalyst.dto.ResultStatus;

public interface ICandidateUpdationDetailsService {
	
public CandidateVO getCandidateDetailsForElection(Long electionId,Long districtId);
public Object gettingElectionYears(Long electionTypeId);
public Object getAllDistrictsForAState(Long stateId);
public List<CandidateVO> gettingCandidateDetails(Long electionScopeId,Long electionId,List<Long> districtIds);
public ResultStatus updateDetailsofACandidate(List<CandidateVO> candidateVOList);
public List<CandidateVO> getCandidateInfo(Long electionId,String electionType);

}