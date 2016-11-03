package com.itgrids.partyanalyst.dao;

import java.util.Date;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreUserHourRegInfoTemp;

public interface ITdpCadreUserHourRegInfoTempDAO extends GenericDao<TdpCadreUserHourRegInfoTemp,Long>{
	
	public int deleteAllRecords(Date fromDate);
	public int setPrimaryKeyAutoIncrementToOne();
}
