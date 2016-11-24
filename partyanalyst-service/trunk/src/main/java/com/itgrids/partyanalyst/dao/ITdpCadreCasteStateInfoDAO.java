package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCasteStateInfo;

public interface ITdpCadreCasteStateInfoDAO extends GenericDao<TdpCadreCasteStateInfo,Long>{
	
	public int pushCadreCountsLocationWiseByCasteState();
}
