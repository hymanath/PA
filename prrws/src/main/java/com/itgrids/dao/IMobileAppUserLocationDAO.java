package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.hibernate.Query;

import com.itgrids.model.MobileAppUserLocation;

public interface IMobileAppUserLocationDAO extends GenericDao<MobileAppUserLocation,Long>{
	public List<Object[]> getMobileAppuserLocations(Long userId);
	public List<Object[]> getSubUserDetails(Long requiredUserTypeId,Long parentLocationScopeId,List<Long> parentLocationValues,Long workTypeId);
	public List<Object[]> getAllEngineers(Long userTypeId);
}
