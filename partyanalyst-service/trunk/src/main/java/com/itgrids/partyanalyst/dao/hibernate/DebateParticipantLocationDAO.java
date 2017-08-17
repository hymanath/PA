package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IDebateParticipantLocationDAO;
import com.itgrids.partyanalyst.model.DebateParticipantLocation;

public class DebateParticipantLocationDAO extends GenericDaoHibernate<DebateParticipantLocation, Long> implements IDebateParticipantLocationDAO{

	public DebateParticipantLocationDAO() {
		super(DebateParticipantLocation.class);
		
	}

}
