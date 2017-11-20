package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.FundSanction;

public interface IFundSanctionDAO extends GenericDao<FundSanction,Long>{
	public List<Long> getLocationValues(Long scopeId);
	public List<Object[]> getFinancialYearWiseScheameDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList,Long searchLevelId,List<Long> govtSchmeIdsList,List<Long> subProgramIdsList,
			Long glSearchLevelId,List<Long> glSearchLevelValue, List<Long> grantTypeIdsList);
	public List<Object[]> getFinancialYearWiseFundDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,Long searchScopeId);
	public List<Object[]> getLocationWiseAmount(Long levelId, Long locationId, Long locationLevelId,Date fromDate,Date toDate,List<Long> financialYrIdList,List<Long> deptId,List<Long> sourceId);
	public List<Object[]> getFinancialYearWiseDeptsWiseScheameDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList,Long searchLevelId);
	public List<Object[]> getLocationsCountDetails(Long locatioinTypeId,List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,Long searchLevlId,List<Long> searchLvlVals,Date fromDate,Date toDate);
	public List<Object[]> getLocationWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long locationScopeId,String type,Long searchLevlId,List<Long> searchLvlVals,List<Long> schemeIds,List<Long> subProgIds);
	public List<Object[]> getSchemeWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,String type,Long searchLevlId,List<Long> searchLvlVals);
	public Long getTotalFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long scopeId,Long searchLevlId,List<Long> searchLvlVals,List<Long> schemeIds,List<Long> subProgIds);
	public List<Object[]> getLocationWiseGrantTypesFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long locationScopeId,Long locationVal,Long searchLevlId,List<Long> searchLvlVals,List<Long> schemeIds,List<Long> subProgIds);
	public List<Object[]> getTotalFundAndCountDtls(List<Long> financialYrIdList,List<Long> departmentId,List<Long> sourceId,Date sDate,Date eDate,Long scopeId,String group,Long searchLevlId,List<Long> searchLvlVals);
	public List<Object[]> getTotalFundForScheme(List<Long> financialYrIdList,List<Long> departmentId,List<Long> sourceId,List<Long> schemeId,Date sDate,Date eDate,String type,Long searchLevlId,List<Long> searchLvlVals);
	public Long getTotalSchemes(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,Date sDate,Date eDate,Long searchLevlId,List<Long> searchLvlVals,String type);
	public List<Long> getLocationBlockLevelIds(Long locationId,Long locationLevelId,Long blockLevelId);
	public List<Object[]> getAllDistrictByStateId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptId,List<Long> sourceId,Date sDate,Date eDate,Long scopeId);
	public List<Object[]> getAllConstituencyByDistrictId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptId,List<Long> sourceId,Date sDate,Date eDate,Long scopeId,Long superLocationLevelId);
	public List<Object[]> getAllTehsilByConstituencyId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate,Long scopeId);
	public List<Object[]> getAllPanchayatByTehsilId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate);
	public List<Object[]> getLocationWiseAmountAndCountDetails(Date fromDate,Date toDate,Long blockLevelId,List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> levelValues);
	public Object[] getMinMaxDates();
	public List<Object[]> getFundSactionCount(List<Long> financialYrIdList);
	public List<Object[]> getGrantTypeHighestAndLowestFund(List<Long> financialYrIdList, List<Long> deptIdsList,List<Long> sourceIdsList, Date startDate, Date endDate,
	String type, Long searchLevelId, List<Long> searchLvlVals);
	public List<Object[]> financialYearWiseFundDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,Long searchScopeId,Long searchLevelId, Long searchLevelValue);
	public List<Object[]> getAllConstituencyByDistrictIds(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate,Long scopeId);
	public List<Object[]> getAllConstituencyByParliamentConstId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate,Long scopeId,Long superLocationLevelId);
	public List<Object[]> getAllParliamentByStateId(Long superLocationId,List<Long> financialYrIdList,List<Long> deptIdList,List<Long> sourceIdList,Date sDate,Date eDate,Long scopeId);
	public List<Object[]> getFundManagementSystemWorkDetails(List<Long> financialYearIdsList, List<Long> departmentIdList, Date startDate,Date endDate,Long locationId,String type);
}
