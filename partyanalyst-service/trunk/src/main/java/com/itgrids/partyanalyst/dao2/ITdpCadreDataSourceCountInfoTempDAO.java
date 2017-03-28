package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreDataSourceCountInfoTemp;

public interface ITdpCadreDataSourceCountInfoTempDAO extends
		GenericDao<TdpCadreDataSourceCountInfoTemp, Long> {
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
	

}
