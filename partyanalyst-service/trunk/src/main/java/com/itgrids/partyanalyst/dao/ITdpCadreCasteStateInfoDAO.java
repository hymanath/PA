package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCasteStateInfo;

public interface ITdpCadreCasteStateInfoDAO extends GenericDao<TdpCadreCasteStateInfo,Long>{
	
	public int pushCadreCountsLocationWiseByCasteState();
	
	public List<Object[]> casteCategoryWiseTdpCadreCounts(Long stateId , String minorityCasteIds);
	public Object[] minorityCastesTdpCadreCounts(Long stateId , String minorityCasteIds);
	
	public List<Object[]> stateWiseTdpCadreCasteCounts(Long stateId , String minorityCasteIds);
	public List<Object[]> stateWiseTdpCadreMinorityCasteCounts(Long stateId , String minorityCasteIds);
	
	public List<Object[]> districtWiseTdpCadreCasteCounts(Long stateId , Long districtId,String minorityCasteIds);
	public List<Object[]> districtWiseTdpCadreMinorityCasteCounts(Long stateId , Long districtId,String minorityCasteIds);
	public List<Object[]> constituencyWiseTdpCadreCasteCounts(Long stateId , Long districtId );
	public List<Object[]> privilegedDistrictWiseTdpCadreCasteCounts(List<Long> distIdList,String minorityCasteIds);
	
	public List<Object[]> privilegedDistrictWiseTdpCadreMinorityCasteCounts(List<Long> distIdList, String minorityCasteIds);
	public List<Object[]> privilegedConstituencyWiseTdpCadreCasteCounts(List<Long> locationIdList);
	public List<Object[]> privilegedCasteCategoryWiseTdpCadreCounts(List<Long> locationIdList , String minorityCasteIds, String accessType);
	public Object[] privilegedMinorityCastesTdpCadreCounts(List<Long> locationIdList , String minorityCasteIds, String accessType);
	public List<Object[]> privilegedStateWiseTdpCadreCasteCounts(List<Long> locationIdList , String minorityCasteIds, String accessType);
	public List<Object[]> privilegedStateWiseTdpCadreMinorityCasteCounts(List<Long> locationIdList , String minorityCasteIds, String accessType);
	
	public int deleteAllRecords(List<Long> locationScopeIds);
	public int insertTdpCadreLocationInfoUpToLowLevelByCasteState();
	
}
