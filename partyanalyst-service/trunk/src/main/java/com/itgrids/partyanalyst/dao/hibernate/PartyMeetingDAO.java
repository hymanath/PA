package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.model.PartyMeeting;


public class PartyMeetingDAO extends GenericDaoHibernate<PartyMeeting,Long> implements IPartyMeetingDAO{

	public PartyMeetingDAO()
	{
		super(PartyMeeting.class);
	}
}
