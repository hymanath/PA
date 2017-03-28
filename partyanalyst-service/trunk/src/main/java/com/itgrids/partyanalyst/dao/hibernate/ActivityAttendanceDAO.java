package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.impl.IActivityAttendanceDAO;
import com.itgrids.partyanalyst.dao.impl.IActivityInviteeDAO;
import com.itgrids.partyanalyst.model.ActivityAttendance;
import com.itgrids.partyanalyst.model.ActivityInvitee;

public class ActivityAttendanceDAO extends
		GenericDaoHibernate<ActivityAttendance, Long> implements
		IActivityAttendanceDAO {
	public ActivityAttendanceDAO() {
		super(ActivityAttendance.class);

	}

	public List<Object[]> getActivityScopeAndLevels(Long tdpCadreId,Long activityLevelId) {
		
		Query query = getSession().createQuery(" select model.activityScope.activityScopeId, model.activityDate, model.day, model.isAttended,model.activityLocationInfoId," +
				" model.activityLocationInfo.locationLevel,model.activityLocationInfo.locationValue   " +
				" from ActivityAttendance model where model.tdpCadre.tdpCadreId = :tdpCadreId  and model.activityScope.isDeleted = 'N' and " +
				"  model.activityScope.activityLevelId=:activityLevelId  and  model.activityScope.activity.isActive='Y'  ");
             query.setParameter("tdpCadreId", tdpCadreId);
             query.setParameter("activityLevelId", activityLevelId);
         return query.list();
	}
	
	public List<Object[]> getCadreAttendanceDetls(Long tdpCadreId) {
		
		Query query = getSession().createQuery(" select model.activityScope.activityLevelId, model.isAttended ,count(distinct model.activityScopeId)  " +
				" from ActivityAttendance model where model.tdpCadre.tdpCadreId = :tdpCadreId  and model.activityScope.isDeleted = 'N' and  model.activityScope.activity.isActive='Y' " +
				" group by model.activityScope.activityLevelId,model.isAttended order by model.activityScope.activityLevelId asc");
             query.setParameter("tdpCadreId", tdpCadreId);
         return query.list();
	}

	public List<Object[]> getCanditeActivtyAttendanceLocationsDtls(Long tdpCadreId,Long activityLevelId,Long activityScopeId) {
		
		Query query = getSession().createQuery(" select model.activityScope.activity.activityName, model.activityScope.activityLevel.level , " +
				" model.activityLocationInfo.locationLevel, model.activityLocationInfo.locationValue,model.activityLocationInfoId , date(model.activityDate),model.activityScope.activityLevelId  " +
				" from ActivityAttendance model where model.tdpCadre.tdpCadreId = :tdpCadreId  and model.activityScope.isDeleted = 'N' and  model.activityScope.activity.isActive='Y' " +
				" and model.isAttended = 'YES' and  model.activityScope.activityLevelId = :activityLevelId  and model.activityScope.activityScopeId =:activityScopeId order by model.activityScope.activityLevelId asc");
             query.setParameter("tdpCadreId", tdpCadreId);
             query.setParameter("activityLevelId", activityLevelId);
             query.setParameter("activityScopeId", activityScopeId);
         return query.list();
	}
	
}
