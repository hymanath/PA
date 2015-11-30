package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityTypeDAO;
import com.itgrids.partyanalyst.model.ActivityType;

public class ActivityTypeDAO extends GenericDaoHibernate<ActivityType, Long> implements IActivityTypeDAO{

	public ActivityTypeDAO() {
		super(ActivityType.class);
		
	}

}
