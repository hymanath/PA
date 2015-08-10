package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;
import com.itgrids.partyanalyst.model.PartyMeetingMinute;

public class PartyMeetingMinuteDAO extends GenericDaoHibernate<PartyMeetingMinute,Long> implements IPartyMeetingMinuteDAO{

	public PartyMeetingMinuteDAO()
	{
		super(PartyMeetingMinute.class);
	}
}
