package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.ActivityInfoFile;

import com.itgrids.partyanalyst.dao.IActivityInfoFileDAO;

public class ActivityInfoFileDAO extends GenericDaoHibernate<ActivityInfoFile, Long> implements IActivityInfoFileDAO{

	public ActivityInfoFileDAO() {
		super(ActivityInfoFile.class);
		
	}

}
