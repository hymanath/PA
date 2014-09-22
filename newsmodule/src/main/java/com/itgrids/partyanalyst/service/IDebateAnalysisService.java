package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.DebateCandidateCharcVO;

import java.util.List;

import com.itgrids.partyanalyst.dto.DebateTopicVO;

public interface IDebateAnalysisService {
	public DebateCandidateCharcVO getPartyWiseCandidateCharacteristicsDetails();
	public List<DebateTopicVO> getPartyCandidatePerfortmanceTopicWise();
}
