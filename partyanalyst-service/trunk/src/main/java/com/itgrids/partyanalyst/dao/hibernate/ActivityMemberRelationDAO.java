package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityMemberRelationDAO;
import com.itgrids.partyanalyst.model.ActivityMemberRelation;

public class ActivityMemberRelationDAO extends GenericDaoHibernate<ActivityMemberRelation,Long> implements IActivityMemberRelationDAO {
	
	public ActivityMemberRelationDAO() {
		super(ActivityMemberRelation.class);
	}
	
	

}
