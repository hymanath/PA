package com.itgrids.dao.impl;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILocationWiseMeesevaCentersDAO;
import com.itgrids.model.LocationWiseMeesevaCenters;

@Repository
public class LocationWiseMeesevaCentersDAO extends GenericDaoHibernate<LocationWiseMeesevaCenters, Long> implements ILocationWiseMeesevaCentersDAO{

	public LocationWiseMeesevaCentersDAO() {
		super(LocationWiseMeesevaCenters.class);
	}
	
	public List<Object[]> getStateMeesevaCentres(){
		Query query = getSession().createQuery("select model.totalCenters,"
				+ "model.establishedSince2014,"
				+ "model.establishedLastYear,"
				+ "model.establishedThisYear,"
				+ "model.establishedLastMonth"
				+ " from LocationWiseMeesevaCenters model"
				+ " where model.isDeleted = 'N'"
				+ " and model.locationScope.locationScopeId = 2");
		
		return query.list();
	}
	
	public List<Object[]> getLocationWiseMeesevaCentres(){
		Query query = getSession().createQuery("select distinct model.address.district.districtId,"
				+ "model.address.district.districtName,"
				+ "model.totalCenters,"
				+ "model.establishedSince2014,"
				+ "model.establishedLastYear,"
				+ "model.establishedThisYear,"
				+ "model.establishedLastMonth"
				+ " from LocationWiseMeesevaCenters model"
				+ " where model.isDeleted = 'N'"
				+ " and model.locationScope.locationScopeId = 3");
		
		return query.list();
	}

}
