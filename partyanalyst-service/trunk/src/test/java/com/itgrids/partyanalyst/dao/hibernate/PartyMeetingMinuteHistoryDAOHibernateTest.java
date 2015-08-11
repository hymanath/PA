package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IPartyMeetingMinuteHistoryDAO;

public class PartyMeetingMinuteHistoryDAOHibernateTest extends BaseDaoTestCase{

	private IPartyMeetingMinuteHistoryDAO partyMeetingMinuteHistoryDAO;

	public void setPartyMeetingMinuteHistoryDAO(
			IPartyMeetingMinuteHistoryDAO partyMeetingMinuteHistoryDAO) {
		this.partyMeetingMinuteHistoryDAO = partyMeetingMinuteHistoryDAO;
	}
	
	public void test()
	{
		partyMeetingMinuteHistoryDAO.getAll();
	}
}
