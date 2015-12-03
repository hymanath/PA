package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityOptionTypeDAO;
import com.itgrids.partyanalyst.model.ActivityOptionType;

public class ActivityOptionTypeDAO extends GenericDaoHibernate<ActivityOptionType, Long> implements IActivityOptionTypeDAO{

	public ActivityOptionTypeDAO() {
		super(ActivityOptionType.class);
		
	}

}
