package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreGenderInfo;

public interface ITdpCadreGenderInfoDAO extends GenericDao<TdpCadreGenderInfo,Long>{
	
	public int pushCadreCountsLocationWiseByGender();
	
	public List<Object[]> stateWiseCadreGenderCounts(Long stateId);
	public List<Object[]> districtWiseCadreGenderCounts(Long stateId , Long districtId );
	public List<Object[]> constituencyWiseCadreGenderCounts(Long stateId , Long districtId );
	
	public List<Object[]> privilegedStateWiseCadreGenderCounts(List<Long> locationIdList, String accessType);
	public List<Object[]> privilegedDistrictWiseCadreGenderCounts(List<Long> districtIdList );
	public List<Object[]> privilegedConstituencyWiseCadreGenderCounts(List<Long> locationIdList );
	
	public int deleteAllRecords(List<Long> locationScopeIds);
	public int insertTdpCadreLocationInfoUpToLowLevelByGender();
}
