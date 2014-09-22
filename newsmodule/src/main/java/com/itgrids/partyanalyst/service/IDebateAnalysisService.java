package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DebateTopicVO;

public interface IDebateAnalysisService {

	public List<DebateTopicVO> getPartyCandidatePerfortmanceTopicWise();
}
