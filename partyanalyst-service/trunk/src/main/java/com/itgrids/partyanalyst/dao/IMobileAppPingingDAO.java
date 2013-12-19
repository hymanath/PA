package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileAppPinging;

public interface IMobileAppPingingDAO extends GenericDao<MobileAppPinging, Long>{
	
	public List<Object[]> getPingingTypeIdByType(Long mobileAppUserId);

}
