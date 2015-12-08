package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityTeamLocationDAO;
import com.itgrids.partyanalyst.model.ActivityTeamLocation;

public class ActivityTeamLocationDAO extends GenericDaoHibernate<ActivityTeamLocation, Long> implements IActivityTeamLocationDAO{

	public ActivityTeamLocationDAO() {
		super(ActivityTeamLocation.class);
		
	}

}
