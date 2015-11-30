package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityLocationInfoDAO;
import com.itgrids.partyanalyst.model.ActivityLocationInfo;

public class ActivityLocationInfoDAO extends GenericDaoHibernate<ActivityLocationInfo, Long> implements IActivityLocationInfoDAO{

	public ActivityLocationInfoDAO() {
		super(ActivityLocationInfo.class);
		
	}

}
