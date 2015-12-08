package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityTeamMemberDAO;
import com.itgrids.partyanalyst.model.ActivityTeamMember;

public class ActivityTeamMemberDAO  extends GenericDaoHibernate<ActivityTeamMember, Long> implements IActivityTeamMemberDAO{

	public ActivityTeamMemberDAO() {
		super(ActivityTeamMember.class);
		
	}

}
