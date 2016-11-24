package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreGenderInfo;

public interface ITdpCadreGenderInfoDAO extends GenericDao<TdpCadreGenderInfo,Long>{
	
	public int pushCadreCountsLocationWiseByGender();
}
