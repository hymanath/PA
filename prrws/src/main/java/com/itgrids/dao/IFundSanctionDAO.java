package com.itgrids.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.FundSanction;

public interface IFundSanctionDAO extends GenericDao<FundSanction,Long>{
	public List<Long> getLocationValues(Long scopeId);
	public List<Object[]> getLocationWiseAmount(Long levelId, List<Long> levelValues,Date fromDate,Date toDate,List<Long> financialYrIdList);
	public List<Object[]> getLocationInfo(Long levelId, List<Long> levelValues);
	public List<Object[]> getLocationsCountDetails(Long locatioinTypeId,Long financialYrId);
	public List<Object[]> getLocationWiseFundHighAndLow(Long financialYrId,Long departmentId,Long sourceId,Date sDate,Date eDate,Long locationScopeId,String type);
	public List<Object[]> getSchemeWiseFundHighAndLow(Long financialYrId,Long departmentId,Long sourceId,Date sDate,Date eDate
			,String type);
	public Long getTotalFund(Long financialYrId,Long departmentId,Long sourceId,Date sDate,Date eDate,Long scopeId);
	public List<Object[]> getLocationWiseGrantTypesFund(Long financialYrId,Long departmentId,Long sourceId,Date sDate,Date eDate
			,Long locationScopeId,Long locationVal);
	public Long getTotalSchemes(Long financialYrId,Long departmentId,Long sourceId,Date sDate,Date eDate);
}
