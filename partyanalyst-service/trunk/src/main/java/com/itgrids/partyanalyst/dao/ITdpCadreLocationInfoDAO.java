package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreLocationInfo;

public interface ITdpCadreLocationInfoDAO extends GenericDao<TdpCadreLocationInfo,Long>{
	
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
}
