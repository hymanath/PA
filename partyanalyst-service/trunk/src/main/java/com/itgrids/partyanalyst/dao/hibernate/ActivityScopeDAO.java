package com.itgrids.partyanalyst.dao.hibernate;

import java.util.LinkedList;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.model.ActivityScope;

public class ActivityScopeDAO extends GenericDaoHibernate<ActivityScope, Long> implements IActivityScopeDAO{

	public ActivityScopeDAO() {
		super(ActivityScope.class);
		
	}

	public List<Object[]> getActivitiesListByTypeAndLevel(Long activityTypeId,Long  activityLevelId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.activityScopeId,model.activity.activityName from ActivityScope model where model.activityLevelId =:activityLevelId and " +
				" model.activity.activitySubTypeId=:activityTypeId ");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("activityLevelId", activityLevelId);
		query.setParameter("activityTypeId", activityTypeId);
		
		return query.list();
	}
}
