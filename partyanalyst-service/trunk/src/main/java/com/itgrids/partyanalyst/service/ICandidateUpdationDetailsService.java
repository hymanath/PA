package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateVO;

public interface ICandidateUpdationDetailsService {
	public CandidateVO getCandidateDetailsForElection(Long electionId,Long districtId);
}
