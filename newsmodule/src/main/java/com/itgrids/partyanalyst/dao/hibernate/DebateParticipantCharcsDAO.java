package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
import com.itgrids.partyanalyst.model.DebateParticipantCharcs;

public class DebateParticipantCharcsDAO extends GenericDaoHibernate<DebateParticipantCharcs, Long> implements IDebateParticipantCharcsDAO{

	public DebateParticipantCharcsDAO() {
		super(DebateParticipantCharcs.class);
		// TODO Auto-generated constructor stub
	}
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateCharcsDetails(Long debateId)
	{
		return getHibernateTemplate().find("select model.debateParticipant.candidate.candidateId ,model.debateParticipant.candidate.lastname ," +
				" model.debateParticipant.party.partyId,model.debateParticipant.party.shortName ," +
				" model.characteristics.name,model.scale from  DebateParticipantCharcs model where " +
				" model.debateParticipant.debate.debateId = ? ",debateId);
	}
}
