package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.PartyElectionResultsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IElectionResultsUpdationService {

	public List<PartyElectionResultsVO> getCandidateResults(String electionType,Long constituencyId,Long stateId,String electionYear);
	
	public List<PartyElectionResultsVO> updateCandidateResults(Long candidateId,Long constiElecId,String status);
	
	public List<SelectOptionVO> getElectionYears(Long stateId, String electionType);
	
}
