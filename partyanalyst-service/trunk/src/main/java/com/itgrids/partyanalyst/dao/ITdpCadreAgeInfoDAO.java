package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreAgeInfo;

public interface ITdpCadreAgeInfoDAO extends GenericDao<TdpCadreAgeInfo,Long>{
	
	public int deleteAllRecords(List<Long> locationScopeIds);
	public int pushCadreCountsLocationWiseByAge();
	public int insertTdpCadreLocationInfoUpToLowLevelByAgeRange();
	
	public List<Object[]> getStateWiseAgeWiseCadreCounts(Long stateId);
	public List<Object[]> getDistrictwiseAgeWiseCadreCounts(Long stateId );
	public List<Object[]> getConstituencyWiseAgeWiseCadreCounts(Long stateId , Long districtId );
	
	public List<Object[]> getStateWiseAgeWiseCadreCountsByConstituencies(Long stateId);
	
	public List<Object[]> privilegedGetStateWiseAgeWiseCadreCounts(List<Long> locationIdList, String accessType);
	public List<Object[]> privilegedDistrictwiseAgeWiseCadreCounts(List<Long> distIdList );
	public List<Object[]> privilegedConstituencyWiseAgeWiseCadreCounts(List<Long> constIdList );
	public List<Object[]> getTehsilWiseAgeReport(Long constituecnyId, Long locationScopeId);
	
}
