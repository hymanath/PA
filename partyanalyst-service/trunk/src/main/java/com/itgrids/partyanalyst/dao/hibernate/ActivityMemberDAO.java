package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityMemberDAO;
import com.itgrids.partyanalyst.model.ActivityMember;

public class ActivityMemberDAO extends GenericDaoHibernate<ActivityMember,Long> implements IActivityMemberDAO {
	
	public ActivityMemberDAO() {
		super(ActivityMember.class);
	}
	
	

}
