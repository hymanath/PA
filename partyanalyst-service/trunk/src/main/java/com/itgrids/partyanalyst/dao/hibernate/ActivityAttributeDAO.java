package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.ActivityAttribute;

import com.itgrids.partyanalyst.dao.IActivityAttributeDAO;

public class ActivityAttributeDAO extends GenericDaoHibernate<ActivityAttribute, Long> implements IActivityAttributeDAO{

	public ActivityAttributeDAO() {
		super(ActivityAttribute.class);
		
	}

}
