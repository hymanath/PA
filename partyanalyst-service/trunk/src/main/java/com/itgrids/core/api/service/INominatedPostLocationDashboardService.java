package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.NominatedPostCandidateDtlsVO;




public interface INominatedPostLocationDashboardService {
	public List<NominatedPostCandidateDtlsVO> getNominatedPositionWiseCandidates(List<Long> locationValues,
			String fromDateStr, String toDateStr,Long locationTypeId,String year,Long boardLvlId,Long startIndex,Long endIndex);
}
