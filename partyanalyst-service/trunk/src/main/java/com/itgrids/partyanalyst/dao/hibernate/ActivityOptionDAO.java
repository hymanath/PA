package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityOptionDAO;
import com.itgrids.partyanalyst.model.ActivityOption;

public class ActivityOptionDAO extends GenericDaoHibernate<ActivityOption, Long> implements IActivityOptionDAO{

	public ActivityOptionDAO() {
		super(ActivityOption.class);
		
	}

}
