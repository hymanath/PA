package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityScopeRequiredAttributesDAO;
import com.itgrids.partyanalyst.model.ActivityScopeRequiredAttributes;

public class ActivityScopeRequiredAttributesDAO extends GenericDaoHibernate<ActivityScopeRequiredAttributes, Long> implements IActivityScopeRequiredAttributesDAO{

	public ActivityScopeRequiredAttributesDAO() {
		super(ActivityScopeRequiredAttributes.class);
		
	}

}
