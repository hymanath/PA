package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.ToursBasicVO;

public interface ICoreDashboardToursService {
	public ToursBasicVO getToursBasicOverviewCountDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId);
}
