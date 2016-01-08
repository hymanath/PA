package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
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
				" model.activity.activitySubTypeId=:activityTypeId and model.activity.isActive = 'Y' order by model.activity.activityId asc");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("activityLevelId", activityLevelId);
		query.setParameter("activityTypeId", activityTypeId);
		
		return query.list();
	}
	public Object[] getDatesForActivityByActivityScopeId(Long activityScopeId){ 
		
		Query query=getSession().createQuery(" select date(model.activity.startDate),date(model.activity.endDate) from ActivityScope model where model.activityScopeId =:activityScopeId ");
		query.setParameter("activityScopeId", activityScopeId);
		return (Object[])query.uniqueResult();
	}
	public Long getNoOFTimesOfAnActivity(Long activityScopeId){
		Query query=getSession().createQuery(" select model.activity.noOfTimes from ActivityScope model where model.activityScopeId =:activityScopeId");
		query.setParameter("activityScopeId", activityScopeId);
		return (Long)query.uniqueResult();
	}
	
	public Date getActivityStartDateByActivityScopeId(Long activityScopeId)
	{
		Query query = getSession().createQuery(" select date(model.activity.startDate) from ActivityScope model where model.activityScopeId =:activityScopeId ");
		query.setParameter("activityScopeId", activityScopeId);
		return (Date) query.uniqueResult();
	}
}
