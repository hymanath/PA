package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
	public List<Object[]> get2016LocationWiseRegisteredCounts(String type,Long locationScopeId,String locationType, List<Long> locationValue);
	public List<Object[]> getDataSourceTypeWiseRegisteredDetails(Date fromDate,Date toDate);
	public List<Object[]> getDataSourceTypeWiseRegisteredDetails1(Date fromDate,Date toDate);
	public List<Object[]> getTdpCadreRecordsCountLocWise(Date fromDate,Date toDate);
	public List<Object[]> getTdpCadreRecordsCount(Date fromDate,Date toDate);
	public List<Object[]> getRenewalTdpCadreRecordsCountLocWise(Date fromDate,Date toDate);
	public List<Object[]> getRenewalTdpCadreRecordsCount(Date fromDate,Date toDate);
	public List<Object[]> getLocationWiseTargets(Long locationScopeId);
	public List<Object[]> getTotalCadreCountLocationWise2014(Long userAccessLevelId,List<Long> userAccessLevelValues);
	public List<Object[]> getConstitiuencyWise2014CadreCountBasedOnUserType(Long userAccessLevelId,Set<Long> locationValue);
	public Long getTotalConstituencyForCdrRegStarted(Long stateId);
	public List<Long> getTodayTotalStartedRegistrationConstituencyStateWise(Long stateId);
	public List<Object[]> getTodayTotalStartedRegistrationConstituencyDetailsStateWise(Long stateId);
	
	public List<Long> getTodayMandalStartedStateWise(Long stateId);
	public List<Object[]> getTodayMandalStartedDtlsStateWise(Long stateId);
	public List<Long> getTodayLocalElectionBodyStartedStateWise(Long stateId);
	public List<Object[]> getTodayLocalElectionBodyStartedDtlsStateWise(Long stateId);
	public Long getTotalCadreCountLocationWise(Long accessLvlId,List<Long> accessLvlValue,String type);
	public Long getTotalRenewlCadreLocationWise(Long accessLvlId,List<Long> accessLvlValue,String type);
	public List<Object[]> get2014CadreBasedOnLocationIds(Long locationScopeId,List<Long> locationIdList);
	public List<Object[]> getTotalCadreLocationWise(Long locationScopeId, List<Long> locationIdList,String scope);
	public List<Object[]> getTotalRenewlCadreLocationWiseCount(Long accessLvlId,List<Long> accessLvlValue,String type);
	public List<Object[]> getConstituencyWiseTodayAndOverAllCounts(String type,Long stateId);
	public List<Object[]> getDistrictWiseTodayAndOverAllCounts(String type,Long stateId);
	public List<Object[]> get2016LocationWiseRegisteredCountsForConstituency(String type,Long locationScopeId,String locationType, List<Long> locationValue,Long districtId);
	public List<Object[]> get2014CadreDistWise(List<Long> constituencyIds,String districtName);
	public Long getTotalCountInConstituencies(String type,List<Long> constIds);
	public Long getOtherDistTotalCountInConstituencies(String type,List<Long> constIds,Long stateId);
	public List<Object[]> get2014And2016CadreCountDtls(List<Long> locValueList,Long memberLvl);
	public Long getMemberShipRegistrationsInCadreLocation(String locationtype,Long locationId,Long year,Long constituencyId,List<Long> constituencyIdsList,Long yearId);
	public Long getMemberShipRegistrationDtlsInCadreLocation(String locationtype,Long locationId,Long year,Long constituencyId,List<Long> constituencyIdsList,Long yearId);
	public List<Object[]> getMemberShipRegistratonsInCadreLocation(String locationtype,List<Long> locationIdsList,Long year,Long constituencyId,List<Long> constituencyIdsList,Long yearId);
}
