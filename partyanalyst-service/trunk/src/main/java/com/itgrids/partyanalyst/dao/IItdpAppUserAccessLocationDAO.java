package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ItdpAppUserAccessLocation;

public interface IItdpAppUserAccessLocationDAO extends GenericDao<ItdpAppUserAccessLocation, Long>{
	public Long getTabUserId(Long accussLevelId,Long accussValue);
	public Long getTabUserLocationId(Long userId);
}
