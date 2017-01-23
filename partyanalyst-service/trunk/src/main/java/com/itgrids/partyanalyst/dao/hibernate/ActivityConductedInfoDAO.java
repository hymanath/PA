package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.ActivityConductedInfo;

public class ActivityConductedInfoDAO extends GenericDaoHibernate<ActivityConductedInfo, Long>{

	public ActivityConductedInfoDAO() {
		super(ActivityConductedInfo.class);

	}

}
