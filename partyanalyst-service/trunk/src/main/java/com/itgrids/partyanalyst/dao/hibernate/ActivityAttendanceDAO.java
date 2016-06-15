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

	public List<Object[]> getActivityScopeAndLevels(Long tdpCadreId){
		Query query = getSession().createQuery(" select distinct model.activityScope.activityScopeId, count(distinct model.activityDate)," +
				" model.activityDate, model.day " +
				" from ActivityAttendance model where model.tdpCadre.tdpCadreId = :tdpCadreId " +
				" and model.activityScope.isDeleted = 'N' group by model.tdpCadre.tdpCadreId,model.activityScope.activityScopeId ");
query.setParameter("tdpCadreId", tdpCadreId);
return query.list();
	}
}
