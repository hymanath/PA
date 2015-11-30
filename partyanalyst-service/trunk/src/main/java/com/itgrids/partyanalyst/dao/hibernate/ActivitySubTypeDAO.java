package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivitySubTypeDAO;
import com.itgrids.partyanalyst.model.ActivitySubType;

public class ActivitySubTypeDAO extends GenericDaoHibernate<ActivitySubType, Long> implements IActivitySubTypeDAO{

	public ActivitySubTypeDAO() {
		super(ActivitySubType.class);
		
	}

}
