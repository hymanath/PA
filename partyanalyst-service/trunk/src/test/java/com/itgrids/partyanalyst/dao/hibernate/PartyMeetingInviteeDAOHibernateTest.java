package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingInviteeDAO;

public class PartyMeetingInviteeDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingInviteeDAO partyMeetingInviteeDAO;

	public void setPartyMeetingInviteeDAO(
			IPartyMeetingInviteeDAO partyMeetingInviteeDAO) {
		this.partyMeetingInviteeDAO = partyMeetingInviteeDAO;
	}
	
	public void test()
	{
		partyMeetingInviteeDAO.getAll();
	}
}
