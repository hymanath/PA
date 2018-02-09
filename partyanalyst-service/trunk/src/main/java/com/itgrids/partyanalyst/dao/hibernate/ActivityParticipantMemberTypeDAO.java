package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityParticipantMemberTypeDAO;
import com.itgrids.partyanalyst.model.ActivityParticipantMemberType;

public class ActivityParticipantMemberTypeDAO extends GenericDaoHibernate<ActivityParticipantMemberType, Long> implements
		IActivityParticipantMemberTypeDAO {

	public ActivityParticipantMemberTypeDAO() {
		super(ActivityParticipantMemberType.class);
		// TODO Auto-generated constructor stub
	}
	
}
