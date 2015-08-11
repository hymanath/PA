package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingUserDAO;

public class PartyMeetingUserDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingUserDAO partyMeetingUserDAO;

	public void setPartyMeetingUserDAO(IPartyMeetingUserDAO partyMeetingUserDAO) {
		this.partyMeetingUserDAO = partyMeetingUserDAO;
	}
	
	public void test()
	{
		partyMeetingUserDAO.getAll();
	}
}
