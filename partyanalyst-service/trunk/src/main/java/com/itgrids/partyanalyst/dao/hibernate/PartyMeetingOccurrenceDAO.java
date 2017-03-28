package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingOccurrenceDAO;
import com.itgrids.partyanalyst.model.PartyMeetingOccurrence;

public class PartyMeetingOccurrenceDAO extends GenericDaoHibernate<PartyMeetingOccurrence,Long> implements IPartyMeetingOccurrenceDAO{

	public PartyMeetingOccurrenceDAO()
	{
		super(PartyMeetingOccurrence.class);
	}
}
