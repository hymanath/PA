package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityRunningStatusDAO;
import com.itgrids.partyanalyst.model.ActivityRunningStatus;

public class ActivityRunningStatusDAO extends GenericDaoHibernate<ActivityRunningStatus, Long> implements IActivityRunningStatusDAO{

	public ActivityRunningStatusDAO() {
		super(ActivityRunningStatus.class);
		
	}

}
