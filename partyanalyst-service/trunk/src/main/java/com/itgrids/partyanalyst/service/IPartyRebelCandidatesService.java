package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.SelectOptionVO;

public interface IPartyRebelCandidatesService {

	public List<SelectOptionVO> findByPartyIdAndElectionId(Long partyId, Long electionId);
	public void savePartyRebelCandidates(Long stateId, Long partyId, Long electionId, List<Long> rebelsList);
}
