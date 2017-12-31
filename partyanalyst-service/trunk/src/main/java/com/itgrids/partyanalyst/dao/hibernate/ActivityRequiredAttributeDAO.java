package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityRequiredAttributeDAO;
import com.itgrids.partyanalyst.model.ActivityRequiredAttribute;

public class ActivityRequiredAttributeDAO extends GenericDaoHibernate<ActivityRequiredAttribute, Long> implements IActivityRequiredAttributeDAO{

	public ActivityRequiredAttributeDAO() {
		super(ActivityRequiredAttribute.class);
	}
    
	public List<Long> getActivityScopeAttribue(Long activityScopeId) {
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append("select  model.requiredAttributeId from ActivityRequiredAttribute " +
		  		" model where model.isActive='true' and model.activityScopeId=:activityScopeId");
		  Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("activityScopeId", activityScopeId);
		  return query.list();
	}
}
