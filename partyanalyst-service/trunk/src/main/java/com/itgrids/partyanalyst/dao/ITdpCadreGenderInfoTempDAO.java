package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreGenderInfoTemp;

public interface ITdpCadreGenderInfoTempDAO extends GenericDao<TdpCadreGenderInfoTemp,Long> {
	
	public int deleteAllRecords();
	
}
