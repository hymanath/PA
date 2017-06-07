package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.FundSanction;

public interface IFundSanctionDAO extends GenericDao<FundSanction,Long>{
	public List<Long> getLocationValues(Long scopeId);
	public List<Object[]> getFinancialYearWiseScheameDetails(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,List<Long> schemeIdsList,Date startDate,Date endDate,Long searchScopeId,List<Long> searchScopeValuesList);
	public List<Object[]> getLocationWiseAmount(Long levelId, List<Long> levelValues,Date fromDate,Date toDate,List<Long> financialYrIdList);
	public List<Object[]> getLocationInfo(Long levelId, List<Long> levelValues);
	public List<Object[]> getLocationsCountDetails(Long locatioinTypeId,List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList);
	public List<Object[]> getLocationWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long locationScopeId,String type);
	public List<Object[]> getSchemeWiseFundHighAndLow(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,String type);
	public Long getTotalFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate,Long scopeId);
	public List<Object[]> getLocationWiseGrantTypesFund(List<Long> financialYearIdsList,List<Long> deptIdsList,
			List<Long> sourceIdsList,Date sDate,Date eDate
			,Long locationScopeId,Long locationVal);
	public List<Object[]> getTotalFundAndCountDtls(List<Long> financialYrIdList,Long departmentId,Long sourceId,Date sDate,Date eDate,Long scopeId,String group);
	public List<Object[]> getTotalFundForScheme(List<Long> financialYrIdList,Long departmentId,Long sourceId,Long schemeId,Date sDate,Date eDate);
	public Long getTotalSchemes(List<Long> financialYearIdsList,List<Long> deptIdsList,List<Long> sourceIdsList,Date sDate,Date eDate);
}
