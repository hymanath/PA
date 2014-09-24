package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.DebateCandidateCharcVO;

import java.util.List;

import com.itgrids.partyanalyst.dto.DebateAnalysisVO;
import com.itgrids.partyanalyst.dto.DebatePartyWiseCountVO;
import com.itgrids.partyanalyst.dto.DebateTopicVO;

public interface IDebateAnalysisService {
	public DebateCandidateCharcVO getPartyWiseCandidateCharacteristicsDetails();
	public List<DebateAnalysisVO> partyWiseCandidatePerformance();
	public List<DebateTopicVO> getPartyCandidatePerfortmanceTopicWise();
	public List<DebatePartyWiseCountVO> getPartyWiseOverAllPerformance();
	public List<DebateTopicVO> getPartyWiseStrongAndWeakTopicAndCandidates();
}
