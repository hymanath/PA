package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreDateWiseInfoTemp;

public interface ITdpCadreDateWiseInfoTempDAO extends GenericDao<TdpCadreDateWiseInfoTemp, Long>{
	
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
}
