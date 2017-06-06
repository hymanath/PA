package com.itgrids.service;

import com.itgrids.dto.LocationFundDetailsVO;

import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationVO;

public interface IFundManagementDashboardService {
	public List<LocationVO> getLocationWiseAmountDetails(InputVO inputVO);
	public LocationFundDetailsVO getLocationWiseFundDetails(Long financialYrId,Long departmentId,Long sourceId,String startDate,String endDate,Long locationScopeId,String type );
	public LocationFundDetailsVO getTotalFunds(Long financialYrId,Long departmentId,Long sourceId,String startDate,String endDate);
}
