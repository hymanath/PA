package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.ActivityLevel;

import com.itgrids.partyanalyst.dao.IActivityLevelDAO;

public class ActivityLevelDAO extends GenericDaoHibernate<ActivityLevel, Long> implements IActivityLevelDAO{

	public ActivityLevelDAO() {
		super(ActivityLevel.class);
		
	}

}
