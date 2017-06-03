package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CandidateDetailsForConstituencyTypesVO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;


public interface ILocationDashboardService {
	public CandidateDetailsForConstituencyTypesVO getCandidateAndPartyInfoForConstituency(Long constituencyId);
	public List<LocationVotersVO> getVotersAndcadreAgeWiseCount(Long constituencyId,Long publicationDateId);
	
}
