package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.model.ActivityMemberAccessLevel;

public class ActivityMemberAccessLevelDAO extends GenericDaoHibernate<ActivityMemberAccessLevel,Long> implements IActivityMemberAccessLevelDAO {
	
	public ActivityMemberAccessLevelDAO() {
		super(ActivityMemberAccessLevel.class);
	}
	
	

}
