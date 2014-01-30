package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateParticipantExceptedRoleDAO;
import com.itgrids.partyanalyst.model.DebateParticipantExpectedRole;

public class DebateParticipantExceptedRoleDAO extends GenericDaoHibernate<DebateParticipantExpectedRole, Long> implements IDebateParticipantExceptedRoleDAO{

	public DebateParticipantExceptedRoleDAO() {
		super(DebateParticipantExpectedRole.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getPaticepentExpRoles(Long debateId)
	{
		return getHibernateTemplate().find("select model.debateRoles.name,model.debateParticipant.candidate.firstname ,model.debateParticipant.candidate.candidateId" +
				"  from DebateParticipantExpectedRole model " +
				" where model.debateParticipant.debate.debateId = ? ",debateId);
	}
}
