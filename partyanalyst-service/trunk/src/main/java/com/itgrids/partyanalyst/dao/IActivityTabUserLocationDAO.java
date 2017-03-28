package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ActivityTabUserLocation;

public interface IActivityTabUserLocationDAO extends GenericDao<ActivityTabUserLocation, Long>{
	
	public List<Object[]> getUserActivityDetailsByUserId(Long userId);

}
