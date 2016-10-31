package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.ToursBasicVO;

public interface ICoreDashboardToursService {
	public ToursBasicVO getToursBasicOverviewCountDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId);
	public String getDesigWiseMemberDtls(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId);
	public List<ToursBasicVO> getDistrictWiseToursSubmitedDetails(Long stateId,String fromDateStr,String toDateStr,Long activityMemberId,Long userTypeId);
}
