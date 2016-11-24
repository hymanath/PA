package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreAgeInfo;

public interface ITdpCadreAgeInfoDAO extends GenericDao<TdpCadreAgeInfo,Long>{
	
	public int pushCadreCountsLocationWiseByAge();
}
