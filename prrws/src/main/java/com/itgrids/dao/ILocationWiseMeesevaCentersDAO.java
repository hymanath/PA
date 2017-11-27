package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LocationWiseMeesevaCenters;

public interface ILocationWiseMeesevaCentersDAO extends GenericDao<LocationWiseMeesevaCenters, Long>{
	
	public List<Object[]> getStateMeesevaCentres();
	public List<Object[]> getLocationWiseMeesevaCentres();

}
