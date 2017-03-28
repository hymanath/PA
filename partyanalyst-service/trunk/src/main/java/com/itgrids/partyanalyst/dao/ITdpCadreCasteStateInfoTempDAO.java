package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCasteStateInfoTemp;

public interface ITdpCadreCasteStateInfoTempDAO extends GenericDao<TdpCadreCasteStateInfoTemp,Long> {
	
	public int deleteAllRecords();
}
