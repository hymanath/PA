package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;
import com.itgrids.partyanalyst.model.PartyMeetingInvitee;

public class PartyMeetingInviteeDAO extends GenericDaoHibernate<PartyMeetingInvitee,Long> implements IPartyMeetingInviteeDAO{

	public PartyMeetingInviteeDAO()
	{
		super(PartyMeetingInvitee.class);
	}
}
