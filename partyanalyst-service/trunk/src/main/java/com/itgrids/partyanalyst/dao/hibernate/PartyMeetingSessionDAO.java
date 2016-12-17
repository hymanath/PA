package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingSessionDAO;
import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.PartyMeetingSession;

public class PartyMeetingSessionDAO extends GenericDaoHibernate<PartyMeetingSession, Long> implements IPartyMeetingSessionDAO{

	public PartyMeetingSessionDAO() {
		super(PartyMeetingSession.class);
		
	}

}
