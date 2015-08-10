package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteDAO;

public class PartyMeetingMinuteDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingMinuteDAO partyMeetingMinuteDAO;

	public void setPartyMeetingMinuteDAO(
			IPartyMeetingMinuteDAO partyMeetingMinuteDAO) {
		this.partyMeetingMinuteDAO = partyMeetingMinuteDAO;
	}
	
	public void test()
	{
		partyMeetingMinuteDAO.getAll();
	}
}
