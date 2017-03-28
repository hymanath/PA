package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreHourRegInfoTemp1;



public interface ITdpCadreHourRegInfoTemp1DAO extends GenericDao<TdpCadreHourRegInfoTemp1, Long>{
	
	public int deleteAllRecords();
	public int setPrimaryKeyAutoIncrementToOne();
}
