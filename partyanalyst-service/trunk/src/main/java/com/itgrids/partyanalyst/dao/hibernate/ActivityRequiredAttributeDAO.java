package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityRequiredAttributeDAO;
import com.itgrids.partyanalyst.model.ActivityRequiredAttribute;

public class ActivityRequiredAttributeDAO extends GenericDaoHibernate<ActivityRequiredAttribute, Long> implements IActivityRequiredAttributeDAO{

	public ActivityRequiredAttributeDAO() {
		super(ActivityRequiredAttribute.class);
	}

}
