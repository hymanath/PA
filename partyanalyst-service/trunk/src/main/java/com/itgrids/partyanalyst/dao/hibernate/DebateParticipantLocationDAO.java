package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateParticipantLocationDAO;
import com.itgrids.partyanalyst.model.DebateParticipantLocation;

public class DebateParticipantLocationDAO extends GenericDaoHibernate<DebateParticipantLocation, Long> implements IDebateParticipantLocationDAO{

	public DebateParticipantLocationDAO() {
		super(DebateParticipantLocation.class);
		
	}
	public int removeDebateParticipantId(Integer debateParticipantId)
	{
		Query query = getSession().createQuery("delete from DebateParticipantLocation model where model.debateParticipantId = :debateParticipantId ");
		query.setParameter("debateParticipantId", debateParticipantId);
		int result = query.executeUpdate();
		return result;
	}
}
