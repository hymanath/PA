package com.itgrids.core.api.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.NominatedPostCandidateDtlsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;




public interface INominatedPostLocationDashboardService {
	public List<NominatedPostCandidateDtlsVO> getNominatedPositionWiseCandidates(List<Long> locationValues,
			String fromDateStr, String toDateStr,Long locationTypeId,String year,Long boardLvlId,Long startIndex,Long endIndex);
	public List<NominatedPostCandidateDtlsVO> getLevelWisePostsOverView(List<Long> locationValues,String fromDateStr,String toDateStr,Long locationTypeId,Long boardLevelId);
	public List<NominatedPostDetailsVO> getDepartmentWisePostAndApplicationDetails(List<Long> locationValues,String startDate, String endDate,Long locationTypeId,String year,Long boardLevelId,Long deptId);
	public List<NominatedPostCandidateDtlsVO> getLevelWiseGoIssuedPostions(List<Long> locationValues,String fromDateStr, String toDateStr,Long locationTypeId,String year,Long boardLvlId,List<Long> statusIds,Long startIndex,Long endIndex);
}
