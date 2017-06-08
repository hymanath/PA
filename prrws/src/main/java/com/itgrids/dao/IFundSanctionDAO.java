package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.FundSanction;

public interface IFundSanctionDAO extends GenericDao<FundSanction,Long>{
	public List<Long> getLocationValues(Long scopeId);
	public List<Object[]> getFinancialYearWiseScheameDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList);
	public List<Object[]> getFinancialYearWiseFundDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,Long searchScopeId);
	public List<Object[]> getLocationWiseAmount(Long levelId, List<Long> levelValues,Date fromDate,Date toDate,List<Long> financialYrIdList,Long deptId,Long sourceId);
	public List<Object[]> getFinancialYearWiseDeptsWiseScheameDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList);
	public List<Object[]> getLocationInfo(Long levelId, List<Long> levelValues);
	public List<Object[]> getLocationsCountDetails(Long locatioinTypeId,List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,Long searchLevlId,List<Long> searchLvlVals);
	public List<Object[]> getLocationWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long locationScopeId,String type,Long searchLevlId,List<Long> searchLvlVals);
	public List<Object[]> getSchemeWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,String type,Long searchLevlId,List<Long> searchLvlVals);
	public Long getTotalFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long scopeId,Long searchLevlId,List<Long> searchLvlVals);
	public List<Object[]> getLocationWiseGrantTypesFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long locationScopeId,Long locationVal,Long searchLevlId,List<Long> searchLvlVals);
	public List<Object[]> getTotalFundAndCountDtls(List<Long> financialYrIdList,Long departmentId,Long sourceId,Date sDate,Date eDate,Long scopeId,String group,Long deptId);
	public List<Object[]> getTotalFundForScheme(List<Long> financialYrIdList,Long departmentId,Long sourceId,Long schemeId,Date sDate,Date eDate,Long deptId);
	public Long getTotalSchemes(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,Date sDate,Date eDate,Long searchLevlId,List<Long> searchLvlVals);
	public List<Long> getLocationBlockLevelIds(Long locationId,Long locationLevelId,Long blockLevelId);
	public List<Object[]> getAllDistrictByStateId(Long superLocationId,List<Long> financialYrIdList,Long deptId,Long sourceId,Long locationScopeId,Date sDate,Date eDate);
	public List<Object[]> getAllConstituencyByDistrictId(Long superLocationId,List<Long> financialYrIdList,Long deptId,Long sourceId,Long locationScopeId,Date sDate,Date eDate);
}
