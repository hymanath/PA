package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.MobileAppUserLocation;

public interface IMobileAppUserLocationDAO extends GenericDao<MobileAppUserLocation,Long>{
	public List<Object[]> getMobileAppuserLocations(Long userId);
}
