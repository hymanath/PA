package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateParticipantRoleDAO;
import com.itgrids.partyanalyst.model.DebateParticipantRole;

public class DebateParticipantRoleDAO extends GenericDaoHibernate<DebateParticipantRole, Long> implements IDebateParticipantRoleDAO{

	public DebateParticipantRoleDAO() {
		super(DebateParticipantRole.class);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getParticepentRoles(Long debateId)
	{
		return getHibernateTemplate().find("select model.debateParticipant.candidate.candidateId , model.debateParticipant.candidate.firstname, " +
				" model.debateParticipant.party.partyId , model.debateParticipant.party.shortName , " +
				" model.debateRoles.name  from DebateParticipantRole model where model.debateParticipant.debate.debateId = ? ",debateId);
	}

	@SuppressWarnings("unchecked")
	public List<DebateParticipantRole> getDebateParticipantRoleDetails(){
		Query query = getSession().createQuery("select model from DebateParticipantRole model");
		 
		return query.list();
	 }
}
