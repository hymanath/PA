package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreLocationInfoTemp1;

public interface ITdpCadreLocationInfoTemp1DAO extends GenericDao<TdpCadreLocationInfoTemp1, Long>{
	
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
}
