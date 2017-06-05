package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.LocationVO;

public interface IFundManagementDashboardService {
	public List<LocationVO> getLocationWiseAmountDetails(String fromDateStr, String toDateStr, Long levelId,List<Long> levelValues,Long financialYrId, Long deptId, Long sourceId);
}
