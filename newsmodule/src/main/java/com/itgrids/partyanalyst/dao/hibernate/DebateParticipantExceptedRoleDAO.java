package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateParticipantExceptedRoleDAO;
import com.itgrids.partyanalyst.model.DebateParticipantExpectedRole;

public class DebateParticipantExceptedRoleDAO extends GenericDaoHibernate<DebateParticipantExpectedRole, Long> implements IDebateParticipantExceptedRoleDAO{

	public DebateParticipantExceptedRoleDAO() {
		super(DebateParticipantExpectedRole.class);
		// TODO Auto-generated constructor stub
	}

}
