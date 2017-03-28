package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IRequiredAttributeDAO;
import com.itgrids.partyanalyst.model.RequiredAttribute;


public class RequiredAttributeDAO extends GenericDaoHibernate<RequiredAttribute, Long> implements IRequiredAttributeDAO{

	public RequiredAttributeDAO() {
		super(RequiredAttribute.class);
		
	}
	
	public List<Object[]> getAttributesTypes(Long activityScopeId){
		Query query = getSession().createQuery("select distinct model.requiredAttribute.requiredAttributeId, model.requiredAttribute.requiredAttribute " +
				"from ActivityRequiredAttribute model, RequiredAttribute ra " +
				" where " +
				"model.requiredAttributeId=ra.requiredAttributeId " +				
				"and model.activityScopeId =:activityScopeId " +
				"and model.requiredAttribute.isActive = 'Y'");
	    query.setParameter("activityScopeId", activityScopeId);
	    
	    return query.list();
	  }

}
