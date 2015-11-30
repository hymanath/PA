package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityDAO;
import com.itgrids.partyanalyst.model.Activity;

public class ActivityDAO extends GenericDaoHibernate<Activity, Long> implements IActivityDAO{

	public ActivityDAO() {
		super(Activity.class);
		
	}

}
