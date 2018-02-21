package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IMobileAppUserLocationDAO;
import com.itgrids.model.MobileAppUserLocation;

@Repository
public class MobileAppUserLocationDAO extends GenericDaoHibernate<MobileAppUserLocation, Long> implements IMobileAppUserLocationDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public MobileAppUserLocationDAO() {
		super(MobileAppUserLocation.class);
 	}
	
	public List<Object[]> getMobileAppuserLocations(Long userId){
		//0-regionScopesId,1-locationScope,2-locationValue
		Query query = getSession().createQuery(" select model.locationScope.regionScopesId,model.locationScope.scope,model.locationValue "
				+ " from MobileAppUserLocation model "
				+ " where model.mobileAppUserId=:userId and model.isDeleted='N' ");
		query.setParameter("userId", userId);
		return query.list();
	}
}
