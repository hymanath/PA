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
		
		Query query = getSession().createQuery(" select model.activityTabUserLocationId," +//0
				" model.activityTabUser.activityTabUserId," +//1
				" model.activityLocationInfo.activityScope.activityScopeId, " +//2
				" model.activityLocationInfo.activityScope.activity.activityId," +//3
				" model.activityLocationInfo.activityScope.activity.activityName," +//4
				" model.activityLocationInfo.locationLevel," +//5
				" model.activityLocationInfo.locationValue, " +//6
				" model.activityLocationInfo.activityScope.activityLevel.level," +//7
				" model.activityLocationInfo.activityScope.activityLevel.activityLevelId," +//8
				" model.activityLocationInfo.activityScope.activity.startDate, " +//9
				" model.activityLocationInfo.activityScope.activity.endDate, " +//10
				" model.activityLocationInfo.activityLocationInfoId, " +//11
				" model.activityLocationInfo.plannedDate, " +//12
				" model.activityLocationInfo.conductedDate, " +//13
				" model.activityLocationInfo.plannedEndDate, " +//14
				" model.activityLocationInfo.conductedEndDate, " +//15
				" model.activityLocationInfo.activityScope.maxFilesCount " +//16
				" from ActivityTabUserLocation model where model.activityTabUser.activityTabUserId = :userId and model.isActive = 'Y' ");
		
		query.setParameter("userId", userId);
		
		return query.list();
	}

}
