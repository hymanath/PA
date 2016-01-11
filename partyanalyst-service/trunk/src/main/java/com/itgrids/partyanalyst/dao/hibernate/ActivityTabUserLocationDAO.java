package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityTabUserLocationDAO;
import com.itgrids.partyanalyst.model.ActivityTabUserLocation;

public class ActivityTabUserLocationDAO extends GenericDaoHibernate<ActivityTabUserLocation, Long> implements IActivityTabUserLocationDAO{

	public ActivityTabUserLocationDAO() {
		super(ActivityTabUserLocation.class);
		
	}
	
	public List<Object[]> getUserActivityDetailsByUserId(Long userId){
		
		Query query = getSession().createQuery(" select model.activityTabUserLocationId," +
				" model.activityTabUser.activityTabUserId," +
				" model.activityLocationInfo.activityScope.activityScopeId, " +
				" model.activityLocationInfo.activityScope.activity.activityName," +
				" model.activityLocationInfo.activityScope.activityLevel.activityLevelId," +
				" model.activityLocationInfo.locationValue, " +
				" model.activityLocationInfo.activityScope.activityLevel.level," +
				" model.activityLocationInfo.activityScope.activity.startDate, " +
				" model.activityLocationInfo.activityScope.activity.endDate " +
				" from ActivityTabUserLocation model where model.activityTabUser.activityTabUserId = :userId and model.isActive = 'Y' ");
		
		query.setParameter("userId", userId);
		
		return query.list();
	}

}
