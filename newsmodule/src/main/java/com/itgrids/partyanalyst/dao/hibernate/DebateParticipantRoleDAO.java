package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateParticipantRoleDAO;
import com.itgrids.partyanalyst.model.DebateParticipantRole;

public class DebateParticipantRoleDAO extends GenericDaoHibernate<DebateParticipantRole, Long> implements IDebateParticipantRoleDAO{

	public DebateParticipantRoleDAO() {
		super(DebateParticipantRole.class);
		// TODO Auto-generated constructor stub
	}

}
