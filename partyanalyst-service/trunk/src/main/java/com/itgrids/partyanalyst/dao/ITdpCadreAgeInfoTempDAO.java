package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreAgeInfoTemp;

public interface ITdpCadreAgeInfoTempDAO extends GenericDao<TdpCadreAgeInfoTemp,Long>{
	
	public int deleteAllRecords();
}
