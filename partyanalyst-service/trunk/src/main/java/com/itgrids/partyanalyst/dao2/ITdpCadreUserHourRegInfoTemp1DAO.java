package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreUserHourRegInfoTemp1;

public interface ITdpCadreUserHourRegInfoTemp1DAO extends GenericDao<TdpCadreUserHourRegInfoTemp1,Long>{
	
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
	
}
