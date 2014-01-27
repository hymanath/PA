package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateParticipantDAO;
import com.itgrids.partyanalyst.model.DebateParticipant;

public class DebateParticipantDAO extends GenericDaoHibernate<DebateParticipant, Long> implements IDebateParticipantDAO{

	public DebateParticipantDAO() {
		super(DebateParticipant.class);
		// TODO Auto-generated constructor stub
	}

	public List<Object[]> getDebatePaticepantsAndSummeryDetails(Long debateId)
	{
		Query query = getSession().createQuery("select model.candidate.lastname," +
				" model.party.shortName,model.summary ,model.candidate.candidateId , model.party.partyId from DebateParticipant model " +
				" where model.debate.debateId = :debateId");
		query.setParameter("debateId", debateId);
		return query.list();
	}
}
