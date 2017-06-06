package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.FundSchemeVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;

public interface IFundManagementDashboardService {
	public List<LocationVO> getLocationWiseAmountDetails(InputVO inputVO);
	public List<FundSchemeVO> getFinancialYearWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList);
	public LocationFundDetailsVO getLocationWiseFundDetails(Long financialYrId,Long departmentId,Long sourceId,String startDate,String endDate,Long locationScopeId,String type );
	public LocationFundDetailsVO getTotalFunds(Long financialYrId,Long departmentId,Long sourceId,String startDate,String endDate);
	public LocationFundDetailsVO getTotalLocationsByScopeId(Long financialYrId,Long departmentId,Long sourceId,String startDateStr,String endDateStr,Long locationScopeId);
	public LocationFundDetailsVO getSchemeWiseHighestAndLowestFund(Long financialYrId,Long departmentId,Long sourceId,String startDateStr,String endDateStr,String type );
	public LocationFundDetailsVO getTotalSchemes(Long financialYrId,Long departmentId,Long sourceId,String startDateStr,String endDateStr);
}
