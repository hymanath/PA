package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.FundMatrixVO;
import com.itgrids.dto.FundSchemeVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;

public interface IFundManagementDashboardService {
	public List<LocationVO> getLocationLevelInfo();
	public List<LocationVO> getLocationWiseAmountDetails(InputVO inputVO);
	public List<FundSchemeVO> getFinancialYearWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
	List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList,String order,String sortingType,Long searchLevelId);
	public List<FundSchemeVO> getFinancialYearWiseDeptsWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList, String order, String sortingType,Long searchLevelId);
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
	public List<LocationVO> getLocationWiseAmountAndCountDetails(InputVO inputVO);
	public List<FundMatrixVO> compareFundsBetweenFinancialYears(InputVO inputVO);
	public List<LocationVO> getLocationWiseFundSanctionDetails(InputVO inputVO);
	public IdNameVO getMinMaxDates();

}
