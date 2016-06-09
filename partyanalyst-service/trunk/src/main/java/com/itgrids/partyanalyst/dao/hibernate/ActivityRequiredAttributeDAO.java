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
	
	public List<Object[]> getRequiredAttributesOfScope(Long scopeId){
		
		Query query = getSession().createQuery(" SELECT model.requiredAttribute.requiredAttributeId, model.requiredAttribute.requiredAttribute " +
				" FROM ActivityRequiredAttribute model " +
				" WHERE " +
				" model.isActive = 'true'" +
				" and model.requiredAttribute.isActive='Y' " +
				" and model.activityScopeId = :scopeId ");
		
		query.setParameter("scopeId", scopeId);
		return query.list();
	}

}
