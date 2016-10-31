package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfo;

public interface ITdpCadreLocationInfoDAO extends GenericDao<TdpCadreLocationInfo,Long>{
	
	public int deleteAllRecords(List<Long> locationScopeIds);
	public int setPrimaryKeyAutoIncrementToOne();
	public List<Object[]> get2014TotalCadreCountLocationWiseCount(Long locationScopeId,List<Long> locationValue,Long stateId);
	public List<Object[]> get2014TotalCadreCountLocationWise(Long locationScopeId,List<Long> locationValue);
	public List<Object[]> get2014TotalCadreCountBasedOnUserType(List<Long> locationValue,Long userType,Long activityMemberId);
	public List<Object[]> getLocationsRegistrationsDetails(GISVisualizationParameterVO inputVO);
	
	public int insertTdpCadreLocationInfoUpToConstituencyLevel();
	public int insertTdpCadreLocationInfoUpToLowLevel();
	public List<Object[]> get2016LocationWiseRegisteredCounts(String type,Long locationScopeId);
	public List<Object[]> getDataSourceTypeWiseRegisteredDetails(Date fromDate,Date toDate);
	public List<Object[]> getDataSourceTypeWiseRegisteredDetails1(Date fromDate,Date toDate);
	public List<Object[]> getTotalCadreCountLocationWise2014(Long userAccessLevelId,List<Long> userAccessLevelValues);
	public List<Object[]> getConstitiuencyWise2014CadreCountBasedOnUserType(Long userAccessLevelId,Set<Long> locationValue);
	 
}
