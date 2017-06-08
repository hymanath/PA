package com.itgrids.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itgrids.dto.FundSchemeVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;

public interface IFundManagementDashboardService {
	public List<LocationVO> getLocationLevelInfo();
	public List<LocationVO> getLocationWiseAmountDetails(InputVO inputVO);
	public List<FundSchemeVO> getFinancialYearWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
	List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList);
	public List<FundSchemeVO> getFinancialYearWiseDeptsWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList);
	public LocationFundDetailsVO getLocationWiseFundDetails(InputVO inputVO);
	public LocationFundDetailsVO getTotalFunds(InputVO inputVO);
	public LocationFundDetailsVO getTotalLocationsByScopeId(InputVO inputVO);
	public LocationFundDetailsVO getSchemeWiseHighestAndLowestFund(InputVO inputVO );
	public LocationFundDetailsVO getTotalSchemes(InputVO inputVO);
	public LocationFundDetailsVO getAverageFundForAnyLevel(InputVO inputVO);
	public LocationFundDetailsVO getAverageFundForScheme(InputVO inputVO);
	public List<LocationFundDetailsVO> getAllDistrictByStateId(Long stateId);
	public List<LocationFundDetailsVO> getAllConstituenciesByDistrictId(Long districtId);
	public List<LocationFundDetailsVO> getAllSubLocationsBySuperLocationId(InputVO inputVO);
	public List<LocationVO> getAllFiniancialYears();
	public List<LocationFundDetailsVO> getAllDepartments();

}
