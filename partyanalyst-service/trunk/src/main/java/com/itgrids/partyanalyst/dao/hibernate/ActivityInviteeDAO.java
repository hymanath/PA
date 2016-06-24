package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.impl.IActivityInviteeDAO;
import com.itgrids.partyanalyst.model.ActivityInvitee;

public class ActivityInviteeDAO extends
		GenericDaoHibernate<ActivityInvitee, Long> implements
		IActivityInviteeDAO {
	public ActivityInviteeDAO() {
		super(ActivityInvitee.class);

	}
	public List<Object[]> getActivityScopeAndLevels(Long tdpCadreId,Long activityLevelId){
		Query query = getSession().createQuery(" select distinct model.activityScope.activityScopeId,count(distinct model.activityDate)," +
				" model.activityDate, model.day " +
				" from ActivityInvitee model where model.tdpCadre.tdpCadreId = :tdpCadreId " +
				" and model.activityScope.isDeleted = 'N' and  model.activityScope.activityLevelId=:activityLevelId  group by model.tdpCadre.tdpCadreId,model.activityScope.activityScopeId ");
				query.setParameter("tdpCadreId", tdpCadreId);
				query.setParameter("activityLevelId", activityLevelId);
				return query.list();
	}
	
}
