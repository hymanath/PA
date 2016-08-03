package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessTypeDAO;
import com.itgrids.partyanalyst.model.ActivityMemberAccessType;

public class ActivityMemberAccessTypeDAO extends GenericDaoHibernate<ActivityMemberAccessType,Long> implements IActivityMemberAccessTypeDAO {
	
	public ActivityMemberAccessTypeDAO() {
		super(ActivityMemberAccessType.class);
	}
	
	

}
