package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MobileAppUserAccessLocation;

public interface IMobileAppUserAccessLocationDAO extends GenericDao<MobileAppUserAccessLocation, Long>{
	public List<MobileAppUserAccessLocation> getMobileAppUserAccessLocations(Long mobileAppUserId);
}
