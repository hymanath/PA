package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCasteStateInfo;

public interface ITdpCadreCasteStateInfoDAO extends GenericDao<TdpCadreCasteStateInfo,Long>{
	
	public int pushCadreCountsLocationWiseByCasteState();
	
	public List<Object[]> casteCategoryWiseTdpCadreCounts(Long stateId , String minorityCasteIds);
	public Object[] minorityCastesTdpCadreCounts(Long stateId , String minorityCasteIds);
	
	public List<Object[]> stateWiseTdpCadreCasteCounts(Long stateId , String minorityCasteIds);
	public List<Object[]> stateWiseTdpCadreMinorityCasteCounts(Long stateId , String minorityCasteIds);
	
}
