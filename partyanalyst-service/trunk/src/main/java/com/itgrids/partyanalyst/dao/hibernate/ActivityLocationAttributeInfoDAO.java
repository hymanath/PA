package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.ActivityLocationAttributeInfo;

import com.itgrids.partyanalyst.dao.IActivityLocationAttributeInfoDAO;

public class ActivityLocationAttributeInfoDAO extends GenericDaoHibernate<ActivityLocationAttributeInfo, Long> implements IActivityLocationAttributeInfoDAO{

	public ActivityLocationAttributeInfoDAO() {
		super(ActivityLocationAttributeInfo.class);
		
	}

}
