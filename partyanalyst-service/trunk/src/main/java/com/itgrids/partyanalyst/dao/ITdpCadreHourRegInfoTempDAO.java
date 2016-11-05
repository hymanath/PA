package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreHourRegInfoTemp;

public interface ITdpCadreHourRegInfoTempDAO extends GenericDao<TdpCadreHourRegInfoTemp, Long>{
	
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
}
