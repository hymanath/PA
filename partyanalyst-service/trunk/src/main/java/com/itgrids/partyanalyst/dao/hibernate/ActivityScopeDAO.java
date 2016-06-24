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
	public List<Object[]> getActivityScopeIdByActivityAndLevelId(Long activityId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.activityScopeId,model.activityLevelId from ActivityScope model where " +
				" model.activity.activityId=:activityId and model.isDeleted = 'N' order by model.activityScopeId asc");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("activityId", activityId);
		
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
	
	public Object[] getDatesForActivityScopeId(Long activityScopeId){ 
		Query query=getSession().createQuery(" select date(model.startDate)," +
				" date(model.endDate) " +
				" from ActivityScope model " +
				" where model.activityScopeId =:activityScopeId ");
		query.setParameter("activityScopeId", activityScopeId);
		return (Object[])query.uniqueResult();
	}
	
	public List<Object[]> getActivityLevelsDetails(Long requiredAtrrIds){
		
		/*Query query = getSession().createQuery(" select model.activityLevelId," +
							" count(model.activityLevelId) " +
							" from ActivityScope model " +
							" where model.isDeleted = 'N' " +
							" group by model.activityLevel.activityLevelId ");*/
		Query query = getSession().createQuery(" select model.activityLevelId," +
				" count(model.activityLevelId) " +
				" from ActivityScope model,ActivityScopeRequiredAttributes model1 " +
				" where model.activityScopeId = model1.activityScope.activityScopeId " +
				" and model1.requiredAttribute.requiredAttributeId = :requiredAtrrIds " +
				" and model1.isActive = 'Y' and model1.isActive = 'Y' and model.isDeleted = 'N' " +
				" group by model.activityLevel.activityLevelId ");
		query.setParameter("requiredAtrrIds", requiredAtrrIds);
		return query.list();
	}
	
	public List<Object[]> getActivitiesListByLevelId(Long levelId,Long requiredAtrrIds){
		/*Query query = getSession().createQuery(" select model.activityScopeId," +
							" model.activity.activityId, " +
							" model.activity.activityName, " +
							" model.activityLevelId " +
							" from ActivityScope model " +
							" where model.activityLevel.activityLevelId = :levelId " +
							" and model.isDeleted = 'N' and model.activity.isActive = 'Y' ");*/
		Query query = getSession().createQuery(" select model.activityScopeId," +
				" model.activity.activityId, " +
				" model.activity.activityName, " +
				" model.activityLevelId " +
				" from ActivityScope model,ActivityScopeRequiredAttributes model1 " +
				" where model.activityScopeId = model1.activityScope.activityScopeId " +
				" and model1.requiredAttribute.requiredAttributeId = :requiredAtrrIds " +
				" and model.activityLevel.activityLevelId = :levelId " +
				" and model.isDeleted = 'N' and model.activity.isActive = 'Y' " +
				" and model1.isActive = 'Y' and model1.isActive = 'Y' ");
		query.setParameter("requiredAtrrIds", requiredAtrrIds);
		query.setParameter("levelId", levelId);
		return query.list();
	}
	
	public List<Object[]> getActivityLevelAndScopeIdByActivity(Long activityId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.activityScopeId,model.activityLevel.activityLevelId,model.activityLevel.level from ActivityScope model " +
				" where " +
				" model.activity.activityId=:activityId and model.isDeleted = 'N' order by model.activityScopeId asc");
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("activityId", activityId);
		
		return query.list();
	}
	public Object[] getRequiredDatesOfScope(Long scopeId){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" SELECT date(model.startDate),date(model.endDate) " +
				" FROM ActivityScope model " +
				" WHERE model.isDeleted = 'N' " );
		if(scopeId !=null && scopeId>0){
			queryStr.append(" and model.activityScopeId = :scopeId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());
		if(scopeId !=null && scopeId>0){
			query.setParameter("scopeId", scopeId);
		}
		
		return (Object[]) query.uniqueResult();
		
	}
	
	public Long getNoOfTimesCountForActivityScope(Long scopeId){
		Query query = getSession().createQuery("select model.noOfTimes" +
											" from ActivityScope model" +
											" where model.activityScopeId = :scopeId" +
											" and model.isDeleted = 'N'");
		
		query.setParameter("scopeId", scopeId);
		return (Long) query.uniqueResult();
	}
}
