package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreLocationInfoTemp;

public interface ITdpCadreLocationInfoTempDAO extends GenericDao<TdpCadreLocationInfoTemp,Long>{
	
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
}
