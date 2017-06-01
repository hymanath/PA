package com.itgrids.core.api.service;

import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;


public interface ILocationDashboardService {
	public CandidateDetailsForConstituencyTypesVO getCandidateAndPartyInfoForConstituency(Long constituencyId);
	
}
