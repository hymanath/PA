package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityScopeRequiredAttributesDAO;
import com.itgrids.partyanalyst.model.ActivityScopeRequiredAttributes;

public class ActivityScopeRequiredAttributesDAO extends GenericDaoHibernate<ActivityScopeRequiredAttributes, Long> implements IActivityScopeRequiredAttributesDAO{

	public ActivityScopeRequiredAttributesDAO() {
		super(ActivityScopeRequiredAttributes.class);
		
	}
	
	public List<Object[]> getActivityRequiredAttributes(List<Long> scopeIds){
		
		Query query = getSession().createQuery(" select model.requiredAttribute.requiredAttributeId," +
							" model.requiredAttribute.requiredAttribute," +
							" model.activityScope.activityScopeId " +
							" from ActivityScopeRequiredAttributes model " +
							" where model.activityScope.activityScopeId in (:scopeIds) " +
							" and model.requiredAttribute.isActive = 'Y' and model.isActive = 'Y' ");
		query.setParameterList("scopeIds", scopeIds);
		return query.list();
	}
	
	public List<Object[]> getActivityRequiredAttributesForScope(Long scopeId){
		
		Query query = getSession().createQuery(" select model.requiredAttribute.requiredAttributeId," +
							" model.requiredAttribute.requiredAttribute " +
							" from ActivityScopeRequiredAttributes model where model.activityScope.activityScopeId = :scopeId " +
							" and model.requiredAttribute.isActive = 'Y' and model.isActive = 'Y' ");
		query.setParameter("scopeId", scopeId);
		return query.list();
	}
public List<Object[]> getScopeIds(){
		
		Query query = getSession().createQuery(" select distinct model.activityScope.activityScopeId,model.activityScope.activity.activityName, model.activityScope.activityLevel.activityLevelId,model.activityScope.activityLevel.level,model.activityScope.activity.activityId " +
							" from ActivityScopeRequiredAttributes model where model.requiredAttribute.requiredAttributeId = 2 " +
							" and model.requiredAttribute.isActive = 'Y' and model.isActive = 'Y' ");
		return query.list();
	}
}
