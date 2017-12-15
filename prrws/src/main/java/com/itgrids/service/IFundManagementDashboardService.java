package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.FundMatrixVO;
import com.itgrids.dto.FundSchemeVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.NregsFmsWorksVO;
import com.itgrids.dto.PageComponentVO;
import com.itgrids.dto.ResultVO;

public interface IFundManagementDashboardService {
	public List<LocationVO> getLocationLevelInfo();
	public List<LocationVO> getLocationWiseAmountDetails(InputVO inputVO);
	public List<FundSchemeVO> getFinancialYearWiseSchemeDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
	List<Long> sourceIdsList,List<Long> schemeIdsList,String startDateStr,String endDateStr,Long searchScopeId,List<Long> searchScopeValuesList,String order,String sortingType,Long searchLevelId,List<Long> govtSchmeIdsList,List<Long> subProgramIdsList,Long glSearchLevelId,List<Long> glSearchLevelValue,String viewType,List<Long> grantTypeIdsList,InputVO inputVO);
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
	public List<LocationFundDetailsVO> getFundSactionCount(List<Long> FinancialYrIdList);
	public List<LocationVO> getLocationWiseAmountAndCountDetails(InputVO inputVO);
	public List<FundMatrixVO> compareFundsBetweenFinancialYears(InputVO inputVO);
	public List<LocationVO> getLocationWiseFundSanctionDetails(InputVO inputVO);
	public IdNameVO getMinMaxDates();
	public LocationFundDetailsVO getGrantTypeHighestAndLowestFund(InputVO inputVO);
	public List<LocationFundDetailsVO> getAllSubLocations(InputVO inputVO);
	public List<LocationFundDetailsVO> getGovtSchemesDetails();
	public List<LocationFundDetailsVO> getGovtSubProgramsDetails(Long govtSchemesId);
	 public List<LocationFundDetailsVO> getALlProgramesAmountDetails(InputVO inputVO);
	 public LocationFundDetailsVO getSchemeWiseOverviewDetails(InputVO inputVO);
	public List<LocationFundDetailsVO> getAllSubLocationsOnsuperLocation(InputVO inputVO);
	public List<LocationFundDetailsVO> getGovtGrantTypeDetails(Long programId,Long govtSchemesId);
	public List<LocationFundDetailsVO> getGovtSchemsTypeDetails(Long programId, Long grantTypeId);
	
	public  List<NregsFmsWorksVO> getMgnregsFMSWorksDetails(InputVO inputVO);
	public  List<NregsFmsWorksVO> getMgnregsFMSWorksDetailsByCategory(InputVO inputVO);
	
	public List<PageComponentVO> getPageWiseComponentDetails();
	public ResultVO savePageWiseComponents(IdNameVO inputVO);
}
