package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityScopeDAO;
import com.itgrids.partyanalyst.model.ActivityScope;

public class ActivityScopeDAO extends GenericDaoHibernate<ActivityScope, Long> implements IActivityScopeDAO{

	public ActivityScopeDAO() {
		super(ActivityScope.class);
		
	}

}
