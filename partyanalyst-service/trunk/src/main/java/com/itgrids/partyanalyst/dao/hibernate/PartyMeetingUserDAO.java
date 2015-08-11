package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingUserDAO;
import com.itgrids.partyanalyst.model.PartyMeetingUser;

public class PartyMeetingUserDAO extends GenericDaoHibernate<PartyMeetingUser,Long> implements IPartyMeetingUserDAO{

	public PartyMeetingUserDAO()
	{
		super(PartyMeetingUser.class);
	}
}
